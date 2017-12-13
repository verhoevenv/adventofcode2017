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
        (Integer/parseInt (.group matcher 2))
        (if-let [s (.group matcher 3)] (str/split s #", ") [])
      ))))

(defn progs
  [list-str]
  (reduce #(assoc %1 (:name %2) %2) {} (map prog list-str)))

(defn immediately-holding?
  [p1 p2 listing]
  (some #{p2} (:above (get listing p1))))

(defn holding-none?
  [program listing]
  (empty? (:above (get listing program))))

(defn none-holding?
  [program listing]
  (not-any? 
    (fn [x] (immediately-holding? x program listing))
    (keys listing)))

(defn bottom
  [list-str]
  (let [ps (progs list-str)]
    (first 
      (filter #(none-holding? %1 ps) (keys ps)))))
  
(defn weight-of-tower
  [root-str listing]
  (let [root-prog (get listing root-str)]
    (+
      (:weight root-prog)
      (reduce + (map #(weight-of-tower %1 listing) (:above root-prog))))))

(defn balanced?
  [root-str listing]
  (let [aboves (:above (get listing root-str))]
    (if (empty? aboves)
      true
      (apply = (map #(weight-of-tower %1 listing) aboves)))))

(defn different-one
  [coll]
  (let [freqs (frequencies coll)]
    {:normal (key (first (filter #(< 1 (val %1)) freqs)))
     :odd (key (first (filter #(= 1 (val %1)) freqs)))}))

(defn delta-odd-norm
  [different-map]
  (- 
    (get different-map :odd)
    (get different-map :normal)))

;not optimal but let's see if it works..
(defn needed-weight-for-balance
  [listing]
  (let [unbalanced (first (filter #(not (balanced? %1 listing)) (keys listing)))]
  (let [weights-unbalanced (reduce #(assoc %1 %2 (weight-of-tower %2 listing)) {} (:above (get listing unbalanced)))]
  (let [weights-group (different-one (vals weights-unbalanced))]
  (let [weight-difference (delta-odd-norm weights-group)
        wrong-prog (key (first (filter #(= (val %1) (get weights-group :odd)) weights-unbalanced)))]
    (- (:weight (get listing wrong-prog)) weight-difference))))))