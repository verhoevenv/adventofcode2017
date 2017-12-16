(ns adventofcode2017.day9)

(defrecord FSM
  [transitions state])

(defrecord MachineResult
  [new-state token])

(def init-fsm
  (->FSM
    (array-map
     [:accepting, \{]           (->MachineResult :accepting :ob)
     [:accepting, \}]           (->MachineResult :accepting, :cb)
     [:accepting, \,]           (->MachineResult :accepting, :comma)
     [:accepting, \<]           (->MachineResult :garbage, :og)
     [:garbage, \>]             (->MachineResult :accepting, :cg)
     [:garbage, :any]           (->MachineResult :garbage, nil)
     [:accepting, \!]           (->MachineResult :accepting-escaped, :esc)
     [:accepting-escaped, :any] (->MachineResult :accepting, nil)
     [:garbage, \!]             (->MachineResult :garbage-escaped, :esc)
     [:garbage-escaped, :any]   (->MachineResult :garbage, nil)
    )
    :accepting))

(defn non-nil
  [col]
  (filter some? col))

(defn select-transition
  [fsm char]
  (let [t (:transitions fsm)
        s (:state fsm)]
    (first (non-nil [(get t [s char]) (get t [s :any])]))))

(defn fsm-consume
  [[fsm tokens] char]
  (let [machine-result (select-transition fsm char)]
    [(assoc fsm :state (:new-state machine-result))
     (conj tokens (:token machine-result))]))

(defn raw-lex
  [stream]
  (second (reduce fsm-consume [init-fsm []] stream)))

(def interesting-tokens
  #{:ob, :cb})

(defn lex
  [stream]
  (filter interesting-tokens (raw-lex stream)))

(defn parse-token
  [[current-depth groups] token]
  (case token
    :ob [(+ current-depth 1) groups]
    :cb [(- current-depth 1) (conj groups current-depth)]))

(defn parse
  [tokens]
  (second (reduce parse-token [0 []] tokens)))

(defn score
  [stream]
  (reduce + (parse (lex stream))))
