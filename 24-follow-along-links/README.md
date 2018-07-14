# Follow Along Links

- [Quickstart](#quickstart)
- [Learnings](#learnings)
  - [Macros](#macro)
- [Resources](#resources)

## Quickstart

- Build and watch the project

  ```bash
  boot dev
  ```

Visit site at `http://localhost:3000`

## Learnings

### Macros

```clojure
(-> highlight .-style .-width)
```

I found myself writing the above a bunch and wondered if I could explore different ways of doing this in Clojure. I found two options that I wanted to explore:

1.  Macros
2.  Google Closure Library

Here what each of the solutions looked like:

**Macro**

To start, don't jump to macros. I was just trying to understand them better and compare against other solutions, but its a powerful tool that we can reserve for other times. What was interesting with Macros is I found it hard to get it to work initially. So here is an overview:

_Broken Macro_

```clojure
;; I do not work
(defmacro get-attr
  [el attr]
  `(let [element#   ~el
         attribute# ~attr]
     (attribute# element# )))
```

_Working Macro_

```clojure
;; I work
(defmacro get-attr
  [el attr]
  `(~attr ~el))

;; I work
(defmacro get-attr [i e]
(let [prop-sym i
      obj-sym e]
  (list prop-sym obj-sym)))
```

Lets break it down the first one that works

1.  The list is **quoted** - essentially telling clojure compiler not to evaluate the list or its items. Just return itself.
2.  At compile time, evaluate `~attr` and `~el` Which means our code now looks like this at the end of compile time:

```clojure
(.-style element)
```

Okay, now compare to the very first example. So what was happening in the first example? Again, the whole thing is quoted, but the error happens here:

```
(let [element#   ~el
      attribute# ~attr])
```

Essentially the above is the equivalent of trying to do

```clojure
(let [my-local .-style])

or

(def my-global .-style)
```

What is the problem with the above? Well, its because we were trying to evaluate an [instanceField](https://clojure.org/reference/java_interop#_the_dot_special_form)

```clojure
(def my-global .-style)
```

The abvoe is going to break because each of the items is evaluated one at a time. first, the `def`. then the `my-global` and then `.-style`. Normally, the evaluator expects something with `.-` to be at the front of a list and have

The question is, what is `.-`. The answer is that the `.` dot part of it is special and reserved in clojure for interop. That's why it's not going to work.

After all the above, I believe the cleanest way might be the `doto`

```clojure
(set! (-> highlight .-style .-width) (make-hw-val width))
(set! (-> highlight .-style .-height) (make-hw-val height))
(set! (-> highlight .-style .-transform) (make-trans-val left top)))))
```

```clojure
(doto
  (-> highlight .-style)
  (set! -width (make-hw-val width))
  (set! -height (make-hw-val height))
  (set! -transform (make-trans-val left top))))))
```

## Resources

https://cljs.github.io/api/syntax/dot

http://clojurescriptmadeeasy.com/blog/js-interop-property-access.html

https://clojure.org/guides/weird_characters
