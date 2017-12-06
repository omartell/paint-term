(ns paint-term.shapes.rectangle
  (:require [paint-term.core :refer [parse pixels]]))

(defmethod parse :R
  ([[_ x1 y1 x2 y2]]
   {:shape :rectangle
    :x1    (Integer/parseInt x1)
    :y1    (Integer/parseInt y1)
    :x2    (Integer/parseInt x2)
    :y2    (Integer/parseInt y2)}))

(defmethod pixels :rectangle
  ([{:keys [:x1 :y1 :x2 :y2]}]
   (merge (pixels {:x1 x1 :y1 y1 :x2 x1 :y2 y2 :shape :line})
          (pixels {:x1 x1 :y1 y1 :x2 x2 :y2 y1 :shape :line})
          (pixels {:x1 x1 :y1 y2 :x2 x2 :y2 y2 :shape :line})
          (pixels {:x1 x2 :y1 y1 :x2 x2 :y2 y2 :shape :line}))))
