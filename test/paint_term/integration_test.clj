(ns paint-term.integration-test
  (:require [clojure.test :refer :all]
            [paint-term.main :as main]
            [clojure.string :as string]))

(defn input [& lines]
  (->> lines
       (map #(str % "\n"))
       string/join))

(defn output [& lines]
  (->> lines
       (map #(string/replace % "|" "\\|"))
       (string/join "\n")))

(deftest paiting-horizontal-lines
  (testing "it paints a horizontal line successfully and then quits"
    (is (not-empty (re-find (re-pattern (output "|---|"
                                                "|xxx|"
                                                "|   |"
                                                "|   |"
                                                "|---|"))
                            (with-out-str
                              (with-in-str (input "C 3 3"
                                                  "L 1 1 3 1"
                                                  "Q")
                                (main/-main))))))))

(deftest paiting-vertical-lines
  (testing "it paints a vertical line successfully and then quits"
    (is (not-empty (re-find (re-pattern (output "|---|"
                                                "|x  |"
                                                "|x  |"
                                                "|x  |"
                                                "|---|"))
                            (with-out-str
                              (with-in-str (input "C 3 3"
                                                  "L 1 1 1 3"
                                                  "Q")
                                (main/-main))))))))

(deftest paiting-rectangles
  (testing "it paints a rectangle successfully and then quits"
    (is (not-empty (re-find (re-pattern (output "|---|"
                                                "|xxx|"
                                                "|x x|"
                                                "|xxx|"
                                                "|---|"))
                            (with-out-str
                              (with-in-str (input "C 3 3"
                                                  "R 1 1 3 3"
                                                  "Q")
                                (main/-main))))))))
