(ns Environment
  (:require clojure.contrib.combinatorics))

(def *board-size* 4)

;The "board" will be a seq of 0's and 1's representing clean and dirty.
(def *state*
  {:agent-location 0
   :environment [0]})

(defn build-all-possible-boards []
  (clojure.contrib.combinatorics/selections [0 1] *board-size*))

(defn build-all-possible-states []
  (clojure.contrib.combinatorics/cartesian-product
   (Environment/build-all-possible-boards)
   (range 0 Environment/*board-size*)))

(defn get-board []
  (*state* :environment))

(defn get-agent-location []
  (*state* :agent-location))

(defn update-state [board agent-location]
  (def *state*
    {:agent-location agent-location
     :environment board}))

(defn update-agent-location [location]
  (update-state (*state* :environment) location))

;STOPPED HERE. HTF do I update sequence given an index and value?
(defn clean []
  (update-state
   (*state* :agent-location)
   (*state* :environment)))

(defn is-location-dirty? [location]
  (if (== 0 (nth (get-board) location))
    true
    false))

(defn count-clean-squares[]
  "Returns the number of clean squares in the environment."
  (reduce + (get-board)))


