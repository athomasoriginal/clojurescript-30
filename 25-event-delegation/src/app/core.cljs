(ns app.core
  (:require
    [goog.object])
  (:require-macros
    [app.macros :refer [p pp]]))

(def divs (-> js/document (.querySelectorAll "div")))


(defn log-text
  [e]
  (this-as this
    (js/console.log (-> this .-classList .-value))))


(doseq [div (array-seq divs)]
  (-> div (.addEventListener "click" log-text)))
