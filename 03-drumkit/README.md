# 03-Drumkit

---

* [Housekeeping](#housekeepings)
* [Lessons Learned](#lessons-learned)
  * [Overview](#Overview)
  * [Variables](#variables)
  * [Protocols](#protocols)
  * [Pro Tips](#pro-tips)
* [Resources](#resources)

# Basic Vs. Intermediate

I split the two up and the **only** difference between the two repos is the tooling. I wanted to illustrate what a project using only the new CLI API could look and function like. This is a simpler workflow for new developers to CLJS.

# Education Decisions

When I originally worked on these, I immediatley used figwheel and the like, but give that these are meant to be a little step by step guide on some of the simplest ways to work with CLJ, I decided to go back and remove some of the tooling. This was made possible by CLJS 1.10 where we now have a much easier time starting these applications from the terminal. So, instead of immediatley diving into tooling, lets keep things as simple as possible.

Assumptions:

1.  Keep things simple when teaching a new language

2.  The REPL is more confusing in the beginning then helpful. Consider, you test and rapidly develop with it, but when you write code, its in a file.

3.  Don't introduce too much tooling right away. One of the reasons I liked JS30 is because it does something really well - it approaches education of JS and Web Development without tooling. Just open a file, open in the browser, your good to go. CLJS is compiled, so its not that easy, but it does not mean we have to do too much with tooling.

I used macros.clj, but I honestly don't think that is a good idea for people learning, especially because its only saving a few lines.

# Housekeeping

Before trying out this repo please ensure you have a cljs environment setup. See the [Getting Started Guide](https://github.com/tkjone/clojurescript-30#getting-started)

# Quickstart Basic

Run the following comamnds from the root of the `03-drumkit` repo

**1. Build and watch the project**

```bash
clj --main cljs.main --repl-opts '{:static-dir ["." "out" "resources"]}' --watch src --compile drumkit.core --repl
```

For basic you have spend some time setting things up for a project.

> please note that `deps.edn` is only for managing dependencies and classpath. Also note that in intermediate, the path is `<script src="out/main.js"></script>`. I also know that it seems like there are some scary parts to this command, but really we are just telling the cli that we want it to also look into specified static dirs here `{:static-dir ["." "out" "resources"]}`. The browser CLI. [Here are the options that we can pass to repl-opts](https://github.com/mfikes/clojurescript-site/blob/issue-187/content/reference/repl-options.adoc#static-dir)

if we want to read about how the cljs.main tool actually works, can be found [here](https://github.com/clojure/clojurescript/blob/7a5a65cb4d1eeca63746dd21e138ee9e56676ae4/src/main/clojure/cljs/repl/browser.clj)/. We don't need to specify the output dir if I am running `--compile` - it defaults to `out` in this case.

If we are wondering why `.wav` files cannot be found, its because of this section https://github.com/clojure/clojurescript/blob/master/src/main/clojure/cljs/repl/browser.clj#L31

# Quickstart Intermediate

Run the following comamnds from the root of the `03-drumkit` repo

**1. Build and watch the project**

```bash
boot dev
```

**2. Sanity Check**

The above step will compile your `cljs` code in the `src` directory and create a new directory in the root of this project called `target`. Inside of `target` you will find an `index.html` file. Open that file in a browser and you should see a blank white screen. Open the web developer console and you should see a console log of `Hello World!`.

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

* Easier for others to read and understand

* A little complex for such a small program

I would love to delve into this a little further. See [This Commit](https://github.com/tkjone/clojurescript-30/commit/148a5744caa180c948598cf9234c4928939f7e9e) for the example of the code that was there, which I later removed

## Naming Conventions

`core.cljs` is a naming convention. This is the equivalent of `drumkit/index.js` in JS world. You could call it anything you wanted really.

## defs

what happens when a variable is assigned in global and than a parameter has the same name? Can you write them with let's instead?

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

What are the differences between the different ways to format strings?

## Overview

> This was actually annoying as fuck. Where is the context? The resources are not well documented, there is no, here is something bsic to get you going.

The above are my initial thoughts after completing this excercise. Not sure exactly what I meant by the above as I am revisiting this over a month after initially writing it.

[Initial Commit](https://github.com/tkjone/clojurescript-30/commit/34b151e6a2d0fc86fe3f6b34ee0fefaee88c5b94)

## Variables

# Resources

* https://www.andrewhfarmer.com/webpack-hmr-tutorial/
* https://github.com/bhauman/lein-figwheel
