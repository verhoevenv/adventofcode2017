(ns adventofcode2017.day6-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day6 :refer :all]))

(deftest part-1
  (is (= [2 4 1 2] (reallocate-once [0 2 7 0])))
  (is (= [0 3 4 1] (reallocate-once [3 2 3 0])))
  (is (= 5 (steps-before-loop [0 2 7 0])))
  (is (= 4 (cycles-in-loop [0 2 7 0]))))
