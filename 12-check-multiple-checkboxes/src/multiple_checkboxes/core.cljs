;; create main project namespace
(ns multiple-checkboxes.core
  (:require        [goog.string :as gstr] goog.string.format)
  (:require-macros [multiple-checkboxes.macros :refer [p pp]]))

(def appState (atom { :lastChecked nil}))

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
      (reduce (fn [inBetween element]
                  (cond
                    ;; 1. the shift-clicked checkbox
                    (identical? element this)
                    (do
                      (set! (.. element -checked) true)
                      (not inBetween))

                    ;; 2. the lastClicked checbox
                    (identical? (get @appState :lastChecked) element)
                    (do
                      (set! (.. element -checked) true)
                      (not inBetween))

                    ;; 3. automatically check the checkbox - its inBetween the north and south wall
                    (true? inBetween)
                    (do
                      (set! (.. element -checked) true)
                      true)

                    ;; 4.  Do nothing
                    :else false))
              false
              (get-checkboxes)))
    (swap! appState assoc :lastChecked this)))

(doseq [checkbox (get-checkboxes)]
  (.addEventListener checkbox "click" handle-check))
