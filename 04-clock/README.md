# 04-Clock

* [Housekeeping](#housekeepings)
* [Lessons Learned](#lessons-learned)
  * [How to do things clojure](#how-to-do-things-in-clojure)
  * [Namespace](#namespaces)
  * [JS Interop](#js-interop)
    * [Dates](#dates)
    * [Set Interval](#set-interval)
    * [Double Dot Macro](#double-dot-macro)
    * [aset v set](#aset-v-set)
  * [Clojure Lang](#clojure-lang)
    * [Dispatch Macro](#dispatch-macro)
  * [Gotchas](#gotchas)
* [Resources](#resources)

# Housekeeping

Before trying out this repo please ensure you have a cljs environment setup. See the [Getting Started Guide](https://github.com/tkjone/clojurescript-30#getting-started)

# Lessons Learned

## How to do things clojure

The big question becomes, where do I go to look for thing clojure/clojurescript related. If it is **syntax related** related remember that clojure and clojurescript share 95% of their syntax. So feel free to look into clojure docs if you cannot find something by searcing for clojurescript. Why note this? Because if you change a file name, its export in JS, you do not need to reboot your tooling. This is assuming you have everything setup correctly.

## Namespaces

If you change a namespace while your app is running you will need to restart the app in order to see this namespace change take effect.

## JS Interop

### Dates

For this, you are going to just call the Date function in JS. This will look like this:

```clojure
;; option 1
(js/Date.)

;; option 2
(new js/Date)
```

What we are learning here is that we need more examples of how JS/Interop works.

**So when would you use one over another?**

* [ ] TODO - which one to choose under which circumstance?

### Set Interval

Another example is using `js/setInterval`

```clojure
(js/setInterval function-here duration)
```

### Double Dot Macro

So good luck trying to find this sweet lady in the clojure docs. It apparently has no name. Here is an example of it being used:

```clojure
;; regular
(set! (.-transform (.-style second-hand)) secondsAsDegrees)))

;; with double dot
(set! (.. second-hand -style -transform) 123)
```

### aset and set

This is a good article to read about the difference between `aset` and `set`

[CLJS Interop](http://www.spacjer.com/blog/2014/09/12/clojurescript-javascript-interop/)

## Clojure Lang

When we are introduced to new clojure syntax, we will add it here.

### Dispatch Macro

The [dispatch macro](https://clojure.org/reference/reader) is being used for the first time in this series. In this case, the `dispatch macro` is used to create an anonymous function:

```clojure
#(; do stuff)
```

This is also an anonymous function:

```clojure
(fn [args] (…​))
```

**So when would you use one over another?**

In general, it depends on the length of the code the function is going to run and the readability and who will be working with your code base. If I am a company, I would likely opt for `named functions` are easier, in general to read, especially when we consider how odd it looks in clojurescript. However, there are time when maybe you want what you are writing to be more visually compact. Here are some example cases:

To learn more about these:

* [Clojure Bridge - Anonymous Function](https://clojurebridge.github.io/community-docs/docs/clojure/anonymous-function/)
* [Clojure for the Brave and True - Anonymous Function](https://www.braveclojure.com/do-things/#3_4__Anonymous_Functions).

[3:00] RE - deleting and renaming ns - noisesmith
(where effectively, a renamed file is both - you would need to eliminate things defined under the old ns etc.)

# Gotchas

* make sure you specify the correct style name for a transform. For example, if you write `rotate(3234)` this will not do anything at all in clojurescript. How does this exact same thing respond in Javascript?

* Boots reloading doesn't handle new files, deleted files, or renamed files.

# Resources

* [Clojure Workshop - Calling Functions](https://practicalli.github.io/clojure/defining-behaviour-with-functions/calling-functions.html)

Issue this helped me with was clearing up how exactly to call a function. For example,

```clojure
(defn set-date [] (p "Hello"))

(set-date)
```
