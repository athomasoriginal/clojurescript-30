(ns app.core
  "Demo app illustrating a music guitar chord."
  (:require [hiccups.runtime]
            [reagent.core :as r])
  (:require-macros
    [app.macros      :refer [p pp]]
    [dynadoc.example :refer [defexample]]
    [hiccups.core    :refer [html]]))

;; Globals
;; -----------------------------------------------------------------------------


(def chord-boxes
  {:em [[1 :mute] [2 3 1] [3 2 2] [4 :open] [5 1 1] [6 :open]]})


;; Helpers
;; -----------------------------------------------------------------------------


(defn muted-fret?
  "Returns true when the key provided is :mute"
  [fret]
  (= fret :mute))


(defn open-fret?
  "Returns true when the key provided is :open"
  [fret]
  (= fret :open))


(defn string-css-class
  "Returns a string representing a CSS class.

  Possible return values:  mute, open, finger"
  [first-fret? fret curr-fret]
  (cond
    ;; because of how we are styling the html, we only want the mutes and
    ;; opens to be rendered on the first fret.  Reason being them strings are
    ;; the grid gaps
    (and first-fret? (muted-fret? fret))
    "mute"

    (and first-fret? (open-fret? fret))
    "open"

    (muted-fret? fret)
    ""

    (open-fret? fret)
    ""

    (= curr-fret fret)
    "finger"

    :default
    ""))


;; Components
;; -----------------------------------------------------------------------------


(defn fret
  "Renders 1 fret and 6 strings per fret"
  [chord curr-fret]
  (map
    (fn [chord*]
      (let [first-fret?               (= curr-fret 1)
            [string fret* finger-pos] chord*]
        [:div {:class (str "string " (string-css-class first-fret? fret* curr-fret))}]))
    chord))


(defn frets
  "Render 4 frets"
  [chord total-frets]
  (for [curr-fret total-frets]
    [:div.fret
      (fret chord curr-fret)]))

(defn chord-box
  "Render a chord diagram of 4 frets and 6 strings"
  [title chord]
  (html [:div.chord-diagram-wrapper
          [:h1 title]
          [:div.chord-diagram
            (frets chord (range 1 5))]]))


;; Live Docs
;; -----------------------------------------------------------------------------


(defexample chord-box
  {:with-card card
   :with-focus [focus [chord-box "CM" (:em chord-boxes)]]}
  (reagent.core/unmount-component-at-node card)
  (reagent.core/render-component focus card)
  nil)


;; Start
;; -----------------------------------------------------------------------------


(doto
  (.. js/document (getElementById "chord-container"))
  (-> .-innerHTML (set! (chord-box "C Major" (:em chord-boxes)))))
