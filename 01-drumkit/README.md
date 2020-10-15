# Drumkit

---

- [Intro](#intro)
- [Quick Start](#quick-start)
- [Lessons Learned](#lessons-learned)
  - [Protocols](#Protocols)
  - [defs](#defs)
  - [Formatting Strings](#formatting-strings)
- [Final Thoughts](#final-thoughts)
- [Resources](#resources)

## Quick Start

```bash
clj -M:dev
```

> `-M` assumes your using a Clojure Tools version greater than `1.10.1.708`.  Not sure what version your on?  Run `clj -h` and you should see output near the top of the output like: See [CHANGELOG]

```bash
➜ clj -h
Version: 1.10.1.708 # this is the version your on
```

## Intro

I originally built this example using [3 different tools](https://github.com/tkjone/clojurescript-30/tree/59e6afa3342ba5d596948d331f1c61231be97259) to see how the experience compared. Since then, I have moved away from showing all three in the same repo. If you want to see them, visit the link above. With this in mind, some of my original assumptions still hold true:

**Assumptions:**

1.  Keep things simple when teaching a new language

2.  The REPL is more confusing in the beginning then helpful. Consider, you test and rapidly develop with it, but when you write code, its in a file.

3.  Don't introduce too much tooling right away. One of the reasons I liked JS30 is because it approaches education of JS and Web Development without tooling. Just create an HTML file, open it in the browser and your good to go. CLJS is compiled, so its not as simple, but with tools like `deps-cli` it has become a lot easier.

# Lessons Learned

## Protocols

Originally I thought I needed this:

```clojure
;; protocol
(extend-type js/NodeList
   ISeqable
   (-seq [node-list] (array-seq node-list)))
```

But really I could just do it inline. Difference is that

- Easier for others to read and understand

- A little complex for such a small program

I would love to delve into this a little further. See [This Commit](https://github.com/tkjone/clojurescript-30/commit/148a5744caa180c948598cf9234c4928939f7e9e) for the example of the code that was there, which I later removed

## Naming Conventions

`core.cljs` is a naming convention. This is the equivalent of `drumkit/index.js` in JS world. You could call it anything you want really.

## defs

when you use `def` these become global variables in Clojure. For this reason, we usually put defs at the top. One of the reasons for this is because Clojure is read from bottom to top.

## Formatting strings

There are different ways to format strings. One way is like this:

```clojure
(:require        [goog.string :as gstr] goog.string.format)

data-selector (gstr/format "audio[data-key=\"%s\"]" key-code)
```

An alternative is like this

```clojure
data-selector (str "audio[data-key=" "\"" key-code "\"" "]")
```

Difference between the two?

1.  The second option can be used in `clojure` and `clojurescript`
2.  The first option can be considered more readable + easier to maintain

Just keep in mind there are other ways to format strings, so this is not an exhaustive overview.

## Final Thoughts

> This was actually annoying as fuck. Where is the context? The resources are not well documented, there is no, here is something bsic to get you going.

The above is what I wrote when I finished this exercise. No idea what I was referring to at the time. Checkout the [Initial Commit](https://github.com/tkjone/clojurescript-30/commit/34b151e6a2d0fc86fe3f6b34ee0fefaee88c5b94) for more.

# Resources

- https://www.andrewhfarmer.com/webpack-hmr-tutorial/
- https://github.com/bhauman/lein-figwheel

[CHANGELOG]: https://github.com/clojure/brew-install/blob/1.10.1/CHANGELOG.md
