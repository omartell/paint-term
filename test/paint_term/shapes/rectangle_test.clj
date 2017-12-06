(ns paint-term.shapes.rectangle-test
  (:require [paint-term.core :refer [parse pixels]]
            [clojure.test :refer :all]))

(deftest parsing-rectangle-inputs
  (testing "it successfully parses a valid rectangle input"
    (is (= {:shape :rectangle
            :x1    1
            :y1    1
            :x2    3
            :y2    3}
           (parse [:R "1" "1" "3" "3"]))))
  (testing "it errors if one of the inputs is not valid"
    (is (thrown? java.lang.NumberFormatException (parse [:R "a" "3"])))))

(deftest translating-to-pixels
  (testing "it successfully translates a rectangle into pixels"
    (is (= {[1 1] "x"
            [1 2] "x"
            [1 3] "x"
            [2 1] "x"
            [2 3] "x"
            [3 1] "x"
            [3 2] "x"
            [3 3] "x"}
           (pixels {:shape :rectangle
                    :x1    1
                    :y1    1
                    :x2    3
                    :y2    3})))
    (is (= {[1 1] "x"
            [1 2] "x"
            [1 3] "x"
            [2 1] "x"
            [2 3] "x"
            [3 1] "x"
            [3 2] "x"
            [3 3] "x"}
           (pixels {:shape :rectangle
                    :x1    3
                    :y1    3
                    :x2    1
                    :y2    1})))))
