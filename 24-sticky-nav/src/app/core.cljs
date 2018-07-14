(ns app.core
  (:require
    [goog.object])
  (:require-macros
    [app.macros :refer [p pp]]))


;; Globals

(def nav (-> js/document (.querySelector "#main")))

(def top-of-nav (-> nav .-offsetTop))

(def nav-height (-> nav .-offsetHeight))


;; Event Handlers


(defn fix-nav!
  []
  (if (< top-of-nav (-> js/window .-scrollY))
    (do
      (set! (-> js/document .-body .-style .-paddingTop) (str nav-height "px"))
      (-> js/document .-body .-classList (.add "fixed-nav")))
    (do
      (set! (-> js/document .-body .-style .-paddingTop) 0)
      (-> js/document .-body .-classList (.remove "fixed-nav")))))


;; Start

(-> js/document
    (.addEventListener "scroll" fix-nav!))
