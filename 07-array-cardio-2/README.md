# 07 Array Cardio 2

- [Housekeeping](#housekeepings)
- [Quickstart](#quickstart)
- [Lessons Learned](#lessons-learned)
  - [Clojure](#clojure)
    - [Variables](#variables)
  - [Resources][#resources]

## Housekeeping

Before trying out this repo please ensure you have a cljs environment setup. See the [Getting Started Guide](https://github.com/tkjone/clojurescript-30#getting-started)

## Quickstart

Run the following comamnds from the root of the `09-array-cardio-2` repo

**1. Build and watch the project**

```bash
clj -A:dev
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

- [Clojure Plan and Simple Slideshow](https://www.slideshare.net/bmabey/clojure-plainandsimple)

  awesome because it gives names to all the different syntax styling. Slide 24 was memorable.

;; I did quite a bit of research to find the answer to the above and while doing this ran into laziness. Always
;; laziness. I kind of skirt over this, but the topic became an annoying misconception as of activity 8.
;; which is when I found this article http://thinkrelevance.com/blog/2008/12/01/living-lazy-without-variables and what
;; caught my eye is life without variables. variables are the problem coming from JS and moving to clojure. life
;; is variables in JS...or at least until you see that there are other ways...actually the above article, at this
;; did not help me :( but there is something there.

;; eventually I realized that this is an interesting question for several reasons....my original question on the slack
;; channel:

;; Hey all, sorry in advance, this question is long.
;
; Before I dive into a larger question, I was looking for an idiomatic way to `findIndex` on a list of maps.
;
; `(books ({id: 23} {id: 45})) ;`
;
;
; and noticed that findIndex is not part of the core library. This lead me to read through a section in `Programming Clojure` about choosing the correct data structure. To summarize, the idea seems to be that if you are going to be searching for an item, for example, using a function like `findIndex` (JS) against some data structure, you should be using a set.
;
; The idea is to communicate intent. This is why, to my understanding, `findIndex` is not part of the core library.
;
; What happens though when you are provided with a list, with a large dataset, and you need to use something like `findIndex` on it? Eventually, with large enough data, this becomes a performance issue point. Which goes against idiomatic clojure - do clojurists just sigh and move on, or is there a better way to handle this kind of situation? For example, you are writing clojurescript, and receive a a JS array of objects...this would become a list (as far as I know) .
;
; To loop back to the original question - what does idiomatic `findIndex` look like.
;
; Here is what I did to implement `find` like functionality:
;
; `(first (filter #(= (.. % -id) 823423) js/comments))`

;; `seancorfield` was kind of enough to provide an answer. In which, I understood it as follows. If you are looking
;; things up by a unique key, then a hash map is best `(books {23 {:id 23 :title "..."} 45 {:id 45 :title`.
;; original data structure not like the above? transform the data structure you are provided into another data structure.
;; in this case, you could do it like this:
;; `(reduce (fn [m v] (assoc m (:id v) v)) {} book-list)`
;; `(into {} (map (juxt :id identity)) book-list)`
;;
;; Right. Because `findIndex` is O(n) and slow if you are going to be repeating it. - `seancorfield`

;; But when I researched a little, this seems to be an odd ask in clojureland...where as in JS land, its a method on `Array`
;; http://www.spacjer.com/blog/2015/11/24/lesser-known-clojure-keep-and-keep-indexed-functions/
