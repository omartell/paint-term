(ns paint-term.core
  (:require [clojure.string :as string]
            [paint-term.screen :as screen]))

(defmulti parse
  "Multimethod used to parse inputs given by the user. It dispatches
   on the first character of the input (C, L, R) once the input has been
   split into characters. This is the first extension point that needs
   to be implemented when a new shape is added."
  (comp keyword first))

(defmethod parse :default
  ([input]
   (throw (ex-info "Parsing failed for input" {:input input}))))

(defmethod parse :Q
  ([_] :quit))

(defmulti pixels
  "Multimethod used to translate a given shape command into a map
  of pixels where the keys are [x y] coordinates and the values are the
  object that needs to be rendered for that pixel, i.e. x. This is
  the second extension point that new shapes need to implement."
  :shape)

(defmethod pixels :default
  ([command]
   (throw (ex-info "Translating input to pixels failed for command"
                   {:command command}))))

(defn parse-input [input]
  (try
    (-> input
        (string/split #"\s")
        parse)
    (catch Throwable t
      :invalid-input)))

(defn commands->pixels [commands]
  (->> commands
       (map pixels)
       (reduce merge)))

(defn- process-commands
  "This is the main flow of the application. It takes a collection of commands
  where the first command is the canvas, translates them into pixels and prints
  them on to the screen."
  [[{:keys [:width :height]} :as commands]]
  (->> commands
       commands->pixels
       (screen/paint width height)))

(defn shape-no-canvas?
  "Validation to check if the user is trying to draw a shape without
  first defining a canvas."
  [command history]
  (let [canvas-defined? (->> history
                             (map :shape)
                             (some #{:canvas}))]
    (and (not= :canvas (:shape command))
         (not canvas-defined?))))

(defn start
  "Start interactive loop"
  []
  (loop [input   (read-line)
         history []]
    (let [command (parse-input input)]
      (cond (= :quit command)                  (println "Bye.")
            (= :invalid-input command)         (do
                                                 (println "Invalid input. Try again:")
                                                 (recur (read-line) history))
            (shape-no-canvas? command history) (do
                                                 (println "Canvas not defined. Define it with C W H.")
                                                 (recur (read-line) history))
            :default                           (let [commands (conj history command)]
                                                 (process-commands commands)
                                                 (recur (read-line) commands))))))
