# Countdown Clock

- [Quickstart](#quickstart)
- [Learnings](#learnings)
  - [as->](#as->)
  - [Declare](#declare)

## QuickStart

```bash
clojure -m figwheel.main -b dev -r
```

## Learnings

### as->

Use this when we want to have finer control over where our result goes. For example, if we look at how set interval need to work:

```clojure
; original
(js/Math (.round (/ (- then (-> js/Date (.now))) 1000)))

;; next iteration
(->> (-> js/Date (.now))
     (- then)
     (/ 1000) ;; oops, we need the value from the above to come before :(
     (-> js/Math .round))

;; another iteration
(-> (-> js/Date (.now))
    (/ (- then ??) 1000) ;; okay, we need it to come after next :(
    (-> js/Math .round))

;; good stuff
(as-> (-> js/Date (.now)) time
      (- then time)
      (/ time 1000)
      (-> js/Math (.round time)))
```

At the end, we have something a lot cleaner and easier to read

## Declare

One thing done in the video is wes declared `display-time-left` below the function that uses it. This is no bueno in clojure. But how could we do this if we wanted to?

1.  Move `display-time-left` above `timer`
2.  Declare `display-time-left`

> This is all because a function has to be defined before we use it. This is often why you start reading Clojure from the bottom up.

To perform the second you need to `declare` the function before it is used. This is often reserved for specific scenarios, but I have done it here just as an example:

```clojure
(declare display-time-left)

(defn timer [seconds]
  ;; ...
 (display-time-left seconds))

(defn display-time-left [seconds]
  ;; do stuff)
```
