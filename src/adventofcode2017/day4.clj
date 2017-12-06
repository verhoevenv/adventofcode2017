(ns adventofcode2017.day4)

(defn words
  [phrase]
  (clojure.string/split phrase #" "))

(defn valid-by
  [f phrase]
  (every? (fn [x] (= 1 (count x))) 
    (vals
      (group-by f (words phrase)))))

(defn valid-passphrase-duplicate
  [phrase]
  (valid-by identity phrase))

(defn valid-passphrase-anagram
  [phrase]
  (valid-by (fn [x] (sort (seq x))) phrase))

(defn count-valid
  [validity phrases]
  (count (filter validity phrases)))