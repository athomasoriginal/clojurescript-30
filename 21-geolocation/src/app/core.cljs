(ns app.core
  (:require-macros [app.macros :refer [p pp]]))

(def last-hole (atom 0))
(def time-up (atom false))
(def score (atom 0))

(def holes (-> js/document (.querySelectorAll ".hole")))
(def score-board (-> js/document (.querySelector ".score")))
(def moles (-> js/document (.querySelectorAll ".mole")))


(defn random-time
  "Generate a random time in miliseconds"
  [min max]
  (as-> (- max min) time
        (* time (-> js/Math (.random)))
        (+ time min)
        (-> js/Math (.round time))))


(defn random-hole
  "Select a hole at random"
  [holes]
  (let [index (as-> (count holes) hole-count
                    (* hole-count (-> js/Math (.random)))
                    (-> js/Math (.floor hole-count)))
        hole  (nth holes index)]

    (when (= index last-hole)
      (random-hole holes)
      (p "same hole"))

    (reset! last-hole index)

    hole))


(defn peep
  "Show a mole"
  [holes]
  (let [time (random-time 200, 1000)
        hole (random-hole holes)]

    (-> hole .-classList (.add "up"))

    (js/setTimeout
      (fn []
        (-> hole .-classList (.remove "up"))
        (when-not @time-up
          (peep holes)))
      time)))


(defn bonk
  "Hide mole when user clicks of them"
  [e]
  (this-as this
    (if-not (-> e .-isTrusted)
      nil
      (do
        (swap! score inc)
        (-> this .-classList (.remove "up"))
        (set! (-> score-board .-textContent) @score)))))




(defn startGame
  "You know what it is"
  []
  (set! (-> score-board .-textContent) 0)

  (reset! time-up false)

  (reset! score 0)

  (peep (array-seq holes))

  (js/setTimeout #(reset! time-up true) 10000))


(doseq [mole (array-seq moles)]
  (-> mole (.addEventListener "click" bonk)))
