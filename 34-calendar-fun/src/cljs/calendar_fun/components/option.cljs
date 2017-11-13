(ns calendar-fun.components.option
  (:require        [calendar-fun.utils.time :refer [format-time]])
  (:require-macros [calendar-fun.macros :refer [p pp]]))


(defn time-select-option
  "Wrap supplied time in time HTML option component - returns a string."
  [time]
  (str "<option value=\"" time "\"/>" (format-time time) "</option>"))


(defn time-select-options
  "Create a series of time HTML option components - returns a string."
  [times]
  (apply str (map #(time-select-option %1) times)))
