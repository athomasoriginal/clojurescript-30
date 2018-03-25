;; create main project namespace
(ns ajax-type-ahead.core
  (:require-macros [ajax-type-ahead.macros :refer [p pp]]))


;; Globals

(def cities (atom nil))

(def endpoint "https://gist.githubusercontent.com/Miserlou/c5cd8364bf9b2420bb29/raw/2bf258763cdddd704f8ffd3ea9a3e81d25e2c6f6/cities.json")

(def suggestions (.querySelector js/document ".suggestions"))


;; Helpers

(defn find-matches 
 [word-to-match, cities]
 (filter #(re-seq (re-pattern (str "(?i)" word-to-match)) (.. % -city)) (.. cities -state)))


;; Event Handlers

(defn display-matches! 
 [e]
 (this-as this
   (let [matchArray (find-matches (.. this -value) cities)
         html       (clojure.string/join "" (map #(str "<li>" (.. % -city )  "</li>") matchArray))]
     (set! (.. suggestions -innerHTML) html))))


;; Start

(-> (js/fetch endpoint)
    (.then (fn [res] (.. res json)))
    (.then (fn [res] (reset! cities res))))

(let [search-input (.. js/document (querySelector  ".search"))]
  (.. search-input (addEventListener  "change" display-matches!))
  (.. search-input (addEventListener "keyup" display-matches!)))
