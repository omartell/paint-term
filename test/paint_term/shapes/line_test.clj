(ns paint-term.shapes.line-test
  (:require [paint-term.core :refer [parse pixels]]
            [clojure.test :refer :all]))

(deftest parsing-line-inputs
  (testing "it successfully parses a valid line input"
    (is (= {:shape :line
            :x1    1
            :y1    1
            :x2    1
            :y2    3}
           (parse [:L "1" "1" "1" "3"]))))
  (testing "it errors if one of the inputs is not valid"
    (is (thrown? java.lang.NumberFormatException (parse [:L "a" "1" "1" "3"])))))

(deftest translating-to-pixels
  (testing "it successfully translates a vertical line into pixels"
    (is (= {[1 1] "x"
            [1 2] "x"
            [1 3] "x"}
           (pixels {:shape :line
                    :x1     1
                    :y1     1
                    :x2     1
                    :y2     3})))
    (is (= {[1 1] "x"
            [1 2] "x"
            [1 3] "x"}
           (pixels {:shape :line
                    :x1     1
                    :y1     3
                    :x2     1
                    :y2     1}))))
  (testing "it successfully translates a horizontal line into pixels"
    (is (= {[1 1] "x"
            [2 1] "x"
            [3 1] "x"}
           (pixels {:shape :line
                    :x1     1
                    :y1     1
                    :x2     3
                    :y2     1})))
    (is (= {[1 1] "x"
            [2 1] "x"
            [3 1] "x"}
           (pixels {:shape :line
                    :x1     3
                    :y1     1
                    :x2     1
                    :y2     1})))))
