(ns html5-canvas.core
  (:require        [goog.string :as gstr] goog.string.format)
  (:require-macros [html5-canvas.macros :refer [p pp]]))


;; App State

(def appState (atom { :isDrawing false :lastX 0 :lastY 0 :hue 0}))


;; DOM Helpers

(defn get-canvas []
  (.querySelector js/document "#draw"))

(defn get-ctx []
  (.getContext (get-canvas) "2d"))

(defn get-window-innerWidth []
  (.. js/window -innerWidth))

(defn get-window-innerHeight []
  (.. js/window -innerHeight))

(defn toggle-drawing [b]
  (swap! appState assoc :isDrawing b))


;; Event Handlers

(defn draw [e]
  (when (get @appState :isDrawing)
    (do
      (.beginPath (get-ctx))
      ;; this is interesting because if I remove this line the color does not
      ;; rainbow, however, when I have it hear it does - does an atom not update
      ;; unless it is being accessed again?
      (p (get @appState :hue))
      (set! (.. (get-ctx) -strokeStyle) (str "hsl(" (get @appState :hue) ", 100%, 50%"))
      (.moveTo (get-ctx) (get @appState :lastX) (get @appState :lastY))
      (.lineTo (get-ctx) (.. e -offsetX) (.. e -offsetY))
      (.stroke (get-ctx))
      (swap! appState assoc :lastX (.. e -offsetX))
      (swap! appState assoc :lastY (.. e -offsetY))
      (swap! appState update-in [:hue] inc))))


;; Start

;; set canvas to be width and height of window
(set! (.. (get-canvas) -width) (get-window-innerWidth))
(set! (.. (get-canvas) -height) (get-window-innerHeight))

;; set drawing style of canvas
(set! (.. (get-ctx) -lineJoin) "round")
(set! (.. (get-ctx) -lineCap) "round")
(set! (.. (get-ctx) -lineWidth) 50)


(.addEventListener (get-canvas) "mousemove" draw)
(.addEventListener (get-canvas) "mousedown" (fn [e] (do
                                                     (toggle-drawing true)
                                                     (swap! appState assoc :lastX (.. e -offsetX))
                                                     (swap! appState assoc :lastY (.. e -offsetY)))))


(.addEventListener (get-canvas) "mouseup" (fn [] (toggle-drawing false)))
