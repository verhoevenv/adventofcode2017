(ns adventofcode2017.day7)

(require '[clojure.string :as str])

(def prog-regex
  #"([a-z]+) \(([0-9]+)\)(?: -> ([a-z, ]+))?")

(defrecord Program
  [name weight above])

(defn prog
  [str]
  (let [matcher (re-matcher prog-regex str)]
    (if (.matches matcher)
      (->Program
        (.group matcher 1)
        (.group matcher 2)
        (if-let [s (.group matcher 3)] (str/split s #", ") [])
      ))))

(defn progs
  [list-str]
  (reduce #(assoc %1 (:name %2) %2) {} (map prog list-str)))

(defn is-immediately-holding
  [p1 p2 listing]
  (some #{p2} (:above (get listing p1))))

(defn none-holding
  [program listing]
  (not-any? 
    (fn [x] (is-immediately-holding x program listing))
    (keys listing)))

(defn bottom
  [list-str]
  (let [ps (progs list-str)]
    (first 
      (filter #(none-holding %1 ps) (keys ps)))))
  