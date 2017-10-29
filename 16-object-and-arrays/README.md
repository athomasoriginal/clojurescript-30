# Object and Array

Wes took a look at `assignment v. reference` in JavaScript for this lesson.  I used this lesson to explore some of the foundational pieces of clj/s:

1. [Symbols](#Symbols)
2. [Vars](#Vars)
3. [Assignment vs. Binding](#Assignment-vs-Binding)

# Lesson

I work as a developer professionally and I found this lesson refreshing because it can be easy to forget how these seemingly "easy" concepts are actually very challenging to thoroughly understand.  Especially for someone new in general.  If I had to teach someone these concepts I would have to sit with them for a while because I am not sure the best approach.  These notes go deeper than most would care, but understanding that knowing the rules of a language are imperative to mastery, I can't help but think that this is important.  Maybe even from the beginning.  Having said this, it is mind blowing how challening explaining the difference between a **key**, **Symbol** and **Var** is.  Likely because when someone new is looking at them they would immediatley think they are all just **Strings**.  Food for thought.

# Resources

Getting into the nitty-gritty's of all this can be very challenging.  What I have provided below is a summation of my understanding of these things.  For stronger technical explainations see the following resources:

* https://clojure.org/reference/vars
* http://fasttrackclojure.blogspot.ca/2010/10/what-no-variables-in-clojure.html
* https://8thlight.com/blog/aaron-lahey/2016/07/20/relationship-between-clojure-functions-symbols-vars-namespaces.html

# Terminology?

These are the terms used in Clojure.  They are not interchangeable, rather they refer to one clearly defined idea.  The spelling is important.  If I get the spelling wrong in this document, I likely messed up.

* Var
* Symbols
* Data Types
* resolved Symbol
* value
* root binding
* dynamic thread-local binding
* unbound
* local scope
* global scope
* scope

# Symbols

A **Symbol** is [Data Types](https://clojure.org/reference/data_structures).  Here are some examples of **Symbols**:

```clojure
(def my-name "Jerald")

(+ 1 2)
```

In the above, `+` and `my-name` are **Symbols**.  Comparatively, `1` and `2` are **Numbers** which are a different kind of **Data Type**.  How do I know that `my-name` is a **Symbol**?  Is it not a **Var**?  I am going to hold off on the **Var** question for the moment, and show you that `my-name` is a **Symbol** using the `quote` macro:

```clojure
(def my-name "Jerald")

'my-name ;; my-name - Symbol

my-name ;; "Jared" - resolved Symbol
```

When we did `'my-name` we tell clojure to show us the Symbol.  However, writing `my-name` is telling clojure to resolve a Symbol called `my-name` which is mapped to a Var.  In other words, its like this:

> Hey current `namespace`!  I have a **Symbol** called `my-name`, do you have a **Var** mapped to it?  You do?  Awesome!  Please get me that **Var** and I will resolve it.

The end result is we replace the `my-name` Symbol with a **value**.  In this case, the **value** of `my-name` is `"Jared"`.

Please note that a Symbol is not a String.

# Vars

You get a **Var** in Clojure when you do something like this:

```clojure
(def my-name "Jerald")

;; #'user/my-name
```

In the above, you created a **Var**.  In general, we could say that you created a Var called `my-name`, but to be more accurate, you actually created a Var called `user/my-name` with the **value** `"Jerald"` bound to it.  Why the distinction?  Well, it may confuse initially, but I think it is important to understand that the above code is actually creating a **Symbol** called `my-name` which is mapped to a **Var** called `user/my-name` which has a **value** of `"Jerald"`.

This distinction is going to help when / if you really need to dive into CLJ/S code.  For a great overview of this, please see [this article](https://8thlight.com/blog/aaron-lahey/2016/07/20/relationship-between-clojure-functions-symbols-vars-namespaces.html)

I also want to not that we can also refer to the **Vars** value as its binding.  Further, all **Vars** defined using **def** become global - no matter where they are defined.  E.g. even if they are in a deeply nested function.


## What kinds of Vars are there?

* static variable
* dynamic variable
* free variable
* local variable
* global variable

## Root Binding

All **Vars**, both **static** and **dynamic**, can have a **root binding**.  A root binding is the initial value associated with your variable.  For example,

```clojure
(def name "Thomas")
```

In the above, "Thomas" is the **root binding**.  Having said this, you do not need to bind a **Var** with a `root value`.  Consider;

```clojure
(def name)
```

In the above example, the **Var** above was not bound a `value` when it was created, so we consider this **unbound**.


## What order are they searched for?

We will look in **local scope** first and than move to the **global scope**


## Can you re-bind variables?

Yes.  when you do this:

```clojure
(def name "Thomas")

(def name "Kate")
```

The variable `"name"` is not destroyed and re-created, but rather the **Var** is re-bound to the new **value** `"Kate"`.


# Assignment vs Binding

When we talk about variables in clojure we do not say **assigning**, we actually say **binding**.  The difference is a tricky one to explain, and it might be more conceptual than anything else.  Suffice to say, use the terminology **binding** and not **assigning**
