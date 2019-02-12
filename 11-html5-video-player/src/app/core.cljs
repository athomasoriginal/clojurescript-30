;; create main project namespace
(ns app.core
  (:require        [goog.string :as gstr] goog.string.format)
  (:require-macros [app.macros :refer [p pp]]))

;; constants

(def player (.querySelector js/document ".player"))

(def video (.querySelector player ".viewer"))

(def toggle (.querySelector player ".toggle"))

(def progress (.querySelector player ".progress"))

(def progress-bar (.querySelector player ".progress__filled"))

(def skip-buttons (.querySelectorAll player "[data-skip]"))

(def ranges (.querySelectorAll player ".player__slider"))

;; state

(def app-state (atom {:mouse-down? false}))

;; protocols

(extend-type js/NodeList
   ISeqable
   (-seq [node-list] (array-seq node-list)))


;; event functions

(defn toggle-mouse-down []
  (let [current-mouse-down (get @app-state :mouse-down?)]
    (swap! app-state assoc :mouse-down? (not current-mouse-down))))


(defn toggle-play [e]
  (if (.-paused video)
    (.play video)
    (.pause video)))


(defn toggle-play-btn-icon [e]
  (this-as this
    (let [icon (if (.-paused this) "►" "❚ ❚")]
      (set! (.-textContent toggle) icon))))


(defn skip [e]
  (this-as this
    (let [skip-count      (.. this -dataset -skip)
          skip-count-str  (js/parseFloat skip-count)
          next-skip-count (+ (.-currentTime video) skip-count-str)]
      (set! (.-currentTime video) next-skip-count))))


(defn handle-range-update [e]
  (this-as this
    (let [volume-slider? (= (.-name this) "volume")
          playback-rate? (= (.-name this) "playbackRate")]
      (when volume-slider?
        (set! (.-volume video) (.-value this)))
      (when playback-rate?
        (set! (.-playbackRate this) (.-value this))))))


(defn handle-progress [e]
  (let [percent         (* (/ (.-currentTime video) (.-duration video)) 100)
        next-flex-basis (str percent "%")]
      (p next-flex-basis)
    (set! (.. progress-bar -style -flexBasis) next-flex-basis)))


(defn scrub [e]
    (let [scrub-time (* (/ (.-offsetX e) (.-offsetWidth progress)) (.-duration video))]
      (set! (.-currentTime video) scrub-time)))


;; event listeners

(.addEventListener video "click" toggle-play)

(.addEventListener video "play" toggle-play-btn-icon)

(.addEventListener video "pause" toggle-play-btn-icon)

(.addEventListener video "timeupdate" handle-progress)

(.addEventListener progress "click" scrub)

(.addEventListener progress "mousemove" #(and (get @app-state :mouse-down?) scrub))

(.addEventListener progress "mousedown" toggle-mouse-down)

(.addEventListener progress "mouseup" toggle-mouse-down)

(.addEventListener toggle "click" toggle-play)

(doseq [btn skip-buttons]
  (.addEventListener btn "click" skip))

(doseq [range ranges]
  (.addEventListener range "change" handle-range-update))

(doseq [range ranges]
  (.addEventListener range "mousemove" handle-range-update))
