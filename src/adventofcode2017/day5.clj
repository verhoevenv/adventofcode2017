(ns adventofcode2017.day5)

(defrecord CpuState [instructions pointer])

(defn jump 
  [pointer instructions]
  (+ pointer (get instructions pointer)))

(defn increase-offset
  [pointer instructions]
  (update instructions pointer inc))

(defn increase-or-decrease-offset
  [pointer instructions]
  (update instructions pointer
    (if (>= (get instructions pointer) 3) dec inc)))
  
(defn jumps-needed
  [offsets]
  (loop [instructions offsets
         pointer 0
         steps 0]
    (if-not (< -1 pointer (count instructions))
      steps  
      (recur (increase-offset pointer instructions)
             (jump pointer instructions)
             (inc steps)))))

(defn jumps-needed-strange
  [offsets]
  (loop [instructions offsets
        pointer 0
        steps 0]
    (if-not (< -1 pointer (count instructions))
      steps  
      (recur (increase-or-decrease-offset pointer instructions)
              (jump pointer instructions)
              (inc steps)))))
