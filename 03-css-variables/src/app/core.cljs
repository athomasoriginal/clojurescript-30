(ns app.core
  (:require-macros [app.macros :refer [p pp]]))


(defn update-style []
  (this-as this
    (let [suffix          (.. this -dataset -sizing)
          name            (.. this -name)
          val             (.. this -value)
          formatted-name  (str "--" name)
          formatted-value (str val suffix)]
      (.. js/document -documentElement -style (setProperty formatted-name formatted-value)))))


;; Start app

(let [inputs (.. js/document (querySelectorAll ".controls input"))]
  (doseq [input (array-seq inputs)]
    (.. input (addEventListener "change" update-style))
    (.. input (addEventListener "mousemove" update-style))))
