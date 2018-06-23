(ns app.core
  (:require [goog.object])
  (:require-macros [app.macros :refer [p pp]]))


;; Event Handlers
;; -----------------------------------------------------------------------------


(defn handle-enter
  []
  (this-as this
    (let [background      (-> js/document (.querySelector ".dropdownBackground"))
          nav             (-> js/document (.querySelector ".top"))
          dropdown        (-> this (.querySelector ".dropdown"))
          dropdown-coords (-> dropdown (.getBoundingClientRect))
          nav-coords      (-> nav (.getBoundingClientRect))
          ;; TODO there should be a better way to do this in clojure land
          coords          {:height (goog.object/get dropdown-coords "height")
                           :width  (goog.object/get dropdown-coords "width")
                           :top    (- (goog.object/get dropdown-coords "top")
                                      (goog.object/get nav-coords "top"))
                           :left   (goog.object/get dropdown-coords "left")}]


      (-> this .-classList (.add "trigger-enter"))
      (js/setTimeout
        (fn []
          (when (-> this .-classList (.contains "trigger-enter"))
            (-> this .-classList (.add "trigger-enter-active")), 150)))

      (-> background .-classList (.add "open"))
      (-> background .-style (.setProperty "width" (str (:width coords) "px")))
      (-> background .-style (.setProperty "height" (str (:height coords) "px")))
      (-> background .-style (.setProperty "transform" (str "translate(" (:left coords) "px, " (:top coords) "px" ")"))))))


(defn handle-leave
  []
  (this-as this
    (let [background (-> js/document (.querySelector ".dropdownBackground"))]
      (-> this .-classList (.remove "trigger-enter" "trigger-enter-active"))
      (-> background .-classList (.remove "open")))))


;; Start
;; -----------------------------------------------------------------------------


(let [triggers (-> js/document (.querySelectorAll ".cool > li"))]
  (doseq [trigger (array-seq triggers)]
    (-> trigger (.addEventListener "mouseenter" handle-enter)))

  (doseq [trigger (array-seq triggers)]
    (-> trigger (.addEventListener "mouseleave" handle-leave))))
