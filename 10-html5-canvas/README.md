# 10 HTML5 Canvas

* [Housekeeping](#housekeepings)
* [Quickstart](#quickstart)
* [Lessons Learned](#lessons-learned)
  * [Functions](#functions)
  * [Atoms](#atoms)
  * [State](#state)

# Housekeeping

Before trying out this repo please ensure you have a cljs environment setup. See the [Getting Started Guide](https://github.com/tkjone/clojurescript-30#getting-started)

## Quickstart

Run the following comamnds from the root of the `10-html-canvas` repo

**1. Build and watch the project**

```bash
boot dev
```

# Lessons Learned

## Functions

I was playing with the idea that I could avoid storing DOM elements with `vars` and instead use functions which, when called, would return the required DOM elements. At this stage in my learning, this felt a little more idiomatic.

For example:

```
(defn get-canvas []
  (.querySelector js/document "#draw"))

(get-canvas)
```

would be better than this:

```
(def canvas (.querySelector js/document "#draw"))

canvas
```

Furthering to this point, how exactly to invoke a function was not clear. I believe one of the challenges was that in a language like jsvascript, you have a tail of parens `myFunction()` but in clojure, its the first item in the list. And most times, you are working with examples which does noy give a lot of time to focus on what a function invocation looks like. Example:

```clojure
# works
(fn [] (setDrawing true))

# does not work
(fn [] setDrawing true)
```

## Atoms

This course was also the first time I used atoms.

```clojure
(get @appState :isDrawing)
```

When first coming across this I did not realize that in order to access the contents of an Atom you would have to use `@` symbol. This threw me off quite a bit. I ended up figuring it out by looking at the [atom problem](http://www.lispcast.com/atom-problem)

## State

It was also at this point where I realized how wonderful `Atoms` are and how we can think of them as appState - which is nice because we are building a small program, one that is often not considered stateful. This is a light intro to state.
