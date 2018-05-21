(ns speech-synthesis.core
  (:require
    [goog.object])
  (:require-macros
    [speech-synthesis.macros :refer [p pp]]))


(defn voice-option
  [voice]
  (str "<option value=" (goog.object/get voice "name") ">"
       (goog.object/get voice "name") "(" (goog.object/get voice "name") ")"
       "</option>"))


(defn toggle
  "Cancels speaker and starts new speaker.

  Args:
  -----

  ::startover - boolean - defaults to true.  When false, cancels and does not
                          startover the speaker"
  ([]
   (-> js/speechSynthesis .cancel)
   (-> js/speechSynthesis (.speak js/msg)))

  ([startover]
   (-> js/speechSynthesis .cancel)
   (when startover
     (-> js/speechSynthesis (.speak js/msg)))))


;; Event Handlers

(defn populate-voices!
  "Populates the Voice selection input and globally populates js/voices array."
  []
  (this-as this
    (set! js/voices (-> this (.getVoices)))

    (let [voice-options (apply str (map voice-option js/voices))]
      (set! (.-innerHTML js/voicesDropdown) voice-options))))


(defn set-voice! []
  (this-as this
    (let [voice (first (filter #(= (goog.object/get % "name") (.-value this)) js/voices))]
       (set! (.-voice js/msg) voice)
       (toggle))))


(defn set-option! []
  (this-as this
    (let [name  (-> this .-name)
          value (-> this .-value)]
      (goog.object/set js/msg name value))))

;; Start

(set! (.-text js/msg) (-> js/document (.querySelector "[name=text]") .-value))

(-> js/speechSynthesis (.addEventListener "voiceschanged" populate-voices!))

(-> js/voicesDropdown (.addEventListener "change" set-voice!))

(doseq [option (array-seq js/options)]
  (.addEventListener option "change" set-option!))

(-> js/speakButton (.addEventListener "click" toggle))

(-> js/stopButton (.addEventListener "click" #(toggle false)))
