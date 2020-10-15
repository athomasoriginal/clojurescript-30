# 13 Slide in on scroll

I found that this lesson did not contain anything new, but it did build upon concepts explored earlier

- [Higher Order Functions](#higher-order-function)
- [doseq](doseq)

# Requirements

Please ensure you have a clojure environment running locally - see [Getting Started Guide](https://github.com/tkjone/clojurescript-30#getting-started) if you need to set one up.

# Quickstart

Run the following comamnds from the root of the `15-slide-in-on-scroll`

**1. Run the projcet**

```bash
clj -M:dev
```

> `-M` assumes your using a Clojure Tools version greater than `1.10.1.708`.  Not sure what version your on?  Run `clj -h` and you should see output near the top of the output like:

```bash
➜ clj -h
Version: 1.10.1.708 # this is the version your on
```


**2. Visit the app**

http://localhost:3000/

## Higher Order Functions

In this case we are using the global function `debounce` and passing our `check-slide` function to it.

## doseq

We are not modifying the collection, we are only getting side effects. `for` can also be used for side effects, but it is going to return another list and this is not what we want to happen, so we use `doseq`. `for` is what's known as a `list comprehension`.
