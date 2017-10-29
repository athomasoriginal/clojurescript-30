# Object and Array

Wes took a look at `assignment v. reference` in JavaScript for this lesson.  I used this lesson to explore the equivalent concept in CLJ/S:

0. [Terminology](#terminology)
1. [Symbols](#symbols)
2. [Vars](#vars)
3. [Assignment vs. Binding](#assignment-vs-binding)

# Lesson

I work as a developer professionally and I found this lesson refreshing because it can be easy to forget how these seemingly "basic" concepts are difficult for professional and new developers alike to fully grasp.  This being said, these are notes that helped me sort this out.  Consider the challenge that is explaining the difference between a **key**, **Symbol** and **Var**.  I believe a core challenge here is that someone new is looking at them they would immediatley think they are all just **Strings**  With this said, this lesson is not done and I will likely revisit this several more times to improve and clarify where possible, but for now, I feel it is a good start.


# Resources

Getting into the nitty-gritty's of all this can be very challenging.  What I have provided below is a summation of my understanding of these things.  For stronger technical explainations see the following resources:

* https://clojure.org/reference/vars
* http://fasttrackclojure.blogspot.ca/2010/10/what-no-variables-in-clojure.html
* https://8thlight.com/blog/aaron-lahey/2016/07/20/relationship-between-clojure-functions-symbols-vars-namespaces.html

# Terminology

These are the terms used in Clojure.  They are not interchangeable, rather they each refer to one clearly defined idea.  The spelling is important.  If I get the spelling wrong in this document, I likely messed up.

* Var
* Symbol
* Unqualified Symbol
* Data Type
* resolved Symbol
* value
* root binding
* dynamic thread-local binding
* unbound
* local scope
* global scope
* scope

# Vars

This is how you declare a **Var** in Clojure:

```clojure
(def my-name "Jerald")

;; #'user/my-name
```

* A **Var** is a mutable container
* A **Var** declared with `def` is going to always become global in your namespace
* A **Var** references its _binding_ (_value_).  In the above, `my-name` (_var_) refernces `"Jerald"` (_binding_ or _value_)
* A **Var** can have two types of binding:  _root binding_ and _dynamic thread-local binding_
* A **Var** can have several sub-types: _static_, _dynamic_, _free_, _local_

**The root binding**

All **Vars** and their subtypes have a **root binding**.  A root binding is the initial value associated with your variable.  For example:

```clojure
(def name "Thomas")
```

In the above, "Thomas" is the **root binding**.  Having said this, you do not need to bind a **Var** with a `root value`.  Consider;

```clojure
(def name)
```

In the above example, the **Var** above was not bound a `value` when it was created, so we consider this **unbound**.

**Var search order**


We will look in **local scope** first and than move to the **global scope**

**Re-bind Vars**

```clojure
(def name "Thomas")

(def name "Kate")
```

The variable `"name"` is not destroyed and re-created, but rather the **Var** is re-bound to `"Kate"`.


# Symbols

A **Symbol** is [Data Type](https://clojure.org/reference/data_structures).  Here are some examples of **Symbols**:

```clojure
(def my-name "Jerald")

(+ 1 2)
```

In the above, `+` and `my-name` are **Symbols**.  Comparatively, `1` and `2` are **Numbers** which are a different kind of **Data Type**.  This could be confusing we said in the above section that `my-name` is a **Var**.  The truth is that the reaility is a little more complex and for most of your development process, does not really matter, but keep the following in mind:

* `my-name` is a **Symbol** which references a **Var**
* When we reference a **Symbol** the symbol knows where to find a **Var** and the **Var** is going to return it's binding.

This distinction is going to help when / if you really need to dive into CLJ/S code.  For a great overview of this, please see [this article](https://8thlight.com/blog/aaron-lahey/2016/07/20/relationship-between-clojure-functions-symbols-vars-namespaces.html)


# Assignment vs Binding

When we talk about variables in clojure we do not say **assigning**, we actually say **binding**.  The difference is a tricky one to explain, and it might be more conceptual than anything else.  Suffice to say, use the terminology **binding** and not **assigning**
