(ns adventofcode2017.day8)

(require '[clojure.string :as str])
(require 'clojure.set)

(def instruction-regex
  #"([a-z]+) (inc|dec) (-?[0-9]+) if ([a-z]+) ([<>=!]+) (-?[0-9]+)")

(defrecord Instruction
  [mod-reg mod cond-reg condition])

(defn parse-op
  [op]
  (case op
        "inc" +
        "dec" -))

(defn parse-cond
  [c]
  (case c
        "<" <
        ">" >
        "<=" <=
        ">=" >=
        "==" =
        "!=" not=))
        

(defn instr
  [str]
  (let [matcher (re-matcher instruction-regex str)]
    (if (.matches matcher)
      (->Instruction
        (.group matcher 1)
        (fn [v]
          ((parse-op (.group matcher 2))
            v
            (Integer/parseInt (.group matcher 3))))
        (.group matcher 4)
        (fn [v]
          ((parse-cond (.group matcher 5))
            v
            (Integer/parseInt (.group matcher 6))))
        ))))

(defn instrs
  [list-str]
  (map instr list-str))

(defn all-registries
  [listing]
  (clojure.set/union (set (map :mod-reg listing)) (set (map :cond-reg listing))))

(defn init-mem
  [registries]
  (zipmap registries (repeat 0)))

(defn exec
  [mem instr]
  (let [c (:condition instr)
        v-c (get mem (:cond-reg instr))
        m (:mod instr)
        m-reg (:mod-reg instr)]
    (if (c v-c)
     (update mem m-reg m)
     mem)))

(defn exec-all
  [listing]
  (reduce exec (init-mem (all-registries listing)) listing))

(defn largest-register-value
  [instructions-str]
  (let [listing (instrs instructions-str)]
  (let [end-mem (exec-all listing)]
    (apply max (vals end-mem)))))
