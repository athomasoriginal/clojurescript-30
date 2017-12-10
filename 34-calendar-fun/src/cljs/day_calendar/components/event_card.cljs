(ns day-calendar.components.event-card
  (:require        [hiccups.runtime]
                   [day-calendar.utils :refer [format-time]])
  (:require-macros [hiccups.core :refer [html]]
                   [day-calendar.macros :refer [p pp]]))


(defn event-card
  "Return HTML `event-card` component"
  [event]
  (let [name       (event :name)
        start-time (format-time (event :start-time))
        end-time   (format-time (event :end-time))]
    (html
      [:div.root-event
       [:p.event-title name]
       [:p.event-time  start-time " - " end-time]])))
