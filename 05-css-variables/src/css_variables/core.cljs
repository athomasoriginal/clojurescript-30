(ns css-variables.core
  (:require        [goog.string :as gstr] goog.string.format)
  (:require-macros [css-variables.macros :refer [p pp]]))

;; protocol
(extend-type js/NodeList
   ISeqable
   (-seq [node-list] (array-seq node-list)))

;; global definitions
(def inputs (.querySelectorAll js/document ".controls input"))

(defn handle-update []
  (this-as this
    (let [suffix (.. this -dataset -sizing)
          name (.. this -name)
          val (.. this -value)
          formatted-name (gstr/format "--%s" name)
          formatted-value (gstr/format "%s%s" val suffix)]
      (.. js/document -documentElement -style (setProperty formatted-name formatted-value)))))


;; print to the console
(doseq [input inputs]
  (.addEventListener input "change" handle-update)
  (.addEventListener input "mousemove" handle-update))
