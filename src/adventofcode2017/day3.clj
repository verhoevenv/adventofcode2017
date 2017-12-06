(ns adventofcode2017.day3)

(defrecord Coordinate [x y])

(defn bigger-or-eq-odd-square-root
  [loc]
  (let [root (int (Math/ceil (Math/sqrt loc)))]
    (if (odd? root) root (inc root))))

(defn strict-smaller-odd-square-root
  [loc]
  (let [root (int (Math/floor (Math/sqrt (- loc 1))))]
    (if (odd? root) root (dec root))))
    
(defn ring
  [loc]
  (/ (- (bigger-or-eq-odd-square-root loc) 1) 2))

(defn pos-on-ring
  "Number of squares from the lowest numbered square on the ring to loc, along the spiral"
  [loc]
  (let [lower-root (strict-smaller-odd-square-root loc)]
    (- (- loc (* lower-root lower-root)) 1)))

(defn size-of-ring
  [ring]
  (* ring 8))

(defn coor
  [loc]
  (let [r (ring loc) p (pos-on-ring loc)] 
    (let [s (size-of-ring r)]
      (cond
        (< p (* (/ s 4) 1)) (->Coordinate r (- p r -1))
        (< p (* (/ s 4) 2)) (->Coordinate (- (* r 3) p 1) r)
        (< p (* (/ s 4) 3)) (->Coordinate (- r) (- (* r 5) p 1))
        (< p (* (/ s 4) 4)) (->Coordinate (- p (* r 7) -1) (- r))))))

(defn spiral-distance
  [loc]
  (let [c (coor loc)]
    (+ (Math/abs (:x c)) (Math/abs (:y c)))))

(defn numbers-from
  [x]
  (iterate inc x))

(defn range-inclusive
  [start end]
  (range start (inc end)))

(defn neighbours
  [coor]
  (for 
    [x (range-inclusive -1 1) y (range-inclusive -1 1) :when (not= x y 0)]
    (->Coordinate (+ (:x coor) x) (+ (:y coor) y))))

(defn adjacent-sum
  [memory coor]
  (reduce + (map (fn [x] (get memory x 0)) (neighbours coor))))

(defn store-next-adjacent-sum
  [memory loc]
  (assoc memory (coor loc) (adjacent-sum memory (coor loc))))

(def init-grid
  {(->Coordinate 0 0) 1})

(def expanding-memory
  (reductions store-next-adjacent-sum init-grid (numbers-from 2)))

(defn stress-test-up-to
  [max-val]
  (reduce 
    (fn
      [memory loc]
      (let [next-sum (adjacent-sum memory (coor loc))]
        (if (> next-sum max-val)
          (reduced next-sum)
          (assoc memory (coor loc) next-sum))))
    init-grid
    (numbers-from 2)))