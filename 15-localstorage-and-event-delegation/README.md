# 15 Local Storage

For this excercise, I focused on the JS interop aspects of CLJS. By this I mean that I did not use immutable datastructures in favor of things like `array.push`. The goal was to see how this would feel. With this said, here are some of the larger topics touched on:

- [apply](#apply)
- [map-indexed](#map-indexed)
- [how to not do HTML in CLJS](#how-to-not-do-html)

# Requirements

Please ensure you have a clojure environment running locally - see [Getting Started Guide](https://github.com/tkjone/clojurescript-30#getting-started) if you need to set one up.

# Quickstart

Run the following comamnds from the root of the `17-localstorage-and-event-delegation`

**1. Run the projcet**

```bash
clj -A:dev
```

**2. Visit the app**

http://localhost:3000/

# Apply

Wes used `array.join('')` in the video series. There is nothing quite like this in clojurescript. We can do something similar using reduce or apply. I opted to use apply because I have not had the opportunity thus far. This is what my code looked like

```clojure
(apply str (map-indexed #(make-plate-html %1 %2) plates))
```

Lets break the above down in the order they are evaluated so its a little easier to grasp:

```clojure
;; map over each item in plates (array) and for each item return an HTML string
(map #(make-plate-html (.-text %1)) plates))

;; the above returns something like this - I am writing HTML as a placeholder for
;; the list item which is actually in there
["HTML" "HTML" "HTML"]

;; think:  apply the function str over each element which is the equivalent of
;; writing ( str "HTML" "HTML" "HTML")
(apply str ["HTML" "HTML" "HTML"])
```

# map-indexed

I used `map-indexed` because I needed to know the current index of the current element I was mapping over. JS provides this natively with the map function, but in CLJ you have to explicitly use `map-indexed`.

# How to not do HTML

You will notice in the `make-plate-html` function I have a big-ass string concatenation fiesta going on. I would strongly suggest never doing this like I have in a production app. This code is difficult to maintain, hard to read, and likely a security risk. I did this as I have just complete the excercise without introducing a third party library. Having said this, in a production app I would just use something like [hiccup](https://github.com/weavejester/hiccup) to handle this in a smart way.

# Resources

http://www.spacjer.com/blog/2014/09/12/clojurescript-javascript-interop/
