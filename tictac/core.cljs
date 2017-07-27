(ns tictac.core)

(def a true)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn sum1
  []
  (let [aa (if (= "" (.-value (.getElementById js/document (str "output" 1)))) 0
               1)
        b (if (= "" (.-value (.getElementById js/document (str "output" 2)))) 0
              1 )
        c (if (= "" (.-value (.getElementById js/document (str "output" 3)))) 0 1 )
        d (if (= "" (.-value (.getElementById js/document (str "output" 4)))) 0 1 )
        e (if (= "" (.-value (.getElementById js/document (str "output" 5)))) 0 1 )
        f (if (= "" (.-value (.getElementById js/document (str "output" 6)))) 0 1 )
        g (if (= "" (.-value (.getElementById js/document (str "output" 7)))) 0 1 )
        h (if (= "" (.-value (.getElementById js/document (str "output" 8)))) 0 1 )
        i (if (= "" (.-value (.getElementById js/document (str "output" 9)))) 0 1 )]
    #_(js/alert (+ aa b c d e f g h i))
    (if (= 9 (+ aa b c d e f g h i))
      (do  (js/alert "Game over!")
           false)
      true)))




(defn reset1
  []
  (set! (.-value (.getElementById js/document (str "output" 1)))
        "")
  (set! (.-value (.getElementById js/document (str "output" 2)))
        "")
  (set! (.-value (.getElementById js/document (str "output" 3)))
        "")
  (set! (.-value (.getElementById js/document (str "output" 4)))
        "")
  (set! (.-value (.getElementById js/document (str "output" 5)))
        "")
  (set! (.-value (.getElementById js/document (str "output" 6)))
        "")
  (set! (.-value (.getElementById js/document (str "output" 7)))
        "")
  (set! (.-value (.getElementById js/document (str "output" 8)))
        "")
  (set! (.-value (.getElementById js/document (str "output" 9)))
        ""))



#_(defn reset2
  []
  (for [i (range 1 10)]
    (reset1 i)))

(defn convert-to-list
  []
  (let [aa (.-value (.getElementById js/document (str "output" 1)))
        b (.-value (.getElementById js/document (str "output" 2)))
        c (.-value (.getElementById js/document (str "output" 3)))
        d (.-value (.getElementById js/document (str "output" 4)))
        e (.-value (.getElementById js/document (str "output" 5)))
        f (.-value (.getElementById js/document (str "output" 6)))
        g (.-value (.getElementById js/document (str "output" 7)))
        h (.-value (.getElementById js/document (str "output" 8)))
        i (.-value (.getElementById js/document (str "output" 9)))
        ]
    [aa b c d e f g h i]))


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
