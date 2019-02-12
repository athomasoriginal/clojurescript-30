;; create main project namespace
(ns app.core
  (:require        [goog.string :as gstr] goog.string.format)
  (:require-macros [app.macros :refer [p pp get-attr]]))

;; Globals

(def triggers (.querySelectorAll js/document "a"))


(def highlight (.createElement js/document "span"))

;; utils

(defn make-hw-val
  "hw stands for Height and Width"
  [coordinate]
  (str coordinate"px"))


(defn make-trans-val
  [left top]
  (str "translate(" left"px ," top"px)"))

; (defn hello
;   [symbol el]
;   (p symbol)
;   (p (-> el symbol)))
;
(pp (macroexpand '(.-style hello)))


;; Event handlers

(defn highlight-link
  [e]
  (this-as this
    (let [link-coords (.getBoundingClientRect this)
          width       (.-width link-coords)
          height      (.-height link-coords)
          left        (+ (.-left link-coords) (.-scrollX js/window))
          top         (+ (.-top link-coords) (.-scrollY js/window))]

      (doto (-> highlight .-style)
            (set! -width (make-hw-val width))
            (set! -height (make-hw-val height))
            (set! -transform (make-trans-val left top))))))


;; Setup

(-> highlight .-classList (.add "highlight"))


(-> js/document .-body (.append highlight))


(doseq [trigger (array-seq triggers)]
  (.addEventListener trigger "mouseenter" highlight-link))
