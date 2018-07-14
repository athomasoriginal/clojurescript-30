(ns app.core
  (:require-macros [app.macros :refer [p pp]]))


;; Vars
;; -----------------------------------------------------------------------------

(def slider (.. js/document (querySelector ".items")))

;; State
;; -----------------------------------------------------------------------------

(def down? (atom false))
(def start-x (atom false))
(def scroll-left (atom false))


;; Event Handlers
;; -----------------------------------------------------------------------------

(defn handle-mousedown
  [e]
  (let [next-scroll-left (.. slider -scrollLeft)
        next-start-x     (- (.. e -pageX) (.. slider -offsetLeft))]
    (reset! down? true)
    (.. slider -classList (add "active"))
    (reset! start-x next-start-x)
    (reset! scroll-left next-scroll-left)))


(defn handle-mouseleave
  []
  (reset! down? false)
  (.. slider -classList (remove "active")))


(defn handle-mouseup
  []
  (reset! down? false)
  (.. slider -classList (remove "active")))


(defn handle-mousemove
  [e]
  (.. e (preventDefault))

  (when-not @down?
    (p "not down?"))

  (when @down?
    (let [x    (- (.. e -pageX) (.. slider -offsetLeft))
          walk (- x @start-x)]
      (reset! down? true)
      (set! (.. slider -scrollLeft) walk))))

;; Start
;; -----------------------------------------------------------------------------

(.. slider (addEventListener "mousedown" handle-mousedown))

(.. slider (addEventListener "mouseleave" handle-mouseleave))

(.. slider (addEventListener "mouseup" handle-mouseup))

(.. slider (addEventListener "mousemove" handle-mousemove))
