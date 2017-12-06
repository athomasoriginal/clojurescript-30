(ns day-calendar.components.time-option-list
  (:require        [day-calendar.components.time-option :refer [time-option]])
  (:require-macros [day-calendar.macros :refer [p pp]]))


(defn time-option-list
  "Create a series of time HTML option components - returns a string."
  [times]
  (apply str (map #(time-option %1) times)))
