# Key Sequence Detection

In this excercise you will review the following concepts

* [Equality](#equality)
* [Data Structures](#data-structures)
* [Code Cleanup](#code-cleanup)
* [Slice](#slice)
* [Higher Order Functions](#higher-order-functions)

# Requirements

Please ensure you have a clojure environment running locally - see [Getting Started Guide](https://github.com/tkjone/clojurescript-30#getting-started) if you need to set one up.

# Quickstart

Run the following comamnds from the root of the `14-key-sequence-detection`

**1.  Run the projcet**

```bash
boot dev
```

**2.  Visit the app**

http://localhost:3000/

You are going to see a blank screen.  Try typing in the word `secret` :)

# Lessons

Its all about the Data with clojure.  As a result though, you will be asked to think more about the data structures you choose in clojure as opposed to JS which until recently, only really had the `Object` and `Array` as options.  Thus, Figuring out how you are going to interact with the data is important.

This leads to the lesson which is to really question everything you write when you write clojure/script.  Especially if coming from the JS world.  It really is a different way of thinking.


## Slice

The primary goal of this excerise it do the following:

> When the user types in the word `secret` we trigger some event

The solution provided by wes in JavaScript 30 is to use `splice`. However, when you try to find the equivalent of `splice` you will notice it does not exist.  One reason is because splice is going to mutate the original data structure, so your not going to find an equivalent of `splice`.  What we would be looking for is `slice`.  In Clojure you are going to find some gnarly solutions to do a `slice`, unless you are using a vector, which is meant to have `slice` like operations done on it, and in this case the method you are looking for is `subvec` as seen below

```clojure
(swap! keys-pressed subvec keys (- (count keys) 6) (count keys))
```

This is totally fine and works.  However, when we consider what we are trying to do, which is leave off the left most item when our data structure has more than 6 items, we might actually be more interested in a `queue`.


## Queue

In my solution I opted to use a `queue` instead of a `vector` or a `list`.  The reason I chose a `queue` is because we are leaving off the left most items after a minimum of 6 key strokes. Thus, the above `subvec` is replaced with the following:

```clojure
(swap! keys-pressed pop keys-pressed))
```

The fact that this code is less verbose should not be the big take away, the big takeaway is that this is the _ideal_ data structure for the job.

This does not mean that using a vector is wrong, especially given that in this situation, your vector will never really contain a performance hindering number of items.

For more details on this, please see [Joy of Clojure](https://www.manning.com/books/the-joy-of-clojure-second-edition) section 5.2.7


## Code Cleanup

After I finished the first draft of the code, I took a few steps to make this cleaner:

**Atom**

Instead of doing this:

```clojure
(def app-state (atom {:keys-pressed #queue []}))

;; get
(get @app-state :keys-pressed))
```

I changed it to this:

```clojure
(def keys-pressed (atom #queue []))

;; get
@app-state
```

The benefit was that getting the value of `keys-pressed` becomes less code.

**Higher Order Function**

My next question was whether I could make my `keyup-handler` function align to more of the principles of functional programming.  A route that I chose was to turn `keyup-handler` into a `higher order function`.  In this case, it is a function that returns a function.  This is a very simple HOF.

```clojure
(defn create-hof
  [name]
  (fn []
    (p name)))
```

In this example, calling `(create-hof "Terry")` would return another function that when called would console log "terry".  In the case of our function, we created a function that would return the event handler, looking like this:

```clojure
(create-keyup-handler
  [secret f]
  (fn [e]))
```

This helps because now we have an event handler where the secret and the event are customizable.  We are still working with an atom, which could be affected by other functions, so this is not a truly pure function.

**naming conventions**

You could use `set` or `create` to name the function, from conversations on clojurians, one approach is to reserve `set` or `register` for functions which call the `addEventListener`.  In our case, we are creating a function, so we can prefix with the word `create`.



# Resources

[Atom v. Refs](http://tarynsauer.tumblr.com/post/77631451200/clojure-should-i-use-atoms-or-refs)
https://learnxinyminutes.com/docs/clojure/
[Understanding persistent vectors](http://hypirion.com/musings/understanding-persistent-vector-pt-1)
[Why clojure goes fast](http://clojure-goes-fast.com/)
