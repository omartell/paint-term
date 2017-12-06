(ns paint-term.shapes.canvas-test
  (:require [paint-term.core :refer [parse pixels]]
            [clojure.test :refer :all]))

(deftest parsing-canvas-input
  (testing "it successfully parses a valid canvas input"
    (is (= {:shape  :canvas
            :width  3
            :height 3}
           (parse [:C "3" "3"]))))
  (testing "it errors if one of the inputs is not valid"
    (is (thrown? java.lang.NumberFormatException (parse [:C "a" "3"])))))

(deftest translating-to-pixels
  (testing "it successfully translates a canvas into pixels"
    (is (= {[1 1] " "
            [1 2] " "
            [1 3] " "
            [2 1] " "
            [2 2] " "
            [2 3] " "
            [3 1] " "
            [3 2] " "
            [3 3] " "}
           (pixels {:shape  :canvas
                    :width  3
                    :height 3})))))
