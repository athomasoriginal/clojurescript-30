(ns day-calendar.components.event-card-list
  (:require        [day-calendar.components.event-card :refer [event-card]])
  (:require-macros [day-calendar.macros :refer [p pp]]))


(defn event-card-list
  "Create a series of event HTML components - returns a string"
  [events]
  (apply str (map #(event-card %1) events)))
