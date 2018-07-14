;; create main project namespace
(ns array-cardio-2.core
  (:require        [goog.string :as gstr] goog.string.format)
  (:require-macros [array-cardio-2.macros :refer [p pp]]))


(p "---------------------------- some ---------------------------------")

;; is at least one person 19 or older?

(p js/people)
(p js/comments)

(p (some #(> 19 (- (.. % -year) 2017))
          js/people))

(p "---------------------------- every ---------------------------------")

;; is everyone 19 or older
(p (every? #(> 19 (- (.. % -year) 2017))
           js/people))

(p "---------------------------- find ---------------------------------")

;; find the comment with the ID of 823423

;; another way to write the below (first (filter (fn [v] (= (:id v) ??)) book-list))

(p (first (filter #(= (.. % -id) 823423)
                   js/comments)))

(p "---------------------------- findIndex ---------------------------------")

;; Find the comment with this ID - 823423

(def indexOfComments (keep-indexed
                        (fn [index item]
                          (when (= (.. item -id) 823423)
                            index)) js/comments))

(p (first indexOfComments))

;; took me about two hours to find a good way to do this :)
