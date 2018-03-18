;; create main project namespace
(ns app.core
  (:require-macros [app.macros :refer [p pp]]))

;; globals

(def duration 
 {:sec 1000 
  :min 60000 
  :hour 3600000})

(def time-unit->class 
 {:sec ".second-hand"
  :min ".min-hand"
  :hour ".hour-hand"})

;; Helpers

(defn- set-transform-style!
  "Sets the transform-style css property of the specified hand.
  
  css-class     - type: string - choice of :sec | :min | :hour
  transform-val - type: int."
  [css-class transform-val]
  (set! (.-transform (.-style (.querySelector js/document css-class))) transform-val))


(defn duration->degrees
  "Converts the duration provided into a degree.

  duration-type - type: key - choice of :sec | :min | :hour
  duration      - type: int."
  [duration-type duration]
  (cond 
    (or (= duration-type :sec) (= duration-type :min))
    (+ (* (/ duration 60) 360) 90)

    (= duration-type :hour)
    (+ (* (/ duration 12) 360) 90)

    :else
    nil))


(defn get-time
  "Returns the time-unit based on the current time provided."
  [now time-unit]
  (cond
    (= time-unit :sec) (.getSeconds now)
    (= time-unit :min) (.getMinutes now)
    :else              (.getHours now)))


(defn set-hand! 
  [time-unit]
  (let [now           (js/Date.)
        time          (get-time now time-unit)
        degrees       (duration->degrees time-unit time)
        transform-val (str "rotate(" degrees "deg)")
        css-class     (time-unit->class time-unit)]
    (set-transform-style! css-class transform-val)))


;; Start

; set all clock hands to the correct positions when app loads
(set-hand! :sec)
(set-hand! :min)
(set-hand! :hour)

; update clock hands at 1 sec, min and hour intervals
(js/setInterval #(set-hand! :sec) (:sec duration))
(js/setInterval #(set-hand! :min) (:min duration))
(js/setInterval #(set-hand! :hour) (:hour duration))
