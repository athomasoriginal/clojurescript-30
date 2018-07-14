# ClojureScript 30

This is Wes Bos's [JavaScript 30](https://javascript30.com/) challenge written in [ClojureScript](https://clojurescript.org/).

The original goal was to become better at functional programming. I would learn Clojure, a functional language, and then apply these skills to JavaScript. What this repo has become is me documenting the process I used to learn ClojureScript. Each repo contains the exercises written in ClojureScript and write-ups on lessons learned.

In this spirit, I hope others find this repo and can use it on their own journey towards learning Clojure.

If you skeptical on why ClojureScript, or what it is, there is an excellent video called [ClojureScript for Skeptics](https://www.youtube.com/watch?v=gsffg5xxFQI&feature=player_embedded) which really highlights the story elements that make `cljs` a compelling language.

# Table of Contents

- [Getting Started](#getting-started)
  - [Install Java](#install-java)
  - [Install Boot](#install-boot)
  - [Editor Setup](#editor-setup)
- [Content Overview](#content-overview)
- [Learning Themes](#learning-themes)
- [Background](#background)
- [Special Thanks](#special-thanks)

# Getting Started

Before you can start working on `clj/cljs` you need to setup your development environment. The following guide is **OSX** only.

## Install Java

see [clojure guide - install java](https://tkjone.github.io/clojure-guide/v1/guide/#Install-Java)

## Install Boot

see [clojure guide - install boot](https://tkjone.github.io/clojure-guide/v1/guide/#Install-Boot)

## Editor Setup

In short, use [Atom](https://atom.io/). or [VSCode](https://code.visualstudio.com/). These are lightweight editors that have strong Clojure language support.

You are going to hear the more commonly suggested editors are [Emacs](https://www.gnu.org/software/emacs/), [vim](http://www.vim.org/) and [Intelij Cursive](https://cursive-ide.com/userguide/paredit.html). These are excellent editors. I used them when I started learning Clojure. The reason I moved away is because the tooling is complex and the editors are opinionated. If your goal is to learn Clojure, choose an editor that lets you focus on just the language. For new developers, this is going to be something like Atom 90% of the time.

> To emacs/vim people, this is not a slight against your editors. These are amazing editors and people should learn them. When I was learning clojure, I took the time to work with them and the burden on my original goal of learning clj/cljs was too great. Remember what it is like to be new at something - its all about baby steps

With this in mind, here are packages that I recommend adding to Atom to make working with Clojure an even more enjoyable experience:

- [language-clojure](https://atom.io/packages/language-clojure)
- [proto-repl](https://atom.io/packages/proto-repl)
- [proto-repl-charts](https://atom.io/packages/proto-repl-charts)
- [ink](https://atom.io/packages/ink)
- [parinfer](https://atom.io/packages/parinfer)

# Content Overview

Each repo represents a stand alone project and will contain its own README where you can learn more about it.

- [01-drumkit](https://github.com/tkjone/clojurescript-30/tree/master/01-drumkit)
- [02-clock](https://github.com/tkjone/clojurescript-30/tree/master/02-clock)
- [03-css-variables](https://github.com/tkjone/clojurescript-30/tree/master/03-css-variables)
- [04-array-cardio](https://github.com/tkjone/clojurescript-30/tree/master/04-array-cardio)
- [05-flex-panels-image-gallery](https://github.com/tkjone/clojurescript-30/tree/master/05-flex-panels-image-gallery)
- [06-ajax-type-ahead](https://github.com/tkjone/clojurescript-30/tree/master/06-ajax-type-ahead)
- [07-array-cardio-2](https://github.com/tkjone/clojurescript-30/tree/master/07-array-cardio-2)
- [08-html5-canvas](https://github.com/tkjone/clojurescript-30/tree/master/08-html5-canvas)
- [09-dev-tool-tricks](https://github.com/tkjone/clojurescript-30/tree/master/09-dev-tool-tricks)
- [10-check-multiple-checkboxes](https://github.com/tkjone/clojurescript-30/tree/master/10-check-multiple-checkboxes)
- [11-html5-video-player](https://github.com/tkjone/clojurescript-30/tree/master/11-html5-video-player)
- [12-key-sequence-detection](https://github.com/tkjone/clojurescript-30/tree/master/12-key-sequence-detection)
- [13-slide-in-on-scroll](https://github.com/tkjone/clojurescript-30/tree/master/13-slide-in-on-scroll)
- [14-object-and-arrays](https://github.com/tkjone/clojurescript-30/tree/master/14-object-and-arrays)
- [15-localstorage-and-event-delegation](https://github.com/tkjone/clojurescript-30/tree/master/15-localstorage-and-event-delegation)
- [16-mousemove-text-shadows](https://github.com/tkjone/clojurescript-30/tree/master/16-mousemove-text-shadows)
- [17-sort-without-articles](https://github.com/tkjone/clojurescript-30/tree/master/17-sort-without-articles)
- [18-string-times](https://github.com/tkjone/clojurescript-30/tree/master/18-string-times)
- [19-unreal-webcam-fun](https://github.com/tkjone/clojurescript-30/tree/master/19-unreal-webcam-fun)
- [20-native-speech-recognition](https://github.com/tkjone/clojurescript-30/tree/master/20-native-speech-recognition)
- [21-geolocation](https://github.com/tkjone/clojurescript-30/tree/master/21-geolocation)
- [22-follow-along-links](https://github.com/tkjone/clojurescript-30/tree/master/22-follow-along-links)
- [23-speech-synthesis](https://github.com/tkjone/clojurescript-30/tree/master/23-speech-synthesis)
- [24-sticky-nav](https://github.com/tkjone/clojurescript-30/tree/master/24-sticky-nav)
- [25-event-delegation](https://github.com/tkjone/clojurescript-30/tree/master/25-event-delegation)
- [26-stripe-follow-along-dropdown](https://github.com/tkjone/clojurescript-30/tree/master/26-stripe-follow-along-dropdown)
- [27-click-and-drag-to-scroll](https://github.com/tkjone/clojurescript-30/tree/master/27-click-and-drag-to-scroll)
- [28-video-speed-controller-ui](https://github.com/tkjone/clojurescript-30/tree/master/28-video-speed-controller-ui)
- [29-countdown-clock](https://github.com/tkjone/clojurescript-30/tree/master/29-countdown-clock)
- [30-whack-a-mole-game](https://github.com/tkjone/clojurescript-30/tree/master/30-whack-a-mole-game)

# Learning Themes

After re-reading and editing all my responses, here are some high level themes I was able to carve out:

1.  Learn to read the Clojure docs

    They are opinionated and consistent. Once you learn how they are written, they are an amazing tool.

2.  Don't dig too deep too fast

    Clojure feels more complex at times then JavaScript. Digging too deep too early leads to information overload. Take it easy.

# Background

Please note that I am a developer by trade, so I went into this with a solid understanding of development patterns and languages and most importantly **how** to learn languages.

If you are new to programming, do not let the above paragraph discourage you. When I wrote this I was, still and likely never will be a programming prodigy. What I do is grind it out and never stop myself from asking questions, no matter how foolish they may seem.

# Special Thanks

I want to thank [wes bos](https://github.com/wesbos) for providing an amazing course that I believe could be used to help developers learn not just JavaScript, but any language that compiles to JS. For example:

- Elm
- ClojureScript
- Dart
- ElixirScript

and the above list goes on, but the ones I mentioned are the ones that are more interesting to me and likely more well known.

Big thanks also goes to [Modern CLJS](https://github.com/magomimmo/modern-cljs) and the whole of the [clj/cljs community](http://clojurians.net/) which is by far one of the friendliest, most willing and easily accesibly communities I have experienced. Treat them with respect!

By following along with Wes Bos's course, and the help of some insanely talented clojurians, I can say that I was able to go from an [early to beginner ](https://zedshaw.com/2015/06/16/early-vs-beginning-coders/) CLJS developer.
