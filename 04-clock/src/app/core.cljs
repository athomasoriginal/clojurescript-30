;; create main project namespace
(ns app.core
  (:require-macros [app.macros :refer [p pp]]))

;; globals

(def everySecond 1000)

(def everyMinute 60000)

(def everyHour 3600000)

(def second-hand (.querySelector js/document ".hour-hand"))

(def minute-hand (.querySelector js/document ".min-hand"))

(def hour-hand (.querySelector js/document ".second-hand"))


;; Event handlers

(defn set-seconds-hand []
  (let [now (js/Date.)
        seconds (.getSeconds now)
        secondsAsDegrees (+ (* (/ seconds 60) 360) 90)
        transformVal (str "rotate(" secondsAsDegrees "deg)" )]
    (set! (.-transform (.-style second-hand)) transformVal)))


(defn set-minute-hand []
  (let [now (js/Date.)
        minutes (.getMinutes now)
        minutesAsDegrees (+ (* (/ minutes 60) 360) 90)
        transformVal (str "rotate(" minutesAsDegrees "deg)" )]
    (set! (.-transform (.-style minute-hand)) transformVal)))


(defn set-hour-hand []
  (let [now (js/Date.)
        hours (.getHours now)
        hoursAsDegrees (+ (* (/ hours 12) 360) 90)
        transformVal (str "rotate(" hoursAsDegrees "deg)" )]
    (set! (.-transform (.-style hour-hand)) transformVal)))



;; start

(js/setInterval #(set-seconds-hand) everySecond)
(js/setInterval #(set-minute-hand) everySecond)
(js/setInterval #(set-hour-hand) everySecond)
