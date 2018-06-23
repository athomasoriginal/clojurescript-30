(ns app.core
  (:require-macros [app.macros :refer [p pp]]))

;; Globals
;; -----------------------------------------------------------------------------


(def speed (-> js/document (.querySelector ".speed")))

(def bar (-> speed (.querySelector ".speed-bar")))


;; Event Handlers
;; -----------------------------------------------------------------------------

(defn move-bar!
  [e]
  (this-as this
    (let [video         (-> js/document (.querySelector ".flex"))
          y             (- (-> e .-pageY) (-> this .-offsetTop))
          percent       (/ y (-> this .-offsetHeight))
          min           0.4
          max           4
          height        (-> js/Math (.round (* percent 100)))
          height-attr   (str height "%")
          playback-rate (* percent (+ (- max min) min))
          playback-attr (str (-> playback-rate (.toFixed 2)) "x")]
      (set! (-> bar .-style .-height) height-attr)
      (set! (-> bar .-textContent) playback-attr)
      (set! (.-playbackRate video) playback-rate))))


;; Start
;; -----------------------------------------------------------------------------

(doto speed
  (.addEventListener "mousemove" move-bar!))
