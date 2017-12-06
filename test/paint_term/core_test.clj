(ns paint-term.core-test
  (:require [paint-term.core :as paint-term]
            [clojure.test :refer :all]))

(deftest parsing-input
  (testing "it is successful if it is a valid input"
    (is (= {:shape :line
            :x1 1
            :y1 1
            :x2 1
            :y2 1}
           (paint-term/parse-input "L 1 1 1 1"))))
  (testing "it returns invalid input if it's not valid"
    (is (= :invalid-input
           (paint-term/parse-input "L a 1 1 1")))))

(deftest commands->pixels
  (testing "it translates all commands and aggreates their pixels"
    (is (= {[1 1] "x"
            [1 2] "x"
            [1 3] "x"
            [2 1] "x"
            [2 2] " "
            [2 3] " "
            [3 1] "x"
            [3 2] " "
            [3 3] " "}
           (paint-term/commands->pixels [{:shape  :canvas
                                          :width  3
                                          :height 3}
                                         {:shape :line
                                          :x1    1
                                          :y1    1
                                          :x2    1
                                          :y2    3}
                                         {:shape :line
                                          :x1    1
                                          :y1    1
                                          :x2    3
                                          :y2    1}])))))

(deftest shape-no-canvas-validation
  (testing "it is false if there's a canvas defined"
    (is (= false
           (paint-term/shape-no-canvas? {:shape :line
                                         :x1    1
                                         :y1    1
                                         :x2    2
                                         :y2    2}
                                        [{:shape  :canvas
                                          :width  1
                                          :height 1}]))))
  (testing "it is true if there's no canvas defined"
    (is (= true
           (paint-term/shape-no-canvas? {:shape :line
                                         :x1    1
                                         :y1    1
                                         :x2    2
                                         :y2    2}
                                        [])))))
