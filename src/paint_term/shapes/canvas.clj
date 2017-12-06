(ns paint-term.shapes.canvas
  (:require [paint-term.core :refer [parse pixels]]))

(defmethod parse :C
  ([[_ w h]]
   {:shape  :canvas
    :width  (Integer/parseInt w)
    :height (Integer/parseInt h)}))

(defmethod pixels :canvas
  ([{:keys [:width :height]}]
   (zipmap (for [x (range width)
                 y (range height)]
             [(+ x 1) (+ y 1)])
           (repeat " "))))
