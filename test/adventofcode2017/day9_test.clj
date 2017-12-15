(ns adventofcode2017.day9-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day9 :refer :all]))

(deftest lexer
  (is (= [:ob :cb] (lex "{}")))
  (is (= [:ob :ob :ob :cb :cb :cb] (lex "{{{}}}")))
  (is (= [:ob :ob :cb :ob :cb :cb] (lex "{{},{}}")))
  (is (= [:ob :ob :ob :cb :ob :cb :ob :ob :cb :cb :cb :cb] (lex "{{{},{},{{}}}}")))
  (is (= [:ob :cb] (lex "{<a>,<a>,<a>,<a>}")))
  (is (= [:ob :ob :cb :ob :cb :ob :cb :ob :cb :cb] (lex "{{<ab>},{<ab>},{<ab>},{<ab>}}")))
  (is (= [:ob :ob :cb :ob :cb :ob :cb :ob :cb :cb] (lex "{{<!!>},{<!!>},{<!!>},{<!!>}}")))
  (is (= [:ob :ob :cb :cb] (lex "{{<a!>},{<a!>},{<a!>},{<ab>}}"))))

(deftest part-1
  (is (= 1 (score "{}")))
  (is (= 6 (score "{{{}}}")))
  (is (= 5 (score "{{},{}}")))
  (is (= 16 (score "{{{},{},{{}}}}")))
  (is (= 1 (score "{<a>,<a>,<a>,<a>}")))
  (is (= 9 (score "{{<ab>},{<ab>},{<ab>},{<ab>}}")))
  (is (= 9 (score "{{<!!>},{<!!>},{<!!>},{<!!>}}")))
  (is (= 3 (score "{{<a!>},{<a!>},{<a!>},{<ab>}}"))))
