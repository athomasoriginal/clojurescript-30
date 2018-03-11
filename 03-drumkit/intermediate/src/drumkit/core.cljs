;; create main project namespace
(ns drumkit.core
  (:require        [goog.string :as gstr] goog.string.format)
  (:require-macros [drumkit.macros :refer [p pp]]))


;; Event Handlers

(defn handle-key-press [e]
  (let [key-code      (.-keyCode e)
        data-selector (gstr/format "audio[data-key=\"%s\"]" key-code)
        key-selector  (gstr/format ".key[data-key=\"%s\"]" key-code)
        audio-element (.querySelector js/document data-selector)
        key-element   (.querySelector js/document key-selector)]
    (when-not (.isNull js/goog audio-element)
        (set! (.-currentTime audio-element) 0)
        (.play audio-element)
        (.classList/add key-element "playing"))))

(defn handle-remove-transition [e]
  (this-as this
    (when (= (.-propertyName e) "transform")
      (.remove (.-classList this)  "playing"))))


;; Init

(let [el-keys (.querySelectorAll js/document ".key")]
  (doseq [el-key (array-seq el-keys)]
    (.addEventListener el-key "transitionend" handle-remove-transition)))

(.addEventListener js/window "keydown" handle-key-press)
