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
clj -A:dev
```

# Intro

When I originally worked on these, I immediately used boot + boot-reload and the like. However, these tools do have a steep learning curve. I decided to go back and remove some of the tooling. This was made possible by CLJS 1.10 where we now have a much easier time starting these applications from the terminal. All of this to say, you will notice that this repo has three sub directories which all contain the same code in their `src` files: `boot`, `deps-cli` and `deps-cli-figwheel`. The only difference between each of these is the tooling used. For people from JavaScript, think of it as the difference between `npm scripts` or `gulp`.

When I started this project, your options were `boot` and `leiningen`. There is now a third option called `deps cli`. I originally opted to use `Boot`. I am now using `deps-cli` + `figwheel` everywhere. Why? I feel that for a beginner `boot` and `lein` are difficult to understand and configure. You likely want want to write Clojure and not spend time configuring tooling.

My recommendation is to use `deps-cli-figwheel`. Only use the `boot` and `deps-cli` dirs when you want to see how to configure your app with those tool. Thus, they are meant to illustrate what a project using only the new CLI API could look and function like.

With this in mind, please understand the assumptions here:

**Assumptions:**

1.  Keep things simple when teaching a new language

2.  The REPL is more confusing in the beginning then helpful. Consider, you test and rapidly develop with it, but when you write code, its in a file.

3.  Don't introduce too much tooling right away. One of the reasons I liked JS30 is because it approaches education of JS and Web Development without tooling. Just create an HTML file, open it in the browser and your good to go. CLJS is compiled, so its not as simple, but with tools like `deps-cli` it has become a lot easier.

**WARNING:**

`deps-cli` is a little more challenging to use on its own without figwheel during development. The errors are not as visible, the config is a little verbose and, for this particular excercise, it will not serve the `.wav` files correctly.

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
