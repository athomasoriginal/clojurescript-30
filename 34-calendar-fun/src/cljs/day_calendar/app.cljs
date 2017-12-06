(ns day-calendar.app
  (:require  [day-calendar.actions                     :refer [add-event]]
             [day-calendar.utils                       :refer [time-range find-conflicts]]
             [day-calendar.components.event-card-list  :refer [event-card-list]]
             [day-calendar.components.time-option-list :refer [time-option-list]])
  (:require-macros [day-calendar.macros :refer [p pp]]))


; state

(def app-state (atom []))


;; selectors

(def add-event-form (.querySelector js/document ".add-event-form"))

(def event-container (.querySelector js/document ".root-event-container"))

(def start-time-dropdown (.querySelector js/document "#event_start"))

(def end-time-dropdown (.querySelector js/document "#event_end"))

;; update dom

(defn update-event-container!
  [events]
  (set! (.-innerHTML event-container) (event-card-list events)))


(defn update-event-end-dropdown!
  "Re-populate event end dropdown with updated list of time options."
  [e]
  (let [start-time (+ (js/parseFloat (.-value start-time-dropdown)) 0.25)]
    (set! (.-innerHTML end-time-dropdown) (time-option-list (time-range start-time)))))


(defn handle-add-event!
  [e]
  (.preventDefault e)

  (this-as this
    (let [event-name (.. this (querySelector "[name=event_name]") -value)
          start-time (js/parseFloat (.-value start-time-dropdown))
          end-time   (js/parseFloat (.-value end-time-dropdown))
          new-event  (add-event :name event-name :start-time start-time :end-time end-time)]

      ;; add new event to app-state
      (swap! app-state conj new-event)

      ;; check for event conflicts
      (when (and (>= (count @app-state) 2)
                 (>= (count (find-conflicts @app-state)) 1))
        (p "Oh no, it seems you have conflicting events")
        (pp (find-conflicts @app-state)))

      ;; add new event to events list
      (update-event-container! @app-state)

      ;; reset add event form
      (.reset this)

      (set! (.-innerHTML end-time-dropdown) (time-option-list (time-range 9.25))))))


;; Register event listeners

(.addEventListener add-event-form "submit" handle-add-event!)

(.addEventListener start-time-dropdown "change" update-event-end-dropdown!)


;; Init

(set! (.-innerHTML start-time-dropdown) (time-option-list (time-range)))

(set! (.-innerHTML end-time-dropdown) (time-option-list (time-range 9.25)))

(update-event-container! @app-state)
