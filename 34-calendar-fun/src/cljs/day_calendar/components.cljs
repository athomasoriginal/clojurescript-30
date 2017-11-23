(ns day-calendar.components
  (:require        [day-calendar.utils :refer [format-time]])
  (:require-macros [day-calendar.macros :refer [p pp]]))

(defn event-card
  "Wrap an event in an event HTML component - returns a string"
  [event]
  (str "<div class=\"root-event\">"
         "<p class=\"event-title\">" (event :name) "</p>"
         "<p class=\"event-time\">" (format-time (event :start-time)) " - " (format-time (event :end-time)) "</label>"
       "</div>"))


(defn event-cards
  "Create a series of event HTML components - returns a string"
  [events]
  (apply str (map #(event-card %1) events)))


(defn time-option
  "Wrap supplied time in time HTML option component - returns a string."
  [time]
  (str "<option value=\"" time "\"/>" (format-time time) "</option>"))


(defn time-options
  "Create a series of time HTML option components - returns a string."
  [times]
  (apply str (map #(time-option %1) times)))
