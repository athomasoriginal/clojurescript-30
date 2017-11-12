(ns calendar-fun.core
  (:gen-class))


;; Helpers

(defn events?
  "Check if `events` vector contains more than one event."
  [events]
  (> (count events) 1))


(defn compare-events
  "Compare second events start time to the first events end time.
  When second event start time is less than first event end time this results
  in a conflict.  Conflict returns:  `[ [1 4] [2 3] ]`.  No conflicts returns
  nil"
  [x y]
  (let [[s1 e1] x
        [s2 e2] y]
      (when (< s2 e1)
        (conj [] x y))))


;; Core

(defn find-event-conflicts
  "Find all pairs of calendar events that conflict with one another.
  When conficts are found, this returns a list of vectors [ [ [1 3] [2 4] ] ... ].
  When no conflicts are found, return empty list."
  [events]
  (when events?
    (remove nil?
      (for [[x :as xs] (take (count (sort events)) (iterate next (sort events)))
             y xs
             :when (not= x y)]
        (compare-events x y)))))


;; Start

(defn -main
  "Ya double-booked, son!"
  [& args])
