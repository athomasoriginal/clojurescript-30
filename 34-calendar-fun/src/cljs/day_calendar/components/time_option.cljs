(ns day-calendar.components.time-option
  (:require        [day-calendar.utils  :refer [format-time]])
  (:require-macros [day-calendar.macros :refer [p pp]]))


(defn time-option
  "Wrap supplied time in time HTML option component - returns a string."
  [time]
  (str "<option value=\"" time "\"/>" (format-time time) "</option>"))
