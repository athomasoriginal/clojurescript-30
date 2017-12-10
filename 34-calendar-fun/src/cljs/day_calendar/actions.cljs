(ns day-calendar.actions
  (:require-macros [day-calendar.macros :refer [p pp]]))

(defn add-event
  "Return `event` data type.
  https://www.lvguowei.me/post/named-arguments-in-clojure/
  https://clojure.org/guides/destructuring#_keyword_arguments"
  [& {:keys [name start-time end-time]}]
  {
    :id         (random-uuid)
    :name       name
    :start-time start-time
    :end-time   end-time})
