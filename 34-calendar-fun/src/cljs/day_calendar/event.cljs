(ns day-calendar.event
  (:require-macros [day-calendar.macros :refer [p pp]]))

;; data

(defn event
  "Return `event` data type.
  https://www.lvguowei.me/post/named-arguments-in-clojure/
  https://clojure.org/guides/destructuring#_keyword_arguments"
  [& {:keys [name start-time end-time]}]
  {
    :id         36743 ;; make me a random uuid
    :name       name
    :start-time start-time
    :end-time   end-time})


(defn find-conflict
  "Compare two `event` data types to see if there scheduled times conflict.
   A conflict is found when the `second-events` `:start-time` begins before the
  `first-events` `:end-time`. When conflicts found - return vector with ID's of
  the conflicting pairs."
  [first-event second-event]
  (when (< (second-event :start-time) (first-event :end-time))
    [(first-event :id)  (second-event :id)]))


(defn find-conflicts
  "Find all calendar events that conflict with one another.
  When there are no conflicting calendar events this will return an empty list."
  [events]
  (if (> (count events) 1)
    (let [sorted-events (sort-by :start-time events)]
      (remove nil?
        (for [[x :as xs] (take (count sorted-events) (iterate next sorted-events))
               y xs
               :when (not= x y)]
          (find-conflict x y))))
    '()))
