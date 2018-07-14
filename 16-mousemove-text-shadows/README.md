# Mouse Move

This lesson presented a good opportunity to use `Math` and challenge the imperative approach to programming with the functional approach.

* [Math](#math)
* [Functional Thinking](#functional-thinking)
* [Destructuring Clojurscript](#destructuring-javascript)


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

In the above we have local mutable state, or state that changes within the function.  This is an imperative approach. There is nothing wrong with this for imperative languages and you can do something like this in Clojure, but clojure will fight you.  The result will be code that is confusing and unmaintainable.  So rather than imperative, we can try a more functional approach.

A functional approach could be like this:

```clojure
(defn get-xy
  [e this]
  (let [x (.-offsetX e)
        y (.-offsetY e)]
    (if (not= this (.-target e))
      [(+ x (.. e -target -offsetLeft)) (+ y (.. e -target -offsetTop))]
      [x y])))
```

Here is what the above looks like in JS if it makes it a little clearer

```javascript
function getXY (e this) {
  const { offsetX: x, offsetY: y } = e;

  if ( this !== e.target ) {
    const modx = x + e.target.offsetLeft
    const mody = y + e.target.offsetTop
    return [modx, mody]
  }

  return [x, y]
}
```

`get-xy` is the rewrite of the variable re-assignment we see in the JS solution.  In this example, we choose to not set variables that are re-assigned, but create a function that returns the original `x and y` or the modified `x and y`.  We can now use the above function inside of our `shadow-move` function.


# Destructuring Clojurscript

Destructuring is a nice way of writing more concise code.  This lesson was my first opporunity to use destructuring as seen here:

```clojure
(let [[x y]  (get-xy e this)])
```

You will also notice that in the Functional Thinking example above, I did not destructure the `offsetX` and `offsetY` in the let:

```clojure
(let [x (.-offsetX e)
      y (.-offsetY e)])
```

We did not do this because under the hood `let` is using `clojure.core/destructure` to create its binding form and does not expand access macros like that.

If you did want to enable Clojure to do this, you could replacing let with something that uses an expanded destructure, or having a separate macro meant to be used inside a let block.  I will likely not take on this challenge at the moment, but its a good example/opportunity to explore writing macros.  To start this excercise, take a look at the [destructure macro]((https://github.com/clojure/clojure/blob/master/src/clj/clojure/core.clj#L4355))

For more info on destructuring, check out the [clojure destructuring documentation](https://clojure.org/guides/destructuring) and there is also a handy [destructuring cheatsheet](https://gist.github.com/john2x/e1dca953548bfdfb9844) and [another guide here](http://blog.brunobonacci.com/2014/11/16/clojure-complete-guide-to-destructuring/)

and here is a great over of [syntax in clojurescript](https://cljs.github.io/api/syntax/)
