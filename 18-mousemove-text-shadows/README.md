# Mouse Move

* [Math](#math)
* [Functional Thinking](#functional-thinking)
* [Destructuring Clojurscript](#destructuring-javascript)

* what happens when a variable is assigned in global and than a parameter has the same name?


# Requirements

Please ensure you have a clojure environment running locally - see [Getting Started Guide](https://github.com/tkjone/clojurescript-30#getting-started) if you need to set one up.


# Quickstart

Run the following comamnds from the root of the `18-mousemove-and-text-shadows`

**1.  Run the projcet**

```bash
boot dev
```

**2.  Visit the app**

http://localhost:3000/


# Math

`Math` is short for `java.lang.Math` which is one of the Java classes automatically imported into your namespace for you by Clojure.  This is the reason you can access `Math` in any of your namespaces by just using something like `(Math/round 8.837372)`.  You will use `Math` when you need functions like `round`, `abs`, `exp`.  There is a lot more to this, so I recommend taking a look at [Clojure in Action - pg 11](https://www.manning.com/books/clojure-in-action-second-edition).

If you want to research this more, this falls into the **Java Interop** category.


# Functional Thinking

This lesson introduces an opportunity to think like a functional programmer.  Wes writes his code like this:

```javascript
function moveShadow (e) {
    let x = e.target.offsetHeight
    let y =  e.target.offsetWidth

   if (condition) {
       x += 10 //update value
       y += 15 // update value
   }

  doSomethingWithSideEffects(x, y)
}
```

In the above we have local mutable state, or state that change within the function.  This is an imperative approach.  You can do something like this in Clojure, but clojure will fight you.  The result will be code that is confusing and unmaintainable.  SO rather than imperative, we can try a more functional approach.

A functional approach could be like this:

```clojure
(defn get-xy
  [e this]
  (let [x (.-offsetX e)
        y (.-offsetY e)]
    (if (not= this (.-target e))
      [(+ x (.. e -target -offsetLeft)) (+ y (.. e -target -offsetTop))]
      [x y])))


(defn move-shadow
  [e]
  (this-as this
    (let [width  (.-offsetWidth hero)
          height (.-offsetHeight hero)
          [x y]  (get-xy e this)
          x-walk (calc-x-walk x walk width)
          y-walk (calc-y-walk y walk height)]
      (set! (.. text -style -textShadow) (make-text-shadow x-walk y-walk)))))
```

`get-xy` is the rewrite of the variable re-assignment we see in the JS solution.  In this example, we choose to not set variables that are re-assigned, but create a function that returns the original `x and y` or the modified `x and y`.


# Destructuring Clojurscript

Destructuring is a nice way of writing more concise code.  This lesson was my first opporunity to use destructuring as seen here:

```clojure
(let [[x y]  (get-xy e this)])
```

For more info on this, check out the [clojure destructuring documentation](https://clojure.org/guides/destructuring)
