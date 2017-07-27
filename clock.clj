(ns noob2pro.dhiren.clock)

(defn sin-deg
  "Takes x as degree and returns sin x"
  [x]
  (Math/sin (Math/toRadians x)))

(defn cos-deg
  "Takes x as degree and returns cos x"
  [x]
  (Math/cos (Math/toRadians x)))


(defn degrees-min
  "calculates angle of the minute hand"
  [x]
  (* x 6))

(defn degrees-hour
  "calculates angle of the hour hand"
  [x]
  (* x 30))

(defn min-hand
  [x]
  (let [a  (degrees-min (second x))
        b (- (degrees-min (second x)) 90)
        c (- (degrees-min (second x)) 180)
        d (- (degrees-min (second x)) 270)]
    (if (<= (second x) 15)
      [(+ 10 (* 9 (sin-deg a))) (- 10 (* 9 (cos-deg a)))]
      (if (<= (second x) 30)
        [(+ 10 (* 9 (cos-deg b))) (+ 10 (* 9 (sin-deg b)))]
        (if (<= (second x) 45)
          [(- 10 (* 9 (sin-deg c))) (+ 10 (* 9 (cos-deg c)))]
          [(- 10 (* 9 (cos-deg d))) (- 10 (* 9 (sin-deg d)))] )))))





(defn hour-hand1
  [x y]
  (let [a  x
        b (- x 90)
        c (- x 180)
        d (- x 270)]
    (if (<= x 90)
      [(+ 10 (* y (sin-deg a))) (- 10 (* y (cos-deg a)))]
      (if (<= x 180)
        [(+ 10 (* y (cos-deg b))) (+ 10 (* y (sin-deg b)))]
        (if (<= x 270)
          [(- 10 (* y (sin-deg c))) (+ 10 (* y (cos-deg c)))]
          [(- 10 (* y (cos-deg d))) (- 10 (* y (sin-deg d)))] )))))


(defn hour-hand
  [x]
  (let [a (degrees-hour (mod (first x) 12))
        b (* 0.5 (second x))
        c (+ a b)]
    (hour-hand1 c 6)))

(defn Input1
  "for processing input"
  [fname]
  (let [x (slurp (str "resources/" fname))
        y (clojure.string/split x #"[\s]")
        z (rest (map #(Integer/parseInt %) y))]
    #_(map #(Integer/parseInt %) y)
    (partition 2 z)))

(defn -main
  [& args]
  (let [a (Input1 "clock.txt")]
    (map vector (map hour-hand a)
         (map min-hand a))
    ))
