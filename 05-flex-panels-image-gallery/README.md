# 05 Flex Panels Image Gallery

- [Housekeeping](#housekeepings)
- [Quickstart](#quickstart)
- [Lessons Learned](#lessons-learned)
  - [Double Dot](#double-dot)
  - [Parens](#parens)

## Housekeeping

Before trying out this repo please ensure you have a cljs environment setup. See the [Getting Started Guide](https://github.com/tkjone/clojurescript-30#getting-started)

## Quickstart

Run the following comamnds from the root of the `07-flex-panels-image-gallery` repo

**1. Build and watch the project**

```bash
clj -M:dev
```

> `-M` assumes your using a Clojure Tools version greater than `1.10.3.855`.  Not sure what version your on?  Run `clj -h` and you should see output near the top of the output like:

```bash
➜ clj -h
Version: 1.10.3.855 # this is the version your on
```


## Lessons Learned

### Double Dot

If you review the [Double Dot](https://clojure.github.io/clojure/clojure.core-api.html#clojure.core/..) macro's documentation, they even provide the reasoinnig which is `easier to read, write etc`. Why note this? Because the clojure docs are truly a wealth of info, they just sometimes, often times, take a little bit of practice to learn where that treasure lives.

### Parens

Another big reminder from this is always watch your parens. Likely that if the syntax looks correct, your parens are probably fucking with you.
