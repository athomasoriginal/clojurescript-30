(ns day-calendar.components.time-option
  (:require        [hiccups.runtime]
                   [day-calendar.utils  :refer [format-time]])
  (:require-macros [hiccups.core :refer [html]]
                   [day-calendar.macros :refer [p pp]]))


(defn time-option
  "Return HTML `time-option` component"
  [time]
  (html
    [:option {:value time} (format-time time)]))
