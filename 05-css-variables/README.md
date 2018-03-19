# 05-CSS-Variables

* [Housekeeping](#housekeepings)
* [Quickstart](#quickstart)
* [Lessons Learned](#lessons-learned)
  * [doseq](#doseq)
  * [js interop](#js-interop)
    * [setProperty](#setproperty)

## Housekeeping

Before trying out this repo please ensure you have a cljs environment setup. See the [Getting Started Guide](https://github.com/tkjone/clojurescript-30#getting-started)

## Quickstart

Run the following comamnds from the root of the `05-css-variables` repo

**1. Build and watch the project**

```bash
boot dev
```

## Lessons Learned

### doseq

At the time, the question I had was which one of the following is preferable:

```clojure
;; Option 1
(doseq [input inputs]
  (.addEventListener input "change" handle-update))

(doseq [input inputs]
  (.addEventListener input "mousemove" handle-update))

;; Option 2
(doseq [input inputs]
  (.addEventListener input "change" handle-update)
  (.addEventListener input "mousemove" handle-update))
```

I would go with option two. No need to run a second `for` after the first one.

### js interop

**setProperty**

What does it look like to work with `setProperty`?

```clojure
(.. js/document -documentElement -style (setProperty formatted-name formatted-value)
```
