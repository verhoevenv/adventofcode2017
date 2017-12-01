(ns adventofcode2017.day1)

(defn digit
  [d]
  (Character/digit d 10))

(defn rotate
  [n s]
  (concat (drop n s) (take n s)))

(defn val-if-matches
  [x y]
  (if (= x y) x 0))
  
(defn captcha-next
  "Solves a not-human captcha"
  [captcha-string]
  (let [digits (map digit captcha-string)]
    (let [next-digits (rotate 1 digits)]
      (reduce + (map val-if-matches digits next-digits)))))

(defn captcha-half
  [captcha-string]
  (let [digits (map digit captcha-string)]
    (let [next-digits (rotate (/ (count digits) 2) digits)]
      (reduce + (map val-if-matches digits next-digits)))))
