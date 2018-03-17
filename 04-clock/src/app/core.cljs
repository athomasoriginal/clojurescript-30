;; create main project namespace
(ns app.core
  (:require-macros [app.macros :refer [p pp]]))

;; globals

(def duration {:sec 1000 :min 60000 :hour 3600000 })

;; Helpers

(defn- set-transform-style!
  "Sets the transofrm style of the specified hand
  
  hand          - type: string - choice of: .hour-hand | .min-hand | .sec-hand
  transform-val - type: int."
  [hand transform-val]
  (set! (.-transform (.-style (.querySelector js/document hand))) transform-val))

;; Event handlers

(defn set-seconds-hand []
  (let [now (js/Date.)
        seconds          (.getSeconds now)
        secondsAsDegrees (+ (* (/ seconds 60) 360) 90)
        transform-val    (str "rotate(" secondsAsDegrees "deg)")]
    (set-transform-style! ".hour-hand" transform-val)))


(defn set-minute-hand []
  (let [now (js/Date.)
        minutes (.getMinutes now)
        minutesAsDegrees (+ (* (/ minutes 60) 360) 90)
        transform-val (str "rotate(" minutesAsDegrees "deg)")]
    (set-transform-style! ".min-hand" transform-val)))


(defn set-hour-hand []
  (let [now (js/Date.)
        hours (.getHours now)
        hoursAsDegrees (+ (* (/ hours 12) 360) 90)
        transform-val (str "rotate(" hoursAsDegrees "deg)")]
    (set-transform-style! ".sec-hand" transform-val)))


;; start

(js/setInterval #(set-seconds-hand) (:second duration))
(js/setInterval #(set-minute-hand) (:minute duration))
(js/setInterval #(set-hour-hand) (:hour duration))
