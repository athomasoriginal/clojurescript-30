# 06 Array Cardio

* [Housekeeping](#housekeepings)
* [Quickstart](#quickstart)
* [Lessons Learned](#lessons-learned)
  * [Clojure](#clojure)
    * [Sort](#sort)
    * [%](#%)
    * [re-find](#re-find)
  * [JS Interop](#js-interop)
    * [Data Structures](#aata-structures)

When going through this lesson it became clear that functional programming in JS in different than functional programming in a functional language like Clojure. Can you do functional programming in JS? Yes. However, the language is often times permissive and clunky. I would argue that as a result, it will take more effort to learn functional programming in JS than it would in Clojure. Some of the reasons for this:

1.  Clojure Toolkit

    Out of the box clojure support a considerable number of functions for the functional programmer. To get something similar in JS, you would need to import a 3rd party library like [Lodash](https://lodash.com/) or [Rambda](http://ramdajs.com/).

2.  Language Design

    This one feels unfair, but I am going to say that because Clojure was developed with a specific goal in mind, the language itself _feel_ smoother. This becomes apparent when you start composing the functions in clojure.

## Housekeeping

Before trying out this repo please ensure you have a cljs environment setup. See the [Getting Started Guide](https://github.com/tkjone/clojurescript-30#getting-started)

## Quickstart

Run the following comamnds from the root of the `06-array-cardio` repo

**1. Build and watch the project**

```bash
boot dev
```

## Lessons Learned

### Clojure

#### Sort

Sort was difficult for me. The issue was that at the time, I did not really know how to read the documentation and thus, even they were not helpful to me in the beginning. In this case, where I was lost was that the first arg is expected to be a function. Another point of confusion was exactly what the docs meant by `coll`. In the end, the following resources are what helped me get through it

* [Bringing Order With Clojure's Sortby](https://www.opensourcery.co.za/2017/01/24/bringing-order-with-clojure-s-sort-by/)
* [Callability in Clojure](https://camdez.com/blog/2012/03/21/callability-in-clojure/)
* [Sequences and Collections in Clojure](https://www.brainonfire.net/files/seqs-and-colls/main.html)

#### %

You are going to see `%` used inside the anonymous function. This threw me for a loop at first. For example,

```clojure
;; exxample 1
(goog.object/get % "passed")
(goog.object/get %1 "passed")

;; exxample 2
(goog.object/get %2 "passed")
```

`option 1` is going to take in the first arg provided and `option 2` is going to take in the second arg.

* [ ] TODO - Explore this a little more

#### re-find

* [ ] TODO - Explore this more

### JS Interop

For my solutions, you will notice I am passing the results through `clj->js`. This is because for all of the data structures, if we want to return them to the front end we need to turn them back in `JS`.

* [ ] TODO - Explain what is happening to the JS Data Structures after they are run through CLJ functions

#### Data Structures

Something you will learn early is that JS and CLJ/S data structures do not alwys play nice with one another. For example, if you take a look at our `resources/index.html` in this project you will see we have defined a global JS object:

```javascript
const inventors = [
  { first: 'Albert', last: 'Einstein', year: 1879, passed: 1955 },
  { first: 'Isaac', last: 'Newton', year: 1643, passed: 1727 },
  { first: 'Galileo', last: 'Galilei', year: 1564, passed: 1642 },
  { first: 'Marie', last: 'Curie', year: 1867, passed: 1934 },
  // ...
```

We can access the above in our CLJS file like this: `js/inventors`. Further, we can try to work with it like this:

```clojure
(filter #(<= 1500 (:year %) 1600) js/inventors)
```

You will find that the above is not going to work as you would expect. The reason is because `(:year %)` is the equivalent of trying to to do this: `:year { first: 'Albert', last: 'Einstein', year: 1879, passed: 1955 }`. This will not work as this is not a clojure map, it is a JS object. So how can we work with this? There are three options:

1.  Convert `js/inventors` to a CLJ/S map

    ```clojure
    (def js-inventors->cljs-inventors (js->clj js/inventors))
    ```

    > Note that `js-inventors->cljs-inventors` is just a name I made up. It is a common convention is CLJ/S to use `->`. In JS, you might write it as `js_inventors_object_to_cljs_map`.

2.  Use an interop helper

    ```clojure
    (filter #(<= 1500 (aget % "year") 1600) js/inventors)
    ```

    > Notice that I swapped `(:year %)` with `(aget % "year")`. Also note that `aget` should be used carefully.

3.  Do everything in CLJ/S

    Instead of working with a JS native object, we could just create a CLJS map. I am not doing this because in these examples, the goal is to practice our JS/Interop skills.

## Resources

* [Intro to CLJS](https://github.com/swannodette/lt-cljs-tutorial/blob/master/lt-cljs-tutorial.cljs)

  Solid overview of the CLJS syntax. When I was starting, this was a good quick reference to get me going.

* [Less and Greater Than Operators](http://blog.jenkster.com/2013/11/clojure-less-than-greater-than-tip.html)

  In clojure, the `<` and `>` operators can be confusing because of `prefix` notation. This article helped me think differently about them. Another good resource is [Elements of Clojure](https://leanpub.com/elementsofclojure) which also does an excellent job defining sane defaults and how to use these operators.

* [The Power of Clojure Debugging](https://cambium.consulting/articles/2018/2/8/the-power-of-clojure-debugging)

  Excellent article. Not pertinent to succeeding in lesson, but good to know!

* [JS Interop Examples](https://gist.github.com/jasongilman/3671146)

  This helped give me some ideas about how to transform Data. I remember thinking that I knew what to do is JS, the ideas didn't always clearly translate to what clojure would want me to do.

* [JS Interop Property Access](http://clojurescriptmadeeasy.com/blog/js-interop-property-access.html)

  This was updated after I originally used this resource, but provides an awesome example of how to access object properties.

  **A little history:**

  `aget` was a legacy that you may see in some older code bases. Avoid using it. The best object would be `goog.object`. You can see this [here](https://twitter.com/mfikes/status/882585745424338944). Some people would also recommend [cljs-oopt](https://github.com/binaryage/cljs-oops), but again, I would opt for `goog.object`.

  So would some opt for [cljs-oopt](https://github.com/binaryage/cljs-oops)? `oget/oset` are easy to remember and they are multiple arity, not true for `goog.object`. it also supports things like ocall so you never have to worry about externs.

  The fact `aget` works on objects was described to me as an accident and in reality, it is meant to be used on arrays, not objects - for more on this read [CLJS Issue 128](https://github.com/cljs/api/issues/128).

* [Bringing Order With Clojure's Sortby](https://www.opensourcery.co.za/2017/01/24/bringing-order-with-clojure-s-sort-by/)

  So helpful when trying to understand `sort-by`.

* [Callability in Clojure](https://camdez.com/blog/2012/03/21/callability-in-clojure/)

  Good exploration of callability of functions.

* [Sequences and Collections in Clojure](https://www.brainonfire.net/files/seqs-and-colls/main.html)

  Helps to understand what the docs mean when they reference `coll`

* [Destructuring](http://langintro.com/cljsbook/destructuring.html)

  Good overview of destructuring in CLJ/S

* [Reduce Function](http://langintro.com/cljsbook/reduce_function.html)

  Great overview of reduce.
