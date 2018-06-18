# 39 Music Chord

Chord diagrams.

Design Inspiration: https://dribbble.com/shots/591930-ColourChord & https://www.uberchord.com/

## Quick Start

```bash
clojure -m figwheel.main -b dev -r

# or

clj --main cljs.main --repl-opts ro.edn --watch src --compile app.core --repl
```

## TODO

* [x] Barre Chords - styles
* [ ] Cleanup the first attempt and README notes
* [ ] Music Chord generator - enter an array or frets and finger positions and spit out a chord box - needs an input above, and chord generator will be below
* [ ] x and o along the top of each string - styles
* [ ] mute and finger css classes - only user after, but specify that after + 1 should be styled differently
* [ ] naming conventions - `fret position` v. `finger position`
* [ ] horizontal version of the chord diagram
* [ ] Indicate the Fret to start on - roman numerals
* [ ] Think about a strum pattern diagram
* [ ] use numeric chord to indicate the chord - easier to input them and aligns to tab chord notation
* [ ] give each chord the ability to have a title

## Documentation

### Symbols

* "X" >> `:mute`
* "O" >> `:open-str`
* "---" >> `:bar`
* "." >> `:finger`
* "XXX" >> `:rake`

### Representing a Chord

When describing to someone how to play a chord we need to tell them:

1.  The string to play
2.  The fret to play
3.  The finger to use (optional)

The context when writing out in numerical notation to the chord is something like `x32010`. This takes the position of a string first context. The challenge with this, when getting a computer to render these, is with bar notation. So lets look at someways to represent a chord with data:

```clojure
;; x32010

;; 1. Each index in a vector corresponds to the string to play
["E" "A" "G" "D" "B" "E"]

;; 2. We can use `keys`, `numbers` or `vector tuples` to represents something like `x32010`
[:mute 3 2 :open 1 :open]

;; The benefits of this notation is that it aligns with guitar chord tradition so people are familiar with it.  It makes it so we can just put down in an
;; input something like `x32010` and the chord will be properly illustrated.  We can even extend this notation to include finger positions with something ;; like `x3(1)2(2)01(2)0`.  This could be represented in our code like:

[:mute [3 1] [2 2] :open [1 2] :open]

;; The issue comes with when we want to represent bar chords as it does not seem like these are represented in numerical notation very often.  The option ;; to deal with this is
;;
;; 1.  Extend numerical notation
;;
;;     By this we mean take `x3(1)2(2)01(2)0` and extend it to allow the author to write something like `:bar` somewhere inside of it
;;
;;
;; 2.  Use alternative notation
;;
;;     One idea is go from a string first notation, to a fret first.  For example, instead of `x32010` corresponding to strings, we could switch it so
;;     The first position represents a fret.  In this way, we would write
;;
;;     [[x x x x x 1 x]
;;      [x x 2 x x x x]]
;;
;;     As you can see, the above lends itself nicely to more accurately reflecting bar chord.  Each chord diagram is a vector.  Each fret then represents ;;     another vector with 6 positions.  Each position is either nothing, or a number.  The number represents the suggested finger pos.  With this, a bar ;;     could be repped as [x x x :bar :bar :bar] and then we could even provide a suggested finger position like [x x x [:bar 1] [:bar 1] [:bar 1]] which ;;     would be barring with the index finger
;;
;;     Downside here:
;;        1.  data structure is more complex
;;        2.  can't use numerical notation - lose tradition + ease of import / storage
;;
;; 3.  Ignore bar illustrations in Chord Diagrams
;;
;;     This is something I have seen where the authors will have 4 fret positions with a finger pos of 1 and then if you look at these, you just learn that
;;     It means bar chord.  Then, there are also other diagrams that show bar chord.  As a new and early intermediate player I imagine knowing what exactly ;;     to do (does it require a bar or not) is the preference.  
;;
;;
;;  With the above in mind, lets explore expanding the notation to something like an `em shape` with `1:bar33211` or, if we wanted to include the finger ;;  positions we could do something like this `1(1):bar3(3)3(4)2(2)1(1)1(1)`  but we could also leave out the last finger pos because that could be
;;  implied.  The downside is its a little difficult to read, but we could shorten it a bit.  The outstanding question here:
;;
;;  1.  What is a nice format for numerical with bar extension?
;;
;;      1(1):bar 3(3) 3(4) 2(2) 1(1) 1(1)
;;
;;      1(1)(bar) 3(3) 3(4) 2(2) 1(1) 1(1)
;;
;;      Either is nice, but I guess we just have to play with it a little and evaluate based on:
;;
;;         1.  which is easier to parse visually
;;         2.  which is easier to parse from a technical perspective
;;
;; The above would turn into a data structure like this:

[[1 1 :bar] [3 3] [3 4] [2 2] [1 1 :bar] [1 1 :bar]]

;; The way to read the above:
;;
;;   E string, first fret, first finger, bar

;; possible frets

-1 -0 1 2 3 4 5 6

{
  :mute -1
  :open 0
}

[-1 3 2 0 1 0] ;; frets to press

;; This is how we can store frets

{
  :f7 {
    :frets           [-1 3 2 0 1 0]
    :fret-finger-pos [0 1 2 0 3 0]
    :bar             []
  }
}

;; when the front end recieves they can recieve like this
[:mute 3 2 :open 1 :open]

;; need a converter so the above can turn into the following notation

x32010

:numeric-notation

;; at first I thought about storing combined frets, finger pos but then I figured if they are separate it might be more benefitial and since its a functional language, combining them is no trouble.
```

