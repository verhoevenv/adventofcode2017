(ns adventofcode2017.day3-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day3 :refer :all]))

(deftest part-1
  (is (= 0 (ring 1)))
  (is (= 1 (ring 2)))
  (is (= 2 (ring 17)))
  (is (= 2 (ring 25)))
  (is (= 3 (ring 26)))
  (is (= 0 (spiral-distance 1)))
  (is (= 3 (spiral-distance 12)))
  (is (= 2 (spiral-distance 23)))
  (is (= 31 (spiral-distance 1024))))
