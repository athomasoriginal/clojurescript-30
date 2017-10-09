# ClojureScript 30

This repo contains my answers to Wes Bos's [JavaScript 30](https://javascript30.com/) challenge written in ClojureScript.  Wes Bos has done an amazing job of designing a course full of small and approachable real-world programs you can build without any setup...if your using JavaScript that is ;)

My goal when I set out to do this was just to learn CLJ and functional programming.  After working through a few examples, I was actually suprised that some things which I anticipated would be easy, were actually difficult to execute.  I remember hitting my head against the wall trying to figure out why the simplest piece of code would not run.  In this spirit,  my hope is that people find this repo a helpful learning tool.  My recommended approach to using this repo:

* Start with the [Getting Started](getting-started) guide below
* Run through the first 3 [Modern CLJS](https://github.com/magomimmo/modern-cljs) tutorials
* Head over to [ClojureScript 30](https://javascript30.com/), try to complete each of the tutorials there.  When you get stuck, feel free to come over here for details on how I did it.
* If you came up with a question that you had as an early learner, that I did not have, or an issue you ran into that I did not, it would be amazing if we could compile them here.  This might help us gain insight into how we can better help new developers to join the Clojure community.

Aside from the above, use this a reference to see how you can write CLJS.  Please keep in mind that my code is not the best, but I will work to improve this repo as I keep honing my clojure craft.

I also want to throw the video [ClojureScript for Skeptics](https://www.youtube.com/watch?v=gsffg5xxFQI&feature=player_embedded) out there because it really highlights the story elements that make `cljs` a compelling language.

# Table of Contents

* [Getting Started](#getting-started)
  * [Install Java](#install-java)
  * [Install Boot](#install-boot)
  * [Editor Setup](#editor-setup)
* [Content Overview](#content-overview)
* [Background](#background)
* [Special Thanks](#special-thanks)


# Getting Started

Before you can start working on `clj/cljs` you need to setup your development environment.  This guide is **OSX** only.

## Install Java

see [clojure guide - install java](https://tkjone.github.io/clojure-guide/v1/guide/#Install-Java)

## Install Boot

see [clojure guide - install boot](https://tkjone.github.io/clojure-guide/v1/guide/#Install-Boot)

## Editor Setup

The most common suggestions for editors I have seen for `clj/cljs` are [Emacs](https://www.gnu.org/software/emacs/), [vim](http://www.vim.org/) and [Intelij Cursive](https://cursive-ide.com/userguide/paredit.html).  However, I prefer to keep it simple and use [Atom](https://atom.io/).  I believe that this is likely the easiest introduction for new developers as there is strong community support, excellent `clj/cljs` backing and you just don't have to learn to keyboard.

> To emacs/vim people, this is not a slight against your editors.  These are amazing editors and people should learn them.  When I was learning clojure, I took the time to work with them and the burden on my original goal of learning clj/cljs was too great.  Remember what it is like to be new at something - its all about baby steps

With this in mind, here are packages that I have added to my Atom setup to help working with Clojure an even more enjoyable experience:

* [language-clojure](https://atom.io/packages/language-clojure)
* [proto-repl](https://atom.io/packages/proto-repl)
* [proto-repl-charts](https://atom.io/packages/proto-repl-charts)
* [ink](https://atom.io/packages/ink)
* [parinfer](https://atom.io/packages/parinfer)


# Content Overview

Each repo represents a stand alone project and will contain its own README where you can go to learn more about each one. The first 3 projects are not JavaScript 30 excercises, but [Modern CLJS](https://github.com/magomimmo/modern-cljs).  I recommend starting with the first 3 tutorials.  The rest is [JavaScript 30](https://javascript30.com/).

* [00-setup](https://github.com/tkjone/clojurescript-30/tree/master/00-setup)
* [01-setup-reload](https://github.com/tkjone/clojurescript-30/tree/master/01-setup-reload)
* [02-setup-reload-cleaner](https://github.com/tkjone/clojurescript-30/tree/master/02-setup-reload-custom)
* 03-drumkit
* 04-clock
* 05-css-variables
* 06-array-cardio
* 07-flex-panels-image-gallery
* 08-ajax-type-ahead
* 09-array-cardio-2
* 10-html5-canvas
* 11-dev-tool-tricks
* [12-check-multiple-checkboxes](https://github.com/tkjone/clojurescript-30/tree/master/12-scheck-multiple-checkboxes)

# Background

Please note that I am a developer by trade, so I went into this with a solid understanding of development patterns and languages and most importantly **how** to learn languages.

If you are new to programming, do not let the above paragraph discourage you.  When I wrote this I was, still and likely never will be a programming prodigy.  What I do is grind it out and never stop myself from asking questions, no matter how foolish they may seem.

# Special Thanks

I want to thank [wes bos](https://github.com/wesbos) for providing an amazing course that I believe could be used to help developers learn not just JavaScript, but any language that compiles to JS.  For example:

  * Elm
  * ClojureScript
  * Dart
  * ElixirScript

and the above list goes on, but the ones I mentioned are the ones that are more interesting to me and likely more well known.

Big thanks also goes to [Modern CLJS](https://github.com/magomimmo/modern-cljs) and the whole of the [clj/cljs community](http://clojurians.net/) which is by far one of the friendliest, most willing and easily accesibly communities I have experienced.  Treat them with respect!

By following along with Wes Bos's course, and the help of some insanely talented clojurians, I can say that I was able to go from an [early to beginner ](https://zedshaw.com/2015/06/16/early-vs-beginning-coders/) CLJS developer.
