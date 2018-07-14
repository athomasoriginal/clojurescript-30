;; create main project namespace
(ns drumkit.core
  (:require-macros [drumkit.macros :refer [p pp]]))


;; Event Handlers

(defn play-sound 
 [e]
 (let [key-code      (.-keyCode e)
       data-selector (str "audio[data-key=" "\"" key-code "\"" "]")
       key-selector  (str ".key[data-key=" "\"" key-code "\"" "]")
       audio-element (.querySelector js/document data-selector)
       key-element   (.querySelector js/document key-selector)]
   (when-not (.isNull js/goog audio-element)
       (set! (.-currentTime audio-element) 0)
       (.play audio-element)
       (.classList/add key-element "playing"))))


(defn remove-transition 
 [e]
 (this-as this
   (when (= (.-propertyName e) "transform")
     (.remove (.-classList this)  "playing"))))


;; Init

(let [el-keys (.querySelectorAll js/document ".key")]
  (doseq [el-key (array-seq el-keys)]
    (.addEventListener el-key "transitionend" remove-transition)))

(.addEventListener js/window "keydown" play-sound)
