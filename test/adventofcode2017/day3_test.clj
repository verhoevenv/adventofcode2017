(ns adventofcode2017.day3-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day3 :refer :all]))

(deftest part-1
  (is (= (->Coordinate 0 0) (coor 1)))
  (is (= (->Coordinate 1 0) (coor 2)))
  (is (= (->Coordinate 1 1) (coor 3)))
  (is (= (->Coordinate 0 1) (coor 4)))
  (is (= (->Coordinate -1 1) (coor 5)))
  (is (= (->Coordinate -1 0) (coor 6)))
  (is (= (->Coordinate -1 -1) (coor 7)))
  (is (= (->Coordinate 0 -1) (coor 8)))
  (is (= (->Coordinate 1 -1) (coor 9)))
  (is (= (->Coordinate 2 -1) (coor 10)))
  (is (= (->Coordinate -2 2) (coor 17)))
  (is (= (->Coordinate 2 -2) (coor 25)))
  (is (= (->Coordinate 3 -2) (coor 26)))
  (is (= (->Coordinate 3 -1) (coor 27)))
  (is (= (->Coordinate 0 3) (coor 34)))
  (is (= (->Coordinate -3 0) (coor 40)))
  (is (= (->Coordinate 0 -3) (coor 46)))
  (is (= 1 (ring 2)))
  (is (= 2 (ring 17)))
  (is (= 2 (ring 25)))
  (is (= 3 (ring 26)))
  (is (= 0 (spiral-distance 1)))
  (is (= 3 (spiral-distance 12)))
  (is (= 2 (spiral-distance 23)))
  (is (= 31 (spiral-distance 1024))))

(defn summed-value-at
  [loc]
  (get (nth expanding-memory loc) (coor loc)))

(deftest part-2
  (is (= 1 (summed-value-at 1)))
  (is (= 1 (summed-value-at 2)))
  (is (= 2 (summed-value-at 3)))
  (is (= 4 (summed-value-at 4)))
  (is (= 5 (summed-value-at 5)))
  (is (= 10 (summed-value-at 6)))
  (is (= 11 (summed-value-at 7)))
  (is (= 806 (summed-value-at 23)))
  (is (= 4 (stress-test-up-to 3)))
  (is (= 806 (stress-test-up-to 800))))