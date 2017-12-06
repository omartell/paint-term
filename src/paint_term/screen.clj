(ns paint-term.screen
  (:require [clojure.string :as string]))

(defn- sorted-map-by-ys []
  (sorted-map-by (fn [[x1 y1] [x2 y2]]
                   (compare [y1 x1] [y2 x2]))))

(defn- paint-row [col]
  (str "|" (string/join "" col) "|"))

(defn paint [width height pixels]
  (println (paint-row (repeat width "-")))
  (println (->> pixels
                (into (sorted-map-by-ys))
                (partition-all width)
                (map #(paint-row (vals %1)))
                (string/join "\n")))
  (println (paint-row (repeat width "-"))))
