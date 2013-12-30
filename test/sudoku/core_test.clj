(ns sudoku.core-test
  (:require [midje.sweet :refer :all]
            [sudoku.core :refer :all]))

(fact "first-of-column should return the top cell index"
  (first-of-column 0) => 0
  (first-of-column 1) => 1
  (first-of-column 40) => 4
  (first-of-column 80) => 8)

(fact "column-peers should return all indices of the column"
  (column-peers 40) => #{4 13 22 31 49 58 67 76})


(fact "first-of-row should return the left row index"
  (first-of-row 0) => 0
  (first-of-row 13) => 9
  (first-of-row 40) => 36
  (first-of-row 80) => 72)

(fact "row-peers should return all indexes of the row besides itself"
  (row-peers 7) => #{0 1 2 3 4 5 6 8})

(fact "first-of-region should return the index of the top-left cell of the region"
  (first-of-region 13) => 3
  (first-of-region 47) => 27
)

(fact "get-group-row-index returns zero 27 or 54 based on your index"
  (get-group-row-index 13 ) => 0
  (get-group-row-index 40 ) => 27)

(fact "region-peers should return the indices of the cells in the region"
  (region-peers 40 ) => #{30 31 32 39 41 48 49 50}
  (region-peers 26 ) => #{ 6  7  8 15 16 17 24 25}
  )

(fact "getting all peers gets all peers of a given cell"
  (get-all-peers 40) => #{4 13 22 30 31 32 36 37 38 39 41 42 43 44 48 49 50 58 67 76}
  (get-all-peers 40) => (contains (row-peers 40) :in-any-order :gaps-ok)
  (get-all-peers 40) => (contains (column-peers 40) :in-any-order :gaps-ok)
  (get-all-peers 40) => (contains (region-peers 40) :in-any-order :gaps-ok)
  )

(def tabula-rasa
  (->> (repeat 81 #{1 2 3 4 5 6 7 8 9})
      (apply vector))
)

(fact "setting the cell value returns a board with that value set"
  (set-value-of-cell tabula-rasa 0 1) => #(= (nth %  0) #{1})
  (set-value-of-cell tabula-rasa 0 1) => #(= (nth % 1) #{2 3 4 5 6 7 8 9})
  )

(fact "eliminating a possibility from a cell removes the value from the set"
  (eliminate-possible-value 1 tabula-rasa 9) => #(= (nth % 9) #{2 3 4 5 6 7 8 9})
  (eliminate-possible-value 1 tabula-rasa 20) => #(= (nth % 20) #{2 3 4 5 6 7 8 9}))

;(fact "trying to set the value of a cell that is not a possibility ")
