# 11 Custom HTML5 Video Player

Customize the HTML5 Video Player with CLJS.

- [Quickstart](#quickstart)
- [Learnings](#Learnings)
  - [Ternary v. If](#ternary-v-if)
  - [Imperative v. Functional](#imperative-v-functional)
  - [JS Hot Reloading](#js-hot-reloading)

## Quickstart

- Build and watch the project

  ```bash
  clj -A:dev
  ```

Visit site at `http://localhost:3000`

## Learnings

### Ternary V If

In Clojure, the idiomatic way of getting this functionality is an If statement.

## Imperative v Functional

I was required to write this:

```clojure
(defn toggle-play []
  (if (.-paused video)
    (.play video)
    (.pause video)))
```

And as I was looking at it I could not help but think that this can't be the best way. The issue I see is that this function:

- Accesses stuff from the global namespace - is there a way to not do this, or is it okay? Not everything has to be reusable, but this just does not feel functional.

This brings up some bigger questions like:

- When should I be using side effects and how can I know if I am using them too often or not enough?
- When is something just not functional?
- When should something be pure?
- Are we supposed to be separating functions in some way that have side effects?

Part of the answer to this is knowing that not everything has to be reusable and that you are building programs so there are going to be domain specific things as part of this. If this is the case, we can start to make it data structure oriented and then the functions are acting on these data structures

One better way for the above might be to just

```clojure
(map paused? ("play", "pause")) ;;returns true or false and than based on this we toggle the play or not
```

The above seems nicer, aside from the fact that map is not correct, because we now have a function that only cares if it finds a true or false in our domain of the video player app. I was inspired to do the above in the [predicates](https://github.com/mynomoto/lt-clojure-tutorial/blob/master/conditionals.clj) section of the above

### JS Hot Reloading

If you are running this app and live coding it you are going to see that hot reloading is not really working. This is because to make something hot reloadable you have to write your code to be hot reloadable.

# Musings

I am keeping these around because I had issues with these so others might as well and they could be good topics to discuss in the future.

- What do people like when it comes to `this-as`? From a style convention or a when to use perspective?
- At which point do you make the anonymous function its own named function?
- when grabbing DOM elements, which one would be preferred `defn` or `def`
