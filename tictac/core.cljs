(ns tictac.core)

(def a true)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn sum1
  []
  (let [x (map #(if (= "" %)
                  0
                  1) (convert-to-list))
        y (reduce + x)]
    #_(js/alert (+ aa b c d e f g h i))

    (if (= 9 y)
      (do  (js/alert "Game over!")
           false)
      true)))




(defn reset1
  []
  (doall (map #(set! (.-value (.getElementById js/document (str "output" %)))
                     "")
              (range 1 10))))



#_(defn reset2
  []
  (for [i (range 1 10)]
    (reset1 i)))

(defn convert-to-list
  []
  (mapv #(.-value (.getElementById js/document (str "output" %))) (range 1 10)))


(defn check
  []
  (let [coll (convert-to-list)
        x (partition 3 coll)
        y (mapv #(into [] %) x)]
    y))

(defn down
  [lst y]
  (let [a (get-in lst [0 y] "d")
        b (get-in lst [1 y] "d")
        c (get-in lst [2 y] "d")
        ]
    #_(* a b c d)
    (if (= a b c)
      (if-not (= a "")
        "Game Over"))))

(defn right
  [lst x]
  (let [a (get-in lst [x 0] "d")
        b (get-in lst [x 1] "d")
        c (get-in lst [x 2] "d")
        ]
    #_(* a b c d)
    (if (= a b c)
      (if-not (= a "")
        "Game Over"))))

(defn horizontal?
  [lst]
  (for [i (range 3)
        :when (= "Game Over" (right lst i))]
    true))

(defn vertical?
  [lst]
  (for [i (range 3)
        :when (= "Game Over" (down lst i))]
    true))



(defn diagonal-right
  [lst]
  (let [a (get-in lst [0 0] "d")
        b (get-in lst [1 1] "d")
        c (get-in lst [2 2] "d")
        ]
    #_(* a b c d)
    (if (= a b c)
      (if-not (= a "")
        "Game Over"))))

(defn diagonal-left
  [lst]
  (let [a (get-in lst [0 2] "d")
        b (get-in lst [1 1] "d")
        c (get-in lst [2 0] "d")
        ]
    #_(* a b c d)
    (if (= a b c)
      (if-not (= a "")
        "Game Over"))))


(defn diagonals?
  [lst]
  (if (or (= "Game Over" (diagonal-right lst)) (= "Game Over" (diagonal-left lst)))
    true))

(defn game-over?
  [lst]
  (if (or (> (count (vertical? lst)) 0) (> (count (horizontal? lst)) 0) (diagonals? lst))
    true
    false))

(defn chance
  [id]
  (if (sum1)
    (if (game-over? (check))
      (do
        (js/alert "Game Over!")
        (reset1))
      (do
        (if (true? a)
          (def a false)
          (def a true))
        (if (= "" (.-value (.getElementById js/document (str "output" id))))
          (set!  (.-value (.getElementById js/document (str "output" id)))
                 (if (true? a)
                   "x"
                   "o"))
          (js/alert "move not allowed"))))))
