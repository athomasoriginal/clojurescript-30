# 09 Array Cardio 2

* [Housekeeping](#housekeepings)
* [Quickstart](#quickstart)
* [Lessons Learned](#lessons-learned)
  * [Clojure](#clojure)
    * [Variables](#variables)
  * [Resources][#resources]

## Housekeeping

Before trying out this repo please ensure you have a cljs environment setup. See the [Getting Started Guide](https://github.com/tkjone/clojurescript-30#getting-started)

## Quickstart

Run the following comamnds from the root of the `09-array-cardio-2` repo

**1. Build and watch the project**

```bash
boot dev
```

## Lessons Learned

### Clojure

This lesson brought to mind the differences between an intermediate learning a new lang and the early or beginner learning. For intermediate, especially in clojure, it comes down to learning to read the documentation. For example, understanidng that when you see `pred` as an argument, it means a function that returns `true` or `false`. So it's about learning terminology and the way to do things in the specified language.

#### Variables

Start by taking a read about [living without variables](http://thinkrelevance.com/blog/2008/12/01/living-lazy-without-variables). There are some interesting concepts here. variables are the problem coming from JS and moving to clojure. life is variables in JS...or at least until you see that there are other ways...actually the above article, at this
did not help me :( but there is something there.

#### findIndex

This is the last item in array cardio and after asking many questions, the solution was not what I though it would be. To start, there is no `findIndex` in the clojure standard library. In the end though, the answer to solving this problem was a little more about changing ones frame of mind. For example, in clojure, one focuses on choosing the right data structure based on what you need to do with it. Given what we are trying to do, one of the answers ended up being turn into another data structure to work with:

```clojure
(reduce (fn [m v] (assoc m (:id v) v)) {} book-list)
(into {} (map (juxt :id identity)) book-list)
```

Take a look at [this](http://www.spacjer.com/blog/2015/11/24/lesser-known-clojure-keep-and-keep-indexed-functions/)

### Resources

* [Clojure Plan and Simple Slideshow](https://www.slideshare.net/bmabey/clojure-plainandsimple)

  awesome because it gives names to all the different syntax styling. Slide 24 was memorable.
