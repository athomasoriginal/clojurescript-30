;; create main project namespace
(ns app.core
  (:require        [goog.string :as gstr] goog.string.format)
  (:require-macros [app.macros :refer [p pp]]))


;; helpers

(defn strip-article
  [word]
  (clojure.string/replace word #"(?i)a |the |an " ""))

(defn make-band-listItem
  [band-name]
  (str "<li>"band-name"</li>"))

(defn make-band-list
  [bands]
  (apply str (map make-band-listItem bands)))


;; comparator

(defn alphabetically
  [a b]
  (compare (strip-article  a)
           (strip-article  b)))


;; event listener

(set! (.-innerHTML (.querySelector js/document "#bands")) (make-band-list (sort alphabetically js/bands)))