## Learnings

### CSS Grid

**Container**

* Rows and Columns are referred to as `tracks`
* Numbering is based on the line that starts and stops a track.
* implicit rows/columns - `grid-aut-rows/columns` - defines the width of the ones dynamically added
* implicit rows/columns - `grid-auto-flow` tell the new items add whether to be a new `row` or `column`
* units - `fr` - the amount of space left after your css is laid out
* units - `auto` - adjusts to the max size of the content
* height and grids - the default height of an element in a grid is based on the content v. width which is the width of the viewport
* function - `repeat` - examples: `repeat(4, 1fr)` v. `repeat(4 1fr, 2fr)`
* unit - `auto-fit` - exammple - you have `150px` width items - instead of saying - I want 3 columns, just let grid figure out how many columns
* unit - `auto-fill` - good way to tack something to the opposite end of a group - think group of buttons
* function - `minmax` - excellent for handling responsiveness - set the min width something has to be, not enough space and it autocollapses the grid

**Items**

* unit - `span` - tell the grid item how many column tracks / row track to occupy

### Chord Theory

* 3 or more notes played together
* [Guitar Tab Vocabulary](https://www.guitarhabits.com/how-to-read-guitar-tabs-tablature/)
* [Guitar Notation Guide](http://acousticguitar.com/acoustic-guitar-notation-guide/)
* [Improve Guitar Playing](https://www.guitarhabits.com/improve-your-playing-through-slow-thorough-and-full-immersion/)

### Chord Diagram

Chord diagrams show you where to put your fingers on a guitar

**Also Known As**

* Fingering chart
* Chord Diagram
* Chord Chart
* Chord Box

**Terminology**

* string (vertical lines) - technical - `string`, `string-letter`
* finger (black dot or suggested finger) - technical - `dot` + `finger-dot`
* fret (horizontal lines) - technical - `fret`
* O (play string open) - technical - `oh`
* X (mute string) - `ex`
* finger bar (continous black line) `bar`

The internet has come to use asci to represent chords and tabs https://en.wikibooks.org/wiki/Guitar/Tablature_and_Standard_Notation

# Two Thoughts on Programming

From this, I started to see some "patterns" emerge, and maybe these are not actually patterns, but something different entirely.

## Idiomatic Clojure

But really this is more about good programming practices. When I program, I like using variables and often I use them too much. Inline feels ugly and if I just gave something a variable name, I could more easily communicate what it is. The other thing is, does something really need a variable when only used once. Something else that factors into this thinking is also, perhaps learning the idioms of a language, and embracing them, is more important than just blindly bringing your patterns and best practices from language to language. So lets look at one example of how to code something:

**Example 1**

Using the global, but nothing else is

```clojure
(def element (-> js/document (.getElementById "chord-container")))

(set! (-> chord-container .-innerHTML) (chord-box "C Major" (:cm chord-boxes))))
```

**Example 2**

Using the local, so nothing wrong with this, but in this case, things are simple

```clojure
(let [chord-container (-> js/document (.getElementById "chord-container"))]
  (set! (-> chord-container .-innerHTML) (chord-box "C Major" (:cm chord-boxes))))
```

**Example 3**

Just use a `doto` and make things that much cleaner

```clojure
(doto
  (.. js/document (getElementById "chord-container"))
  (-> .-innerHTML (set! (chord-box "C Major" (:cm chord-boxes)))))
```

## Data Oriented Design
