# 08 Ajax Type Ahead

- [Housekeeping](#housekeepings)
- [Quickstart](#quickstart)
- [Lessons Learned](#lessons-learned)
  - [JS Interop](#js-interop)
    - [Fetch](#fetch)
  - [Clojure](#clojure)
    - [Spread Operator](#spread-operator)
    - [Regexes](#regexes)
    - [Concat V Into](#concat-v-into)

## Housekeeping

Before trying out this repo please ensure you have a cljs environment setup. See the [Getting Started Guide](https://github.com/tkjone/clojurescript-30#getting-started)

## Quickstart

Run the following comamnds from the root of the `08-ajax-type-ahead` repo

**1. Build and watch the project**

```bash
clj -A:dev
```

## Lessons Learned

This lesson brought some new pain. Some of the big challenges were

1.  JS Interop - fetch
1.  Clojure - regex, lazy-seqs, maps, string formatting
1.  Debugging Tools

### JS Interop

#### Fetch

What does the fetch `api signature` look like in CLJS?

```clojure
(-> (js/fetch "http://10.0.3.2:3000/" (clj->js {:method "POST"}))
    (.then #(.json %)) ;; warning: `.json` returns a promise that resolves to the parsed json body
    (.then js->clj)
    (.then yourhandlerhere)
    (.catch #(js/console.error %)))
```

One question is whether calling this the `API signature` is correct?

Lets see what the above looks like when I don't use `->` which is a good examle of how to implement idiomatic cljs

I was a little stumped on how to think about this one from a clojure perspective. Consider what we are tying to do here:

1.  get data
2.  store data is some global var (is this an atom in clojure?)
3.  have other functions that can operate on this global data store?

So what would this look like?

```clojure
(def cities (atom nil))

(-> (js/fetch endpoint)
    (.then (fn [res] (reset! cities (seq (.json res))))))
```

A great piece of advice from `noisesmith` was

> in cljs you can use google closure, goog.object has things that make dealing with values a lot easier

I think the nicer way would be to use `swap`

### Clojure

#### Spread Operator

In JS we do this:

```
a1 = [1, 2, 3]
a2 = [4]
a3 = [...a1, ...a2] // [1, 2, 3, 4]
```

Would be good to understand how the above works in CLJS

#### Regexes

If we want to dynamically look for a word, we also have to use the `re-pattern` which lets us create a pattern

`(re-pattern (str "" some-string))`

Something to remember here is that regexes seemed a lot more difficult when performing this excercise then they ever have and for me, I felt it was because of the syntax. It make it confusing to follow and the whole thing is confusing anyways.

2.  How regex's actually work
3.  debugging a filter to see what it is doing
4.  lazySeq not allowing me to see WTF is in the array-seq - how to realize them. in our case, using join does the trick, but maybe explore other options
5.  Not being able to iterate over a map - obvi
6.  Adding html in pur strings is wicked hard - maybe a look at how to add an HTML helper like hiccup
7.  What if the cities object has no vals cause the the promise has not resolved? add a timeout to illustrate

## Concat v Into

> noisesmith

in clojure, you could do this:

if a1 is a clojure type
or `(into [] cat [a1 a2])` if not

or just `(concat a1 a2)` - depending on what you are trying to do

concat is lazy, into is strict

> val_waeselynck

Either `(concat a1 a2)` - yields a lazy seq - or `(into a1 a2)` (yields a collection of the same type as `a1`)
