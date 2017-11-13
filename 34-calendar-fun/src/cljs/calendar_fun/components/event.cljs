(ns calendar-fun.components.event
  (:require        [calendar-fun.utils.time :refer [format-time]])
  (:require-macros [calendar-fun.macros :refer [p pp]]))


(defn event-html
  "Wrap an event in an event HTML component - returns a string"
  [event]
  (str "<div class=\"event__card\">"
         "<p class=\"event__title\">" (event 0) "</p>"
         "<p class=\"event__time\">" (format-time ((event 1) 0)) " - " (format-time ((event 1) 1)) "</label>"
       "</div>"))


(defn events-html
  "Create a series of event HTML components - returns a string"
  [events]
  (apply str (map #(event-html %1) events)))
