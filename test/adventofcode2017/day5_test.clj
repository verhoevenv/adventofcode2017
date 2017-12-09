(ns adventofcode2017.day5-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day5 :refer :all]))

(deftest part-1
  (is (= 5 (jumps-needed [0 3 0 1 -3]))))

(deftest part-1
  (is (= 10 (jumps-needed-strange [0 3 0 1 -3]))))
  