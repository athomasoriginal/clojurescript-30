;; create main project namespace
(ns flex-panels-image-gallery.core
  (:require        [goog.string :as gstr] goog.string.format)
  (:require-macros [flex-panels-image-gallery.macros :refer [p pp]]))

(def panels (.. js/document (querySelectorAll ".panel")))

;; protocol
(extend-type js/NodeList
   ISeqable
   (-seq [node-list] (array-seq node-list)))

;; helpers
(defn toggle-open [e]
  (this-as this
    (.. this -classList (toggle "open"))))

(defn toggle-active [e]
   (this-as this
     (when (.. e -propertyName (includes "flex"))
          (.. this -classList (toggle "open-active")))))

;; start
(doseq [panel panels]
  (.addEventListener panel "click" toggle-open))

(doseq [panel panels]
  (.addEventListener panel "transitionend" toggle-active))
