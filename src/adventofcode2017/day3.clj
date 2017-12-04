(ns adventofcode2017.day3)

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

(defn side-of-ring
  [ring]
  (+ (* ring 2) 1))

(defn orth-steps
  [loc]
  (let [r (ring loc)]
    (Math/abs (- (mod (+ (- (pos-on-ring loc) r -1) r) (* r 2)) r))))

(defn spiral-distance
  [loc]
  (if (= loc 1) 0 ;Math breaks down at the center :/
    (+ (ring loc) (orth-steps loc))))
