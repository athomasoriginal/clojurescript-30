# 04-Clock

* [Housekeeping](#housekeepings)
* [Quickstart](#quickstart)
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
* [Steps to Refactor](#steps-to-refactor)
* [TODO](todo)

# Housekeeping

Before trying out this repo please ensure you have a cljs environment setup. See the [Getting Started Guide](https://github.com/tkjone/clojurescript-30#getting-started)

## Quickstart

Run the following comamnds from the root of the `04-clock` repo

**1. Build and watch the project**

```bash
boot dev
```

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

# Steps to Refactor

These are some of the steps I took when refactoring. I thought it might be interesting to see how the approach could evolve for new developers. The final effort involves me adding spec. One of my favorite things about spec is that when you `s/fdef` a function, the arg types are automatically added to the original function docs, so if you are looking through the docs, you will see the spec defs you provided. I was hesitant to add spec because it is a layer ontop of everything, but this is also why I am recording which points of the repo might be interesting. This feels like the final evolution.

* [Original Effort](https://github.com/tkjone/clojurescript-30/commit/d8b394f35c2caa486369cf0aa1e35c26d669eac4)

* [Remove Duplicated Efforts](https://github.com/tkjone/clojurescript-30/commit/de985ac48d752ec8b53bae6f232c0e31bc610045)

* [Refactor All to One Func](https://github.com/tkjone/clojurescript-30/commit/7609058eff1b878f3a40c4aee5ac4af1b24b61dc)

* [Add spec](https://github.com/tkjone/clojurescript-30/blob/master/04-clock/src/app/core.cljs)

# TODO

* [ ] Explore [removing global selectors](https://github.com/tkjone/clojurescript-30/commit/258fe316c67f148838968be27a5f34714811d3eb) why this as a preference - think Elements of Clojure

* [ ] Explore [removing every as a naming convention](https://github.com/tkjone/clojurescript-30/commit/a729df45af03eae673af46fbf03cd8304102e8cd) why this as a preference - think Elements of Clojure. The challenge here is that there is already a `second` function and a `min` function in the core. So we have to consider this when naming and they are exceptionally useful functions.

* [ ] Explore `(def duration {:second 1000 :minute (* (:second duration) 60)})` - the reason this cannot be done is because maps are eager by default. You could import a `lazy-map` like this `(require '[lazy-map.core :refer [lazy-map]])` and then

```clojure
(def duration (lazy-map {:second 1000 :minute (*  (:second duration) 60)}))

(:second duration) => 1000

(:minute duration) => 60000
```

But without doing the above, you could just do something like this:

```clojure
(def duration
  (let [second 1000
        minute (* second 60)]
  {:second second :minute minute}))
```

[Clojure's lazy map](https://github.com/Malabarba/lazy-map-clojure) which is used in something like [Planck](https://github.com/mfikes/planck/blob/master/planck-cljs/src/planck/repl.cljs#L215) or Lumo to shave off millisecond for the launch time

* [ ] Explore working with specs. This project could be a good way to show how we can use them.
