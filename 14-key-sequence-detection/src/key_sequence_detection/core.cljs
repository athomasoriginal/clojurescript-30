;; create main project namespace
(ns key-sequence-detection.core
  (:require        [goog.string :as gstr] goog.string.format)
  (:require-macros [key-sequence-detection.macros :refer [p pp]]))

;; App state

(def keys-pressed (atom #queue []))


;; Event handlers

(defn create-keyup-handler
  [secret f]
  (fn
    [e]
    ;; add recently pressed key to our queue
    (swap! keys-pressed conj (.-key e))

    ;; store as many items as the length of the secret
    (when (> (count @keys-pressed) (count secret))
      (swap! keys-pressed pop keys-pressed))

    ;; check if user typed secret
    (when (and (= (count @keys-pressed) (count secret))
               (= (apply str @keys-pressed) secret))
      (f))))


;; Event listeners

(.addEventListener js/window "keyup" (create-keyup-handler "secret" (.-cornify_add js/window)))
