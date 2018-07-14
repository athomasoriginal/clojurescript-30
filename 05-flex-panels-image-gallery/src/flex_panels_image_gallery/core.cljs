;; create main project namespace
(ns flex-panels-image-gallery.core
  (:require-macros [flex-panels-image-gallery.macros :refer [p pp]]))


;; helpers
(defn toggle-open [e]
  (this-as this
    (.. this -classList (toggle "open"))))

(defn toggle-active [e]
   (this-as this
     (when (.. e -propertyName (includes "flex"))
        (.. this -classList (toggle "open-active")))))


;; start 

(let [panels (.. js/document (querySelectorAll ".panel"))]
  (doseq [panel (array-seq panels)]
    (.addEventListener panel "click" toggle-open)
    (.addEventListener panel "transitionend" toggle-active)))


