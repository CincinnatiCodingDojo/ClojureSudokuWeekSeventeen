(ns sudoku.core
  (:gen-class))



(defn first-of-column [index]
  (mod index 9))

(defn column-peers [index]
  (-> (range (first-of-column index) 81 9)
      set
      (disj index)))

(defn first-of-row [index]
  (* 9 (quot index 9))
)

(defn row-peers [index]
  (let [firstIndex (first-of-row index)]
    (-> (range firstIndex (+ firstIndex 9))
        set
        (disj index)) ; for the HM people.
  )
)

(defn get-group-row-index [index]
  (- index (mod index 27))
)

(defn first-of-region [index]
  (+ (get-group-row-index index) (* (quot ( first-of-column index) 3) 3) )
)

(defn region-peers [index]
  (let [grid-index (first-of-region index)]
    (-> (map (partial + grid-index) [0 1 2 9 10 11 18 19 20])
        set
        (disj index))
    )

)
