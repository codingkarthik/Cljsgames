(ns memorygame.core
  (:require [clojure.string :as string]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))




(defn get-element
  [id]
  (.-value (.getElementById js/document (str "" id))))

(defn set-element-life
  [id lock life]
  (let [h (string/split (get-element id) #"_")
        j  (drop 1 (drop-last h))]
    (set!
     (.-value (.getElementById js/document (str "" id)))
     (str id  lock  (second j) life))))

(defn game-over?
  []
  (let [x (mapv #(get-element %) (range 1 17))
        y (filter (fn [c]
                    (if (= "Unlocked" (second (string/split c #"_")))
                      true
                      false))
                  x)]
    (if (zero? (count y))
      true
      false)))



(defn createboard
  []
  (loop [x (shuffle (apply concat (repeat 2 (range 8))))
         i 16]
    (if-not (= i 0)
      (do
        (set!
         (.-value (.getElementById js/document (str "" i)))
         (str i "_Unlocked_" (first x) "_Dead"))
        (recur (rest x) (dec i))))))




(defn getfirstvalue
  [id]
  (do
    (def a (.-value (.getElementById js/document (str "" id))))
    (let [c (string/split a #"_")]
      (if (= "Locked" (second c))
        (def a nil)
        (if-not (= "Live" (last c))
          (set-element-life (first c) "_Unlocked_" "_Live")
          (do
            (js/alert "Element is alive")
            (def a nil)))))))

(defn getsecondvalue
  [id]
  (do
    (def b (.-value (.getElementById js/document (str "" id))))
    (let [c (clojure.string/split b #"_")
          d (clojure.string/split a #"_")]
      (if (= "Locked" (second c))
        (def b nil)
        (if-not (= "Live" (last c))
          (do
            (set-element-life (first c) "_Unlocked_" "_Live")
            (if (= (nth c 2) (nth d 2))
              (do
                (set-element-life (first c) "_Locked_" "_Dead")
                (set-element-life (first d) "_Locked_" "_Dead"))
              (do
                (set-element-life (first c) "_Unlocked_" "_Dead")
                (set-element-life (first d) "_Unlocked_" "_Dead")))))))
    (def a nil)))

(def a nil)

(defn getValue
  [id]
  (if (game-over?)
    (do
      (js/alert "Game Over")
      (createboard))
    (if (= a nil)
      (getfirstvalue id)
      (getsecondvalue id))))
