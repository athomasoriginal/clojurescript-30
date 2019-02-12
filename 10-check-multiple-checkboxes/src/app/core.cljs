(ns app.core
  (:require-macros [app.macros :refer [p pp]]))


(def app-state (atom { :last-checked nil}))


;; DOM helper

(defn get-checkboxes []
  (.. js/document (querySelectorAll ".inbox input[type=\"checkbox\"]")))


;; Event Handler

(defn handle-check [e]
  (this-as this
    (when (and (.. e -shiftKey) (.. this -checked))
      (let [select-checkbox? (fn [in-between? checkbox]
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
                                 :else false))]
        (reduce select-checkbox? false (array-seq (get-checkboxes)))))
    (swap! app-state assoc :last-checked this)))

;; start

(doseq [checkbox (array-seq (get-checkboxes))]
  (.addEventListener checkbox "click" handle-check))
