(ns adventofcode2017.day2-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day2 :refer :all]))

(deftest part-1
  (is (= 8 (minmax-row '(5 1 9 5))))
  (is (= 4 (minmax-row '(7 5 3))))
  (is (= 6 (minmax-row '(2 4 6 8))))
  (is (= 18 (checksum-minmax
    "5 1 9 5
    7 5 3
    2 4 6 8"))))

(deftest part-2
  (is (= 4 (div-row '(5 9 2 8))))
  (is (= 3 (div-row '(9 4 7 3))))
  (is (= 2 (div-row '(3 8 6 5))))
  (is (= 9 (checksum-div
    "5 9 2 8
    9 4 7 3
    3 8 6 5"))))
    