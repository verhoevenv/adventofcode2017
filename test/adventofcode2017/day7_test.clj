(ns adventofcode2017.day7-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day7 :refer :all]))

(def example-list
  ["pbga (66)"
   "xhth (57)"
   "ebii (61)"
   "havc (66)"
   "ktlj (57)"
   "fwft (72) -> ktlj, cntj, xhth"
   "qoyq (66)"
   "padx (45) -> pbga, havc, qoyq"
   "tknk (41) -> ugml, padx, fwft"
   "jptl (61)"
   "ugml (68) -> gyxo, ebii, jptl"
   "gyxo (61)"
   "cntj (57)"])

(deftest regex
  (is (every? (fn [x] (re-matches prog-regex x)) example-list)))

(deftest part-1
  (is (= "tknk" (bottom example-list))))

(deftest part-2
  (is (= 66 (weight-of-tower "pbga" (progs example-list))))
  (is (= 251 (weight-of-tower "ugml" (progs example-list))))
  (is (= 243 (weight-of-tower "padx" (progs example-list))))
  (is (= 243 (weight-of-tower "fwft" (progs example-list))))
  (is (balanced? "pbga" (progs example-list)))
  (is (balanced? "ugml" (progs example-list)))
  (is (balanced? "padx" (progs example-list)))
  (is (balanced? "fwft" (progs example-list)))
  (is (not (balanced? "tknk" (progs example-list))))
  (is (= 60 (needed-weight-for-balance (progs example-list)))))