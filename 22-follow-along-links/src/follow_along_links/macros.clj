;; create macros namespace
(ns follow-along-links.macros)


; (defmacro get-attr
;   ""
;   [el attr]
;   `(let [element   ~el
;          attribute ~attr]
;      (attribute element)))

; (defmacro get-attr
;   [el attr]
;   `(-> ~el ~attr))

; (defmacro get-attr
;  [el attr]
;  `(~attr ~el))

(defmacro get-attr [i e]
 (let [prop-sym i
       obj-sym e]
   (list prop-sym obj-sym)))


(defmacro p
  "Print and return native JavaScript argument."
  [x]
  `(let [res# ~x]
     (.log js/console res#)
     res#))


(defmacro pp
 "Pretty print and return argument (uses `prn-str` internally)."
 [x]
 `(let [res# ~x]
    (.log js/console (prn-str res#))
    res#))
