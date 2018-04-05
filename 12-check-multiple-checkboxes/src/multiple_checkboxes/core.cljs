;; create main project namespace
(ns multiple-checkboxes.core
  (:require-macros [multiple-checkboxes.macros :refer [p pp]]))


(def app-state (atom { :last-checked nil}))


;; Functions to help us get DOM elements
(defn get-checkboxes []
  (.. js/document (querySelectorAll ".inbox input[type=\"checkbox\"]")))


;; Protocol
(extend-type js/NodeList
   ISeqable
   (-seq [node-list] (array-seq node-list)))


;; Helper
(defn select-checkbox?
  [in-between? checkbox]
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



;; Event Handler

(defn handle-check [e]
  (this-as this
    (when (and (.. e -shiftKey) (.. this -checked))
      (reduce select-checkbox? false (get-checkboxes)))
    (swap! app-state assoc :last-checked this)))


;; start

(doseq [checkbox get-checkboxes]
  (.addEventListener checkbox "click" handle-check))
