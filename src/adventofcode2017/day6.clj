(ns adventofcode2017.day6)

(defn by-amount-and-idx
  [b1 b2]
  (compare [(second b2) (first b1)]
           [(second b1) (first b2)]))

(defn index-of-max
  [banks]
  (first (first
    (sort-by identity by-amount-and-idx
      (map-indexed vector banks)))))

(defn next-bank
  [curr amount-banks]
  (mod (inc curr) amount-banks))

(defn redistribute
  [init-banks init-blocks from]
  (loop [banks init-banks
         blocks init-blocks
         target from]
    (if (= 0 blocks)
      banks
      (recur
        (update banks target inc)
        (dec blocks)
        (next-bank target (count banks))))))

(defn reallocate-once 
  [banks]
  (let [n (count banks)
        maxi (index-of-max banks)]
    (let [num (get banks maxi)
          banks-taken (assoc banks maxi 0)]
      (redistribute banks-taken num (next-bank maxi n)))))

(defn until-looped
  [init-banks]
  (loop [banks init-banks
    steps 0
    history []]
  (if (some #{banks} history)
    [steps banks]
    (recur (reallocate-once banks)
            (inc steps)
            (conj history banks)))))

(defn steps-before-loop
  [banks]
  (first (until-looped banks)))
            
(defn cycles-in-loop
  [banks]
  (let [banks-at-loop (second (until-looped banks))]
    ;We'll just loop a second time and count the steps needed
    (first (until-looped banks-at-loop))))