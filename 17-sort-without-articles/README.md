# Sort Without Articles

This lesson goes over things like regexes and comparators

* [Sorting](#sort)
* [Strip](#strip)
* [Regex](#regex)

# Requirements

Please ensure you have a clojure environment running locally - see [Getting Started Guide](https://github.com/tkjone/clojurescript-30#getting-started) if you need to set one up.


# Quickstart

Run the following comamnds from the root of the `18-mousemove-and-text-shadows`

**1.  Run the projcet**

```bash
boot dev
```

**2.  Visit the app**

http://localhost:3000/


# Sorting

Sorting is not difficult in JS or Clojure, but clojure definitely provides a more terse syntax.

```clojure
(def bands ["The Plot in You", "The Devil Wears Prada", "Pierce the Veil"])

(sort bands)
```

The above will alphabetically sort things for you.  In JS you would have to pass a [comparator](https://clojure.org/guides/comparators) (a function that tells sort how to sort things).  Having said this, the task we are trying to execute does require we pass `sort` a `comparator` even in clojure.


# Regex

Regex always feels oddly different when I write them in clojure, so I wanted to note how to write case-insensitive regexes here:

```clojure
 #"(?i)a |the |an "
```

As you can see, you prefix with `(?i)`. Most everything else about regexes is usually the same, but you get odd moments like the above where your just left wondering.


# Strip

This can also be known as `trimming`, but the idea is the same:  Remove a type or series of characters from a string.  The most common case is usually `trimming` whitespace.  This is likely why clojure actually provides a `trim` function.
