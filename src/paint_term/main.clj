(ns paint-term.main
  (:require [clojure.string :as string]
            [paint-term.core :as paint]
            [clojure.spec.alpha :as s]
            [paint-term.shapes.canvas]
            [paint-term.shapes.line]
            [paint-term.shapes.rectangle])
  (:gen-class))

(defn -main [& args]
  (println "Welcome to paint-term")
  (println "Available commands:")
  (println "C w h")
  (println "L x1 y1 x2 y2")
  (println "R x1 y1 x2 y2")
  (println "Q Quit the program")
  (paint/start))
