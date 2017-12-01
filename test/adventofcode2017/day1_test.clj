(ns adventofcode2017.day1-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day1 :refer :all]))

(deftest part-1
  (is (= 3 (captcha "1122")))
  (is (= 4 (captcha "1111")))
  (is (= 0 (captcha "1234")))
  (is (= 9 (captcha "91212129"))))
