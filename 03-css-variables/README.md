# 03 CSS Variables

- [Housekeeping](#housekeepings)
- [Quickstart](#quickstart)
- [Lessons Learned](#lessons-learned)
  - [doseq](#doseq)
  - [js interop](#js-interop)
    - [Double Dot](#double-dot)
    - [setProperty](#setproperty)

## Housekeeping

Before trying out this repo please ensure you have a cljs environment setup. See the [Getting Started Guide](https://github.com/tkjone/clojurescript-30#getting-started)

## Quickstart

Run the following comamnds from the root of the `05-css-variables` repo

**1. Build and watch the project**

```bash
clj -M:dev
```

> `-M` assumes your using a Clojure Tools version greater than `1.10.1.708`.  Not sure what version your on?  Run `clj -h` and you should see output near the top of the output like:

```bash
➜ clj -h
Version: 1.10.1.708 # this is the version your on
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

**Double Dot**

It was at this point where I realized the `..` macro might be a solid all around default when working with JS Interop. If we compare this app to some of the other ones I did previously, we can see the benefits:

```clojure
;; option 1
 (let [suffix (.. this -dataset -sizing)
       name   (.. this -name)
       val    (.. this -value)

;; option 2
(let [key-code (.-keyCode e)
```

`Option 1` reads a little more easily, we very clearly understand that it is JS interop, it becomes easier to write as the form becomes more verbose and when comparing / reference to js it feels a little more natural.

**setProperty**

What does it look like to work with `setProperty`?

```clojure
(.. js/document -documentElement -style (setProperty formatted-name formatted-value)
```
