;; create main project namespace
(ns unreal-webcam-fun.core
  (:require        [cljs.core.async :as async])
  (:require-macros [unreal-webcam-fun.macros :refer [p pp]]
                   [cljs.core.async.macros :as m :refer [go]]))


;; Globals

(def video (.querySelector js/document ".player"))

(def canvas (.querySelector js/document ".photo"))

(def ctx (.getContext canvas "2d"))

(def strip (.querySelector js/document ".strip"))

(def snap (.querySelector js/document ".snap"))


;; Webcam helpers

(defn handle-promise
  [local-media-stream]
  (set! (.-src video) (.. js/window -URL (createObjectURL local-media-stream)))
  (.play video))


(defn handle-promise-err
  [err]
  (p "You did not give me permission to use the video"))


(defn get-video
  []
  (-> js/navigator
      .-mediaDevices
      (.getUserMedia #js {:video true :audio false})
      (.then handle-promise)
      (.catch handle-promise-err)))


(defn paint-to-canvas
  []
  (let [height (.-videoHeight video)
        width  (.-videoWidth video)]
    (set! (.-width canvas) width)
    (set! (.-height canvas) height)
    (go
      (loop []
        (async/<! (async/timeout 16))
        (.drawImage ctx video 0 0 width height)
        (recur)))))


;; JS global scope functions

(set! js/takePhoto (fn
                     []
                     (let [data (.toDataURL canvas "image/jpeg")
                           link (.createElement js/document "a")]
                       (set! (.-currentTime snap) 0)
                       (.play snap)
                       (set! (.-href link) data)
                       (.setAttribute link "download" "handsome")
                       (set! (.-innerHTML link) (str "<img src=" data " alt=\"Handsome man\" />"))
                       (.insertBefore strip link (.-firstChild strip)))))


(get-video)

(.addEventListener video "canplay" paint-to-canvas)
