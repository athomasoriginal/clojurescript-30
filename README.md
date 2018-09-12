# ClojureScript 30

This is Wes Bos's [JavaScript 30](https://javascript30.com/) challenge written in [ClojureScript](https://clojurescript.org/).

My original goal with learning Clojure was to learn functional programming. The idea was that I would learn Clojure, a functional language, and then apply these skills to JavaScript. This repo represents those efforts.

This repo contains each of the JS30 exercises written in ClojureScript and write-ups on lessons learned. In this spirit, I hope others find this repo and can use it on their own journey towards learning Clojure.

Never heard of Clojure? Skeptical? I encourage you to watch [ClojureScript for Skeptics](https://www.youtube.com/watch?v=gsffg5xxFQI&feature=player_embedded) which really highlights the story elements that make Clojure a compelling language.

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

## Editor Setup

I recommend [Atom](https://atom.io/) or [VSCode](https://code.visualstudio.com/). By default, these are lightweight editors with strong Clojure language support.

Please note that if you ask around, or observe the Clojure community community, [Emacs](https://www.gnu.org/software/emacs/), [vim](http://www.vim.org/) and [Intelij Cursive](https://cursive-ide.com/userguide/paredit.html) are the top dogs. If you already know how to use one of these, please do so, otherwise, stick to a simple editor. Clojure is going to be a learning journey on its own, there is no need to burden yourself with tools that get in the way of just learning the language.

> To emacs/vim people, this is not a slight against your editors. These are amazing editors and people should learn them. When I was learning clojure, I took the time to work with them and the burden on my original goal of learning clj/cljs was too great. Remember what it is like to be new at something - its all about baby steps

With this in mind, here are packages that I recommend adding to Atom to make working with Clojure an even more enjoyable experience:

- [language-clojure](https://atom.io/packages/language-clojure) (syntax highlighting)
- [parinfer](https://atom.io/packages/parinfer) (code formatting)

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

I want to thank [wes bos](https://github.com/wesbos) for providing amazing, free content. This is a truly awesome resource that I believe could be used to help developers learn not just JavaScript, but any language that compiles to JS. For example:

- [Elm](https://elm-lang.org/)
- [Reason](https://reasonml.github.io/)
- [Dart](https://www.dartlang.org/guides/language)
- [ElixirScript](https://elixirscript.github.io/)

and the above list goes on, but the ones I mentioned are the ones that are more interesting to me and likely more well known.

Big thanks to the [clj/cljs community](http://clojurians.net/) which is by far one of the friendliest, most willing and easily accessible communities I have experienced!
