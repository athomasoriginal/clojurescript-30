;; create main project namespace
(ns multiple-checkboxes.core
  (:require        [goog.string :as gstr] goog.string.format)
  (:require-macros [multiple-checkboxes.macros :refer [p pp]]))


(def app-state (atom { :last-checked nil}))


;; functions to help us get DOM elements
(defn get-checkboxes []
  (.querySelectorAll js/document ".inbox input[type=\"checkbox\"]"))


;; protocol
(extend-type js/NodeList
   ISeqable
   (-seq [node-list] (array-seq node-list)))


(defn handle-check [e]
  (this-as this
    (when (and (.. e -shiftKey) (.. this -checked))
      (reduce (fn [in-between? checkbox]
                  (cond
                    ;; 1. checkbox user shift-clicked
                    (identical? checkbox this)
                    (do
                      (set! (.. checkbox -checked) true)
                      (not in-between?))

                    ;; 2. checkbox last checked
                    (identical? (get @app-state :last-checked) checkbox)
                    (do
                      (set! (.. checkbox -checked) true)
                      (not in-between?))

                    ;; 3. in between the last checked and shift-clicked checkbox
                    (true? in-between?)
                    (do
                      (set! (.. checkbox -checked) true)
                      true)

                    ;; 4.  outside of last checked and shift-clicked checkbox
                    :else false))
              false
              (get-checkboxes)))
    (swap! app-state assoc :last-checked this)))


(doseq [checkbox (get-checkboxes)]
  (.addEventListener checkbox "click" handle-check))
