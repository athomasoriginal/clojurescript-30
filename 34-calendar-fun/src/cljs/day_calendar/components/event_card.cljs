(ns day-calendar.components.event-card
  (:require        [day-calendar.utils :refer [format-time]])
  (:require-macros [day-calendar.macros :refer [p pp]]))


(defn event-card
  "Wrap an event in an event HTML component - returns a string"
  [event]
  (str "<div class=\"root-event\">"
         "<p class=\"event-title\">" (event :name) "</p>"
         "<p class=\"event-time\">" (format-time (event :start-time)) " - " (format-time (event :end-time)) "</label>"
       "</div>"))
