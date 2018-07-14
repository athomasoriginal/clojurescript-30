(ns app.core
  (:require-macros [app.macros :refer [p pp]]))

;; Globals
;; -----------------------------------------------------------------------------

; will hold the setInterval reference
(defonce countdown (atom 0))

(def timer-display (-> js/document (.querySelector ".display__time-left")))

(def end-time-display (-> js/document (.querySelector ".display__end-time")))

(def buttons (-> js/document (.querySelectorAll "[data-time]")))

; declaring here as an example of how to declare a name earlier so we can use
; it earlier than where it is defined
(declare display-time-left)

(declare display-end-time)


;; Event Handlers
;; -----------------------------------------------------------------------------

(defn timer
  "Provided seconds will subtract 1 second every second"
  [seconds]
  (js/clearInterval @countdown)
  (let [now              (-> js/Date (.now))
        then             (+ now (* seconds 1000))
        handle-countdown (fn []
                           (let [seconds-left (as-> (-> js/Date (.now)) time
                                                    (- then time)
                                                    (/ time 1000)
                                                    (-> js/Math (.round time)))
                                 stop-timer?  (<= seconds-left 0)]
                              (when stop-timer?
                                (js/clearInterval @countdown))

                              (display-time-left seconds-left)))]

    (display-time-left seconds)
    (display-end-time then)

    (reset! countdown (js/setInterval handle-countdown 1000))))


(defn display-time-left
  "Display time remaining as ??:??"
  [seconds]
  (let [minutes           (-> js/Math (.floor (/ seconds 60)))
        remainder-seconds (mod seconds 60)
        format-seconds?   (< remainder-seconds 10)
        formatted-seconds (if format-seconds? "0" (str ""))
        display           (str minutes ":" formatted-seconds remainder-seconds)]
    (set! (-> timer-display .-textContent) display)))


(defn display-end-time
  "Display the expected endtime as as `Be back at ??:??`"
  [timestamp]
  (let [end     (js/Date. timestamp)
        _ (p "sad pony")
        _ (p end)
        hour    (-> end (.getHours))
        minutes (-> end (.getMinutes))
        display (str "Be back at " hour ":" minutes)]
    (set! (-> end-time-display .-textContent) display)))


(defn start-timer []
  (this-as this
    (timer (js/parseInt (-> this .-dataset .-time)))))


;; start
;; -----------------------------------------------------------------------------


(doseq [button (array-seq buttons)]
    (.addEventListener button "click" start-timer))
