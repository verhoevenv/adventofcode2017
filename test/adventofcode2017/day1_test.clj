(ns adventofcode2017.day1-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day1 :refer :all]))

(deftest part-1
  (is (= 3 (captcha-next "1122")))
  (is (= 4 (captcha-next "1111")))
  (is (= 0 (captcha-next "1234")))
  (is (= 9 (captcha-next "91212129"))))

(deftest part-2
  (is (= 6  (captcha-half "1212")))
  (is (= 0  (captcha-half "1221")))
  (is (= 4  (captcha-half "123425")))
  (is (= 12 (captcha-half "123123")))
  (is (= 4  (captcha-half "12131415"))))
  