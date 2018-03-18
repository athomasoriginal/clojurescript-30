;; create main project namespace
(ns app.core
  (:require        [cljs.spec.alpha :as s])
  (:require-macros [app.macros :refer [p pp]]))

;; specs

(s/def ::time-unit #{:sec :min :hour})

(s/def ::date inst?)


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
  "Sets the transform-style css property of the specified hand."
  [css-class transform-val]
  (set! (.-transform (.-style (.querySelector js/document css-class))) transform-val))

(s/fdef set-transform-style!
  :args (s/and (s/cat :css-class ::time-unit :transform-val int?))
  :ret  nil?)


(defn duration->degrees
  "Converts the duration provided into a degree."
  [duration-type duration]
  (cond 
    (or (= duration-type :sec) (= duration-type :min))
    (+ (* (/ duration 60) 360) 90)

    (= duration-type :hour)
    (+ (* (/ duration 12) 360) 90)

    :else
    nil))

(s/fdef duration->degrees
  :args (s/and (s/cat :duration-type ::time-unit :duration int?))
  :ret  int?)


(defn get-time
  "Returns the time-unit based on the current time provided."
  [now time-unit]
  (cond
    (= time-unit :sec) (.getSeconds now)
    (= time-unit :min) (.getMinutes now)
    :else              (.getHours now)))

(s/fdef get-time
  :args (s/and (s/cat :now ::date :time-unit ::time-unit))
  :ret  inst?)


(defn set-hand!
  "Sets the clock hand the the correct degree.
  
  time-unit - type: key Choice of :sec | :min | :hour"
  [time-unit]
  (let [now           (js/Date.)
        time          (get-time now time-unit)
        degrees       (duration->degrees time-unit time)
        transform-val (str "rotate(" degrees "deg)")
        css-class     (time-unit->class time-unit)]
    (set-transform-style! css-class transform-val)))

(s/fdef set-hand!
  :args (s/cat :time-unit ::time-unit)
  :ret  nil?)


;; Start

; set all clock hands to the correct positions when app loads
(set-hand! :sec)
(set-hand! :min)
(set-hand! :hour)

; update clock hands at 1 sec, min and hour intervals
(js/setInterval #(set-hand! :sec) (:sec duration))
(js/setInterval #(set-hand! :min) (:min duration))
(js/setInterval #(set-hand! :hour) (:hour duration))
