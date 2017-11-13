;; create main project namespace
(ns calendar-fun.core
  (:require        [events.core :as events]
                   [calendar-fun.components.option :refer [time-select-options]]
                   [calendar-fun.components.event  :refer [events-html]]
                   [calendar-fun.utils.time        :refer [generate-times]])
  (:require-macros [calendar-fun.macros :refer [p pp]]))


;; Globals

(def app-state (atom []))

(def event-form (.querySelector js/document ".add-event-form"))

(def event-container (.querySelector js/document ".events__container"))

(def start-time-dropdown (.querySelector js/document "#event_start"))

(def end-time-dropdown (.querySelector js/document "#event_end"))


;; State helpers

(defn get-times
  "Returns the time item in an event vector."
  [events]
  (map #( %1 1) events))


;; HTML Helpers

(defn update-event-container
  [events]
  (set! (.-innerHTML event-container) (events-html events)))


(defn update-event-end-options
  [e]
  (p "sup"))


;; Event Helpers

(defn add-event
  [e]
  (.preventDefault e)

  (this-as this
    (let [event-name (.. this (querySelector "[name=event_name]") -value)
          start-time (js/parseInt (.. this (querySelector "[name=event_start_time]") -value))
          end-time   (js/parseInt (.. this (querySelector "[name=event_end_time]") -value))
          time       (conj [] start-time end-time)]
      ;; add new event to app-state
      (swap! app-state conj (events/make-event event-name time))

      ;; check for event conflicts
      (when (and (> (count @app-state) 2)
                 (events/find-event-conflicts (get-times @app-state)))
        (p "Oh no, it seems you have conflicting events"))

      ;; add new event to events list
      (update-event-container @app-state)

      ;; reset add event form
      (.reset this))))


;; Event listeners

(.addEventListener event-form "submit" add-event)

(.addEventListener start-time-dropdown "change" update-event-end-options)


;; Populate HTML

(set! (.-innerHTML start-time-dropdown) (time-select-options (generate-times)))

(set! (.-innerHTML end-time-dropdown) (time-select-options (generate-times)))

(update-event-container @app-state)
