(ns adventofcode2017.day4-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day4 :refer :all]))

(deftest part-1
  (is (valid-passphrase "aa bb cc dd ee"))
  (is (not(valid-passphrase "aa bb cc dd aa")))
  (is (valid-passphrase "aa bb cc dd aaa"))
  (is (= 2 (count-valid [
    "aa bb cc dd ee"
    "aa bb cc dd aa"
    "aa bb cc dd aaa"
  ]))))
