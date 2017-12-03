(ns adventofcode2017.day2)

(defn string-rows
  [spreadsheet]
  (map clojure.string/trim (clojure.string/split-lines spreadsheet)))

(defn int-row
  [row]
  (map #(Integer/parseInt %) (clojure.string/split row #"\s+")))

(defn minmax-row
  [row]
  (- (reduce max row) (reduce min row)))

(defn checksum-minmax
  [spreadsheet]
  (reduce + (map minmax-row (map int-row (string-rows spreadsheet)))))

(defn divide-if-possible
  [a b]
  (if (and (not= a b) (= 0 (mod a b))) (/ a b) 0))

(defn div-row
  [row]
  (first (filter pos? (for [x row y row] (divide-if-possible x y)))))
  
(defn checksum-div
  [spreadsheet]
  (reduce + (map div-row (map int-row (string-rows spreadsheet)))))
  