(ns adventofcode2017.day4)

(defn valid-passphrase
  [phrase]
  (apply distinct? (clojure.string/split phrase #" ")))

(defn count-valid
  [phrases]
  (count (filter valid-passphrase phrases)))