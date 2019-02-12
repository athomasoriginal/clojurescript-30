;; create main project namespace
(ns app.core
  (:require        [goog.string :as gstr] goog.string.format)
  (:require-macros [app.macros :refer [p pp]]))


;; globals

(def node-list (.querySelectorAll js/document "[data-time]"))


;; protocols

(extend-type js/NodeList
   ISeqable
   (-seq [node-list] (array-seq node-list)))


;; helpers

(defn get-dataset-time
  [node]
  (.. node -dataset -time))


(defn split-time-string
  "Takes a string like 5:45, splits it, and returns a vector of numbers lie
  [5 45]"
  [timestring]
  (map #(js/parseInt %1) (clojure.string/split timestring #":")))


;; Helpers

(defn get-seconds-from-node
  [acc node]
  (let [time-string   (get-dataset-time node)
        [mins secs]   (split-time-string time-string)
        total-seconds (+ (* mins 60) secs)]
    (+ acc total-seconds)))


(defn get-total-seconds-from-nodes
  [nodelist]
  (reduce get-seconds-from-node 0 node-list))


(defn get-hours-min-seconds
  [seconds]
  (let [hours       (Math/floor (/ seconds 3600))
        mins        (Math/floor (/ (mod seconds 3600) 60))
        secondsLeft (mod (mod seconds 3600) 60)]
    [hours mins secondsLeft]))


(pp (get-hours-min-seconds (get-total-seconds-from-nodes node-list)))
