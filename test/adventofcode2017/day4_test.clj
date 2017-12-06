(ns adventofcode2017.day4-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day4 :refer :all]))

(deftest part-1
  (is (valid-passphrase-duplicate "aa bb cc dd ee"))
  (is (not (valid-passphrase-duplicate "aa bb cc dd aa")))
  (is (valid-passphrase-duplicate "aa bb cc dd aaa"))
  (is (= 2 (count-valid valid-passphrase-duplicate [
    "aa bb cc dd ee"
    "aa bb cc dd aa"
    "aa bb cc dd aaa"
  ]))))

(deftest part-2
  (is (valid-passphrase-anagram "abcde fghij"))
  (is (not(valid-passphrase-anagram "abcde xyz ecdab")))
  (is (valid-passphrase-anagram "a ab abc abd abf abj"))
  (is (valid-passphrase-anagram "iiii oiii ooii oooi oooo"))
  (is (not (valid-passphrase-anagram "oiii ioii iioi iiio")))
  (is (= 3 (count-valid valid-passphrase-anagram [
    "abcde fghij"
    "abcde xyz ecdab"
    "a ab abc abd abf abj"
    "iiii oiii ooii oooi oooo"
    "oiii ioii iioi iiio"
  ]))))
  