;; create main project namespace
(ns calendar-fun.core
  (:require        [events.core :as events]
                   [calendar-fun.components.option :refer [time-select-options]]
                   [calendar-fun.components.event  :refer [events-html]]
                   [calendar-fun.utils.time        :refer [create-time-range]])
  (:require-macros [calendar-fun.macros :refer [p pp]]))


;; Globals

(def app-state (atom []))

(def event-form (.querySelector js/document ".add-event-form"))

(def event-container (.querySelector js/document ".root-event-container"))

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


(defn update-event-end-dropdown
  "Re-populate event end dropdown with updated list of time options."
  [e]
  (let [start-time (+ (js/parseFloat (.-value start-time-dropdown)) 0.25)]
    (set! (.-innerHTML end-time-dropdown) (time-select-options (create-time-range start-time)))))



;; Event Helpers

(defn add-event
  [e]
  (.preventDefault e)

  (this-as this
    (let [event-name (.. this (querySelector "[name=event_name]") -value)
          start-time (js/parseFloat (.-value start-time-dropdown))
          end-time   (js/parseFloat (.-value end-time-dropdown))
          time       (conj [] start-time end-time)]

      ;; add new event to app-state
      (swap! app-state conj (events/make-event event-name time))

      ;; check for event conflicts
      (when (and (>= (count @app-state) 2)
                 (>= (count (events/find-event-conflicts (get-times @app-state))) 1))
        (p "Oh no, it seems you have conflicting events")
        (pp (events/find-event-conflicts (get-times @app-state))))

      ;; add new event to events list
      (update-event-container @app-state)

      ;; reset add event form
      (.reset this)

      (set! (.-innerHTML end-time-dropdown) (time-select-options (create-time-range 9.25))))))




;; Event listeners

(.addEventListener event-form "submit" add-event)

(.addEventListener start-time-dropdown "change" update-event-end-dropdown)


;; Populate HTML

(set! (.-innerHTML start-time-dropdown) (time-select-options (create-time-range)))

(set! (.-innerHTML end-time-dropdown) (time-select-options (create-time-range 9.25)))

(update-event-container @app-state)
