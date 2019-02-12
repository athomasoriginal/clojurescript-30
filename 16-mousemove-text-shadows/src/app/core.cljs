;; create main project namespace
(ns app.core
  (:require        [goog.string :as gstr] goog.string.format)
  (:require-macros [app.macros :refer [p pp]]))

;; globals

(def hero (.querySelector js/document ".hero"))

(def text (.querySelector hero "h1"))

(def walk 100)


;; Helpers

(defn calc-x-walk
  [x walk width]
  (int (- (* (/ x width) walk) (/ walk 2))))

(defn calc-y-walk
  [y walk height]
  (int (- (* (/ y height) walk) (/ walk 2))))

(defn make-text-shadow
  [x y]
  (str x"px " y"px " "0 red"))

(defn get-xy
  [e this]
  (let [x (.-offsetX e)
        y (.-offsetY e)]
    (if (not= this (.-target e))
      [(+ x (.. e -target -offsetLeft)) (+ y (.. e -target -offsetTop))]
      [x y])))


;; Event handlers

(defn move-shadow
  [e]
  (this-as this
    (let [width  (.-offsetWidth hero)
          height (.-offsetHeight hero)
          [x y]  (get-xy e this)
          x-walk (calc-x-walk x walk width)
          y-walk (calc-y-walk y walk height)]
      (set! (.. text -style -textShadow) (make-text-shadow x-walk y-walk)))))


;; Event listeners

(.addEventListener hero "mousemove" move-shadow)
