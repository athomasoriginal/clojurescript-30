;; create main project namespace
(ns array-cardio.core
  (:require [goog.object])
  (:require-macros [array-cardio.macros :refer [p pp]]))


(p "---------------------------- Array.prototype.filter() ---------------------------------")

;; 1. Filter the list of inventors for those who were born in the 1500's)
;; http://blog.jenkster.com/2013/11/clojure-less-than-greater-than-tip.html
;; we are asking if the number is larger than 1500 and smaller than 1600
;; is 1500 less than or equal to 1501?
;; is 1501 less than or equal to 1600?

(p "Original Data Structure")
(p js/inventors)

(def tranformed-ds (clj->js (filter #(<= 1500 (goog.object/get % "year") 1600) js/inventors)))

(p "Transformed Data Structure")
(p tranformed-ds)


(p "---------------------------- Array.prototype.map() ------------------------------------")

;; 2. Give us an array of the inventors' first and last names

(defn full-name [inventor]
  (str (goog.object/get inventor "first") " " (goog.object/get inventor "last")))

(def tranformed-ds-two (clj->js (map full-name js/inventors)))

(p "Transformed Data Structure")
(p tranformed-ds-two)


(p "---------------------------- Array.prototype.sort() ------------------------------------")

;; 3. Sort the inventors by birthdate, oldest to youngest

(def tranformed-ds-three (clj->js (sort-by #(goog.object/get % "year") js/inventors)))

(p "Transformed Data Structure")
(p tranformed-ds-three)


(p "---------------------------- reduce -----------------------------------")

;; 4. How many years did all the inventors live?
;; subtract the year and passed date
;; add the above to a total tracker

(def tranformed-ds-four (clj->js (reduce #(+ %1 (- (goog.object/get %2 "passed") (goog.object/get %2 "year"))  ) 0 js/inventors)))

(p "Transformed Data Structure")
(p tranformed-ds-four)


(p "-----------------------------sort again-------------------------------------")

;; 5. Sort the inventors by years lived

;; sorted from youngest to oldest

(def tranformed-ds-five (clj->js (sort-by #(- (goog.object/get % "passed") (goog.object/get % "year")) js/inventors)))

;; sorted from oldest to youngest

(def tranformed-ds-six (clj->js (sort-by #(- (goog.object/get % "passed") (goog.object/get % "year")) > js/inventors)))

(p tranformed-ds-five)
(p tranformed-ds-six)


(p "---------------------------- filter again ------------------------------------")

;; 6. create a list of Boulevards in Paris that contain 'de' anywhere in the name

(def tranformed-ds-seven (clj->js (filter #(re-find #"de" %) js/people)))

(p tranformed-ds-seven)


(p "---------------------------- sort again ------------------------------------")

;; Sort the people alphabetically by last name

;; you could also do > (split % #",") which is a little cleaner and more conventional

(def tranformed-ds-eight (clj->js (sort-by #(get (re-find #", (.*)" %) 1) js/people)))

(p tranformed-ds-eight)

(p "-----------------------------reduce again-------------------------------------")

;; Sum up the instances of each of these - in other words, creating a word frequency
;; map

;; So this one was fun because clojure actually provides a function that does
;; exactly this.

(def tranformed-ds-nine (clj->js (frequencies js/data)))

;; Just for fun, I have also done the above using reduce.  See below.

;; reduce over each, keep some kind of map available and increment the maps number
;; when a word in the map appears again.

(def tranformed-ds-ten (clj->js (reduce #(assoc %1 %2 (inc (%1 %2 0))) {} js/data)))


(p tranformed-ds-nine)
(p tranformed-ds-ten)

;; after performing these excercises, it because immediatley clear of some of the difference when trying
;; to functional with js and clojurescript.  js can do it, and I believe it does it well, but once you do
;; it with clojure, it feels awkward and like you are trying to make it work by being clever.  This I believe
;; is a combination of the design of the language and clojures out of the box support for functional programming.
;; also, everything in clojure is data.  so working with data is super easy.  It just feels smoother.
;; for example, when doing the above, the way I would normally do this is create an object and then increment
;; things in it.  but we has assoc and inc which makes this so much cleaner and thinking about it is less
;; imperative.
