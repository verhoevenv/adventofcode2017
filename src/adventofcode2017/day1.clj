(ns adventofcode2017.day1)

(defn digit
  [d]
  (Character/digit d 10))

(defn val-if-matches
  [x y]
  (if (= x y) x 0))
  
(defn captcha
  "Solves a not-human captcha"
  [captcha-string]
  (let [digits (map digit captcha-string)]
    (let [next-digits (concat (rest digits) [(first digits)])]
      (reduce + (map val-if-matches digits next-digits)))))
