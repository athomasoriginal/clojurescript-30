# 10 Check Boxes

In this excrecise, you are going to work with

- [Escaping strings](#escaping-strings)
- [Mutable Local Variables](#mutable-local-variables)
- [Control Flow](#control-flow)
- [Tips](#tips)
- [Resources](#resources)

# Requirements

Before trying out this repo please ensure you have a cljs environment setup. See the [Getting Started Guide](https://github.com/tkjone/clojurescript-30#getting-started)

# Quickstart

Run the following comamnds from the root of the `12-check-multiple-checkboxes` repo

**1. Build and watch the project**

```bash
clj -M:dev
```

> `-M` assumes your using a Clojure Tools version greater than `1.10.3.855`.  Not sure what version your on?  Run `clj -h` and you should see output near the top of the output like:

```bash
➜ clj -h
Version: 1.10.3.855 # this is the version your on
```

**2. Sanity Check**

The above step will compile your `cljs` code in the `src` directory and create a new directory in the root of this project called `target`. Inside of `target` you will find an `index.html` file. Open that file in a browser and you should see a blank white screen. Open the web developer console and you should see a console log of `Hello World!`.

## Escaping Strings

As a reminder, the `single-quote` is not something used inside of clojure strings. Therefore, in order to have a qoute in a string, you would need to escape them like so:

```clojure
(p "This is a \"string\"")
```

## Mutable Local Variables

Take a look at the following JS code snippet:

```javascript
function {
  let inBetween = false;

  if (someCondition) {
    htmlElements.forEach( () => {
      if (condition1) {
        inBetween = true
      }

      if (condition2) {
        inBetween = false
      }
    })
  }
}
```

The above is an example of code that has `mutable local variables`. The mutable local variable in this case is `inBetween`. It is a variable inside of our code, that is meant to help us keep track of state.

This is common in imperative programming styles, like JS, but in functional languages like CLJ this is not best-practice. Could you do it? yes, but the language itself makes it a little difficult which brings up a good tip:

> If you are trying to implement something in clojure and the language seems to be fighting you, take a beat. There is a good chance there is a more idiomatic way to do it.

In this case, we are butting our head against a key CLJ feature:

- data is immutable - specifically, lexically scoped variables cannot be mutated e.g. `let`

Turns out the answer to working with mutable local variables is to use `reduce` or `reccur`. Consider the following:

```clojure
(defn handle-event [e]
  (reduce (fn [inBetween element] ;; anonymous function
            (if some-condition
              inBetween
              (not inBetween)))
           false ;; accumulator/val
           html-elements))
```

That's better. We can now keep track of state using the `accumulator`. So we run our `anonymous function` or each item in our list and for each one, when it is done, we return what the state we left off at should look like when we run this function against the next item in the list. This has some interesting affects on our code.

Consider that in the JS example, we can update `inBetween` on the spot. In CLJS example, the state is updated at the very end based on what we return. This means we cannot change the state in the middle of the anonymous function. The result is that we need to perform a few extra steps per `clause`. Which leads us to our next point: Control Flow.

- [Clojure State Management](http://blog.jayfields.com/2011/04/clojure-state-management.html)
- [Global Mutable State](http://www.lispcast.com/global-mutable-state)

## Control Flow

One difficult part for me in understanding control flow is when to use different forms. After this little program, I got to look into when to use `when` vs. `cond`.

**when**

> We want more than one condition to potentially run

```clojure
(fn []
    when condition
      expression 1
    when condition
      expression 1)
```

In the above, both `when` forms are going to be evaluated. This means, if their conditions are both truthy, they both run. Therefore, if you only want one condition to ever run in this set of logic, opt for `cond`, `if` or `case`.

> We want to perform side-effects

```clojure
(fn []
    when condition
      expression 1
      expression 2
    when condition
      expression 1)
```

`when` comes with a `do` form built in, so you can run multiple forms in the expression. This is what inuitively gives developers an understanding of what you are trying to do.

> When our conditions are truthy

I think of `when` as: `When true` or `when false`

**cond**

> We only want one condition to run

```clojure
(fn []
    (cond (condition expression)
          (condition expression)
          (condition expression)))
```

Only one of the above is going to run.

> We want more than two conditions

`if` only provides two conditions: The first is what happens when the condition is true, and the second is what to do when it is false. `cond` is going to let you have as many as you like.

[Mor on cond](http://www.matthewboston.com/blog/understanding-clojure-cond-and-condp/)

> Formatting cond

You are going to notice that our `cond` clauses have more logic than the examples in the clojure docs. You can see this form of styling suggested as an [ok-ish solution](https://github.com/bbatsov/clojure-style-guide#shor-forms-in-cond)

### Tips

- `fn` has an [implicit do](http://clojure-doc.org/articles/language/glossary.html#implicit-do) which means it is going to evaluate each and every `form` it finds.

- There is no concept of `break` as this is an imperative programming thing and not a convention one would follow in functional programming...this is an interesting point because it goes a long way to show how trying to functional program in JS is actually possible, but given that the language is not built for it, you are allowed to do non-functional things much easier. What does this mean? you cannot do this:

```clojure
(fn []
    when condition
        expression 1
        expression 2  ;; <-- break here - don't try to evaluate the next when
    when condition
       expression 1)
```

- **Anonymous Function**

```clojure
(fn [] "body")

#()

#(+ 2 %)

#(+ 2 %1)

#(+ 2 %1 %2)
```

But people also call them `function literals`?

The above two are the same, but this one uses the dispatch macro. Use this when you have a short function - just more compact and easier to read.

- **s-expression**

But people also call them forms?

Turns out, an `s-expression` and a `form` are the same thing. I guess, if we had to make a distinction is that a form has to be a valid s-expression. So, not all forms are `s-expressions`, but all `s-expressions` are `forms`.

## Resources

- [Weird Wonderful Characters of Clojure](https://yobriefca.se/blog/2014/05/19/the-weird-and-wonderful-characters-of-clojure/)
- [Clojure by Example](https://kimh.github.io/clojure-by-example/#if)
- [Clojure Terminology Guide](http://clojure-doc.org/articles/language/glossary.html)
- [Control Flow](https://practicalli.github.io/clojure/basic-clojure/control-flow.html)
- [Namespaces and Clojure](https://stuartsierra.com/2016/clojure-how-to-ns.html)
- [Readable Clojure](http://tonsky.me/blog/readable-clojure/)
