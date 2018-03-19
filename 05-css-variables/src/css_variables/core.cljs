(ns css-variables.core
  (:require-macros [css-variables.macros :refer [p pp]]))


(defn handle-update []
  (this-as this
    (let [suffix          (.. this -dataset -sizing)
          name            (.. this -name)
          val             (.. this -value)
          formatted-name  (str "--" name)
          formatted-value (str val suffix)]
      (.. js/document -documentElement -style (setProperty formatted-name formatted-value)))))

;; Start app

(let [inputs (.querySelectorAll js/document ".controls input")]
  (doseq [input (array-seq inputs)]
    (.addEventListener input "change" handle-update)
    (.addEventListener input "mousemove" handle-update))) 
