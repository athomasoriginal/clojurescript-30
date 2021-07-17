# String Times

I liked this lesson because it provided an opportunity to explore how strings and numbers respond to math in Clojure.

- [Quick Start](#quick-start)
- [Multiplication and Strings](#multiplication-and-strings)

# Requirements

Please ensure you have a clojure environment running locally - see [Getting Started Guide](https://github.com/tkjone/clojurescript-30#getting-started) if you need to set one up.

# Quick Start

Run the following comamnds from the root of `20-string-times`

**1. Run the projcet**

```bash
clj -M:dev
```

> `-M` assumes your using a Clojure Tools version greater than `1.10.3.855`.  Not sure what version your on?  Run `clj -h` and you should see output near the top of the output like:

```bash
➜ clj -h
Version: 1.10.3.855 # this is the version your on
```


**2. Visit the app**

http://localhost:9000/

# Multiplication and Strings

In Clojure, you cannot multiple a string and a number. Whay even make this a note? Because in JS you can and it is a source of great pain if you are not careful. For example:

```clojure
(* "5" 5)
```

The above is going to result in a `ClassCastException` error. What would happen in JS?

```javascript
var hot = "5" * 5; // 25

typeof hot; // 'number'
```

So in JS, it will actually result in a number. Not so much with Clojure. So what does this mean? Well, we have to be sure that we are always doing math against numbers. So how do we **convert a string to a number**?

From my research and adivce from **clojurians** the suggested approach is to use `Integer/parseInt` / `Double/parseDouble`. Another approach is to use `cljs.reader/read-string`. Regarding the later, the advice is to not opt for `read-string` first because it does some interesting things under the hood that will not be what you are expecting. There is also the fact that there are no less than three `read-string` functions in clojure, so it is confusing. Here is an article that outlines [some of the differences](https://coderwall.com/p/8krwqg/clojure-script-compatibility-magic).
