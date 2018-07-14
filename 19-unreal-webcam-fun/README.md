# Unreal Webcam Fun

For this one I did not add the rgb effects and only focused on showing the webcam in the video element and taking a picture.  The other items have been done before and I spent more time researching more idiomatic clojure code

* [Working with Native JS Functions](#working-with-native-js-functions)
* [Thread v. Double Dot Macro](#thread-v-dot-dot-macro)
* [Require from Google Closure](#require-from-google-closure)
* [Time Intervals](#time-intervals)
* [Add to JS Global Scope](#add-to-js-global-scope)

# Working with Native JS Functions

This is more of a note, but sometimes you might try to use a JS method and be confused by the fact that passing a CLJS object will not work with it.  In cases like this, remember to switch to a JS object instead.  For example:

```clojure
(-> js/navigator
    .-mediaDevices
    (.getUserMedia #js {:video true :audio false}) ;; <-- native js object
    (.then handle-promise)
    (.catch handle-promise-err)))
```

By using the `#js` macro, I am creating and passing a JS object to `.getUserMedia`


# Thread v dot dot macro

You are going to notice that I use the `->` macro for the first time in this series.  I am going to opt for this going forward.  It appears to be the preferred option based on conversation I have had with other **clojurians**.


# Require from Google Closure

Take a look at [this article](https://www.martinklepsch.org/posts/requiring-closure-namespaces.html) for more information.


# Time Intervals

Lets say you want to `console.log` something once every 2 seconds.  To do this in JavaScript, you would use something like this:

```javascript
setInterval(() => console.log('tick'), 1000)
```

How would you achieve the same thing in clojure?  Turns out there are no less than 3 approaches to this.  Below, I will show examples of all three of the options, but I would say that either **option 1** or **option 2** are your best bets.  `core.async` is a beast of a library to use for such a simple task.


**1.  js/setInterval**

```clojure
(js/setInterval #(p "tick") 1000)
```

**2.  goog.timer**

To use this, you need to require `goog timer` into your namespace like this:

```clojure
(:require        [goog.events :as events]
                 [goog.Timer :as timer])
(:import [goog Timer])
```

and than you can use it like this

```clojure
(def my-timer (Timer.))

(events/listen my-timer timer/TICK (fn [e] (p "hi")))

(.start my-timer)
```

Working with google closure can be a little confusing, so I recommend reviewing the source code when stuck.  For example, when learning how to start the above timer, I reviewed [google closure timer source code](https://github.com/google/closure-library/tree/master/closure/goog/timer).  Specifically, checkout `timer_test.js`.


**3.  core.async**

This is the heavy duty option.  To start using this approach, you have to add it to your projects dependencies.  See `build.boot` for this line:

```clojure
[org.clojure/core.async    "0.3.443"]
```

> My version might become out-of-date, checkout the [official github repo](https://github.com/clojure/core.async) for the most current version

You then need to import core.async into your namespace. This would look like this:

```clojure
(:require        [cljs.core.async :as async])
(:require-macros [cljs.core.async.macros :as m :refer [go]]))
```

> Take note that you are importing from `cljs` and not `clj`.

Now we can make ourselves a little setInterval timer core.async style:

```clojure
(go
  (loop []
    (async/<! (async/timeout 1))
    (do-something)
    (recur)))
```

This last one was a little tricky to figure out how to use at all.  More info on `core.async`:

* https://www.braveclojure.com/core-async/

* http://www.bradcypert.com/clojure-async/

* http://rigsomelight.com/drafts/clojurescript-core-async-todos.html

* http://martintrojer.github.io/clojure/2014/03/09/working-with-coreasync-exceptions-in-go-blocks

* https://github.com/swannodette/async-tests/tree/master/src/async_test


# Add to JS Global Scope

I only do this in this lesson because I wanted to align with wes's lesson set.  Normally, this would not be the way to do it, but it is good to know how this would be done.

```clojure
(set! js/takePhoto #(...))
```

Note that the naming convention for the `takePhoto` function is not idiomatic JS.  This was just done for this lesson.  Remember that `lisp-case` is not commonly used in JS.
