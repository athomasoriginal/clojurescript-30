# ClojureScript 30

This is Wes Bos's [JavaScript 30] challenge written in [ClojureScript].

I started learning Clojure to improve my functional programming skills and strength/diversify my programming mental model. The idea was that I would learn Clojure, a functional language, and then apply these skills to JavaScript.

This repo contains each of the JavaScript 30 exercises written in ClojureScript with write-ups on the lessons I learned while writing them. In this spirit, I hope others find this repo and can use it on their own journey towards learning Clojure.

Never heard of Clojure? Skeptical? I encourage you to watch [ClojureScript for Skeptics] which really highlights the story elements that make Clojure a compelling language.

# Table of Contents

- [Getting Started]
  - [Editor Setup]
  - [Running Projects]
- [Content Overview]
- [Learning Themes]
- [Background]
- [Special Thanks]

# Getting Started

To start working with this project, or even just starting with your own CLJS projects, you need to setup your local machine for ClojureScript development.  Here are the things you need locally and links to where you can find guides:

- [Install Java]
- [Install Clojure CLI Tools]

The above are for **OSX** users.  For non-osx, please see [setup your development environment].

## Running Projects

Each project has it's own README with guides for how to run them.  For the most part, they are all run in the same way, but if you run across a poject and the run instructions are different it's because I am demoing other ways to run a vanilla CLJS project.

## Editor Setup

I recommend [Atom] or [VSCode]. By default, these are lightweight editors with strong Clojure language support.  For a full write up on the text-editors of Clojure see [Clojure Text Editors].

If you're interested in exploring Atom, there is a whole video series for [Setting up Atom for Clojure Development]

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

I want to thank [wes bos] for providing amazing, free content. This is a truly awesome resource that I believe could be used to help developers learn not just JavaScript, but any language that compiles to JS. For example:

- [Elm]
- [Reason]
- [Dart]
- [ElixirScript]

and the above list goes on, but the ones I mentioned are the ones that are more interesting to me and likely more well known.

Big thanks to the [clj/cljs community] which is by far one of the friendliest, most willing and easily accessible communities I have experienced!

[Getting Started]: #getting-started
[Editor Setup]: #editor-setup
[Running Projects]: #running-projects
[Content Overview]: #content-overview
[Learning Themes]: #learning-themes
[Background]: #background
[Special Thanks]: #special-thanks

[Atom]: https://atom.io/
[JavaScript 30]: https://javascript30.com/
[wes bos]: https://github.com/wesbos
[VSCode]: https://code.visualstudio.com/
[Elm]: https://elm-lang.org/
[Reason]: https://reasonml.github.io/
[Dart]: https://www.dartlang.org/guides/language
[ElixirScript]: https://elixirscript.github.io/
[clj/cljs community]: http://clojurians.net/
[ClojureScript]: https://clojurescript.org/
[ClojureScript for Skeptics]: https://www.youtube.com/watch?v=gsffg5xxFQI&feature=player_embedded
[setup your development environment]: https://clojurescript.org/guides/quick-start
[Install Java]: https://www.youtube.com/watch?v=SljDPNwAFOc&list=PLaGDS2KB3-ArG0WqAytE9GsZgrM-USsZA&index=2
[Install Clojure CLI Tools]: https://www.youtube.com/watch?v=5_q5pLoz9b0&list=PLaGDS2KB3-ArG0WqAytE9GsZgrM-USsZA&index=5
[Clojure Text Editors]: https://betweentwoparens.com/clojure-text-editors
[Setting up Atom for Clojure Development]: https://www.youtube.com/watch?v=XJ4DUFjqDuQ&list=PLaGDS2KB3-AqeOryQptgApJ6M7mfoFXIp
