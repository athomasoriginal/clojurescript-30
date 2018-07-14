;; create main project namespace
(ns slide-in-on-scroll.core
  (:require        [goog.string :as gstr] goog.string.format)
  (:require-macros [slide-in-on-scroll.macros :refer [p pp]]))


;; Constants

(def slide-images (.querySelectorAll js/document ".slide-in"))


;; Protocols

(extend-type js/NodeList
   ISeqable
   (-seq [node-list] (array-seq node-list)))


;; Event handlers

(defn check-slide
  [e]
  (doseq [image slide-images]
    (let [scroll-y           (.-scrollY js/window)
          win-inner-height   (.-innerHeight js/window)
          image-height       (.-height image)
          image-offsetTop    (.-offsetTop image)
          slide-in-at        (- (+ scroll-y win-inner-height) (/ image-height 2))
          image-bottom       (+ image-offsetTop image-height)
          half-shown?        (> slide-in-at image-offsetTop)
          not-scrolled-past? (< scroll-y image-bottom)]
        (if (and half-shown? not-scrolled-past?)
          (.. image -classList (add "active"))
          (.. image -classList (remove "active"))))))


;; Event Listeners

(.addEventListener js/window "scroll" (.debounce js/window check-slide))
