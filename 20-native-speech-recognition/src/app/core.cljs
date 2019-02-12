;; create main project namespace
(ns app.core
  (:require-macros [app.macros :refer [p pp]]))


;; Globals


;; Handlers

(defn write-msg! [msg final?]
  ; Add new paragraph
  (when final?
    (p "NEW PARA")
    (set! js/paragraph (.createElement js/document "p"))
    (.. js/words (appendChild js/paragraph)))

  ; Add to existing paragraph
  (when-not final?
    (p "ORIGINAL")
    (set! (.-textContent js/paragraph) (clojure.string/trim msg))))


(defn- handle-results [results]
  (let [index     (.-resultIndex results)
        final?    (.-isFinal (aget (.-results results) 0))
        message   (.-transcript (aget (.-results results) index 0))
        message-o (.-transcript (aget (.-results results) 0 0))]
    (if (not (and (= 1 index) (= message-o message)))
      (write-msg! (clojure.string/trim message) final?))))


;; Configure speech recognition

(set! (.-interimResults js/speech) true)

;; Start

(.addEventListener js/speech "result" handle-results)

(.addEventListener js/speech "end" #(.start js/speech))

(.start js/speech)
