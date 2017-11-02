;; create main project namespace
(ns localstorage.core
  (:require        [goog.string :as gstr] goog.string.format)
  (:require-macros [localstorage.macros :refer [p pp]]))


;; Actions

(defn create-tapa
  [text done?]
  #js {:text text :done done?})


;; Helpers

(defn make-plate-html
  "Builds an HTML string.  The HTML is a list item which contains an input and
   a label tag"
  [index action]
  (str "<li>"
         "<input type=\"checkbox\" data-index=" index " id=\"item" index "\"" (when (.-done action) "checked") "/>"
         "<label for=\"item" index "\"" ">" (.-text action) "</label>"
       "</li>"))

(defn make-plate-list
  [plates]
  (apply str (map-indexed #(make-plate-html %1 %2) plates)))

(defn save-items
  "save a food item in the browsers local storage"
  [items]
  (.setItem js/localStorage "items" items))

(defn update-items-list
  "Update the innerHTML of the items list"
  [prev-list next-list]
  (set! (.-innerHTML prev-list) (make-plate-list next-list)))

(defn update-item-done-state
  "Toggle the done state of an item"
  [item]
  (set! (.-done item) (not (.-done item))))


;; Event handlers

(defn add-item
  [e]
  (.preventDefault e)

  (this-as this
    (let [text (.. this (querySelector "[name=item]") -value)
          item (create-tapa text false)]
        (.push js/items item)
        (update-items-list js/itemsList js/items)
        (save-items (.stringify js/JSON js/items))
        (.reset this))))

(defn toggle-done
  [e]
  (when (.. e -target (matches "input"))
    (let [element (.-target e)
          index   (.. element -dataset -index)
          item    (aget js/items index)]
        (update-item-done-state item)
        (save-items (.stringify js/JSON js/items))
        (update-items-list js/itemsList js/items))))


;; Event listeners

(.addEventListener js/addItems "submit" add-item)

(.addEventListener js/itemsList "click" toggle-done)


;; Populate the list of items on page load

(update-items-list js/itemsList js/items)
