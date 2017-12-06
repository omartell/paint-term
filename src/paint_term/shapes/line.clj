(ns paint-term.shapes.line
  (:require [paint-term.core :refer [parse pixels]]))

(defmethod parse :L
  ([[_ x1 y1 x2 y2]]
   {:shape :line
    :x1    (Integer/parseInt x1)
    :y1    (Integer/parseInt y1)
    :x2    (Integer/parseInt x2)
    :y2    (Integer/parseInt y2)}))

(defmethod pixels :line
  ([{:keys [:x1 :y1 :x2 :y2]}]
   (let [pixels (cond
                  (= x1 x2) (map #(vector x1 %) (if (< y1 y2)
                                                  (range y1 (inc y2))
                                                  (range y1 (dec y2) -1)))
                  (= y1 y2) (map #(vector % y1) (if (< x1 x2)
                                                  (range x1 (inc x2))
                                                  (range x1 (dec x2) -1))))]

     (zipmap pixels (repeat "x")))))
