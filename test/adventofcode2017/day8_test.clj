(ns adventofcode2017.day8-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day8 :refer :all]))

(def example-instructions
  ["b inc 5 if a > 1"
   "a inc 1 if b < 5"
   "c dec -10 if a >= 1"
   "c inc -20 if c == 10"])

(deftest regex
  (is (every? (fn [x] (re-matches instruction-regex x)) example-instructions)))

(deftest part-1
  (is (= #{"a" "b" "c"} (all-registries (instrs example-instructions))))
  (is (= {"a" 1} (exec  {"a" 0} (instr "a inc 1 if a == 0"))))
  (is (= {"a" 0} (exec  {"a" 0} (instr "a inc 1 if a != 0"))))
  (is (= {"a" -1} (exec  {"a" 0} (instr "a dec 1 if a < 5"))))
  (is (= {"a" 1 "b" 0 "c" -10} (exec-all (instrs example-instructions))))
  (is (= 1 (largest-register-value example-instructions))))
