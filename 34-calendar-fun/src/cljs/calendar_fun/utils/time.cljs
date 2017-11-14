;; create main project namespace
(ns calendar-fun.utils.time
  (:require-macros [calendar-fun.macros :refer [p pp]]))

;; Globals

(def fifteen-minutes 0.25)


;; Utils

(defn inc-15-min
  "Add .25 (fifteen minutes) to each time provided."
  [time]
  (+ time fifteen-minutes))


(defn generate-times
  "Generates a vector of times.
  For example, `[9 9.25 9.5 ...]`"
  []
  (take 33 (iterate inc-15-min  9)))


(defn match-time
  "Capture the hours and minutes for the time provided - returns a vector."
  [time]
  (let [match (re-matches #"([0-9]{1,2})([.][0-9]{1,2})" time)]
    (if (nil? match)
      []
      match)))


(defn format-time
  "Format the time provided into a human friendly time format.
  For example, we store the time 9:15 as 9.25. This will convert 9.25 to 9:15."
  [time]
  (let [time-string (str time)
        [original-time hour minutes] (match-time time-string)]
    (cond
      (nil? original-time)
      (str time-string ":00")

      (= ".25" minutes)
      (str hour ":15")

      (= ".5" minutes)
      (str hour ":30")

      :else
      (str hour ":45"))))
