# 39 Music Chord

Chord diagrams.

- [Quick Start](#quick-start)
- [TODO](#todo)
- [Research](#research)
  - [Symbols](#symbols)
  - [Chord Theory](#chord-theory)
  - [Chord Diagram](#chord-diagram)
- [Documentation](#documentation)
  - [Representing a Chord as Data](#representing-a-chord-as-data)
- [Learnings](#learnings)
  - [CSS Grid](#css-grid)
  - [Idiomatic Clojure](#idiomatic-clojure)
  - [Cool Transforms](#cool-transforms)

## Quick Start

```bash
clojure -m figwheel.main -b dev -r

# or

clj --main cljs.main --repl-opts ro.edn --watch src --compile app.core --repl # not working yet
```

and you can also see some live docs like this:

```bash
clj -m dynadoc.core
```

## TODO

- [ ] Represent bar chords - styles
- [x] Represent :muted and :open - styles
- [ ] Represent chord title - both in chord diagram and data structure
- [ ] Cleanup the first attempt and README notes
- [ ] Add Dynadoc
- [ ] Add new orientation - horizontal version of the chord diagram
- [ ] Add new symbol - `position` - fret to start on as roman numeral
- [ ] Add strum pattern diagram
- [ ] Chord generator
- [ ] Chord generator - support for numeric chord notation

## Research

### Symbols

- "X" `:mute`
- "O" `:open`
- "---" `:bar`
- "." `:finger`
- "XXX" `:rake`

### Chord Theory

3 or more notes played together...

- [Guitar Tab Vocabulary](https://www.guitarhabits.com/how-to-read-guitar-tabs-tablature/)
- [Guitar Notation Guide](http://acousticguitar.com/acoustic-guitar-notation-guide/)
- [Improve Guitar Playing](https://www.guitarhabits.com/improve-your-playing-through-slow-thorough-and-full-immersion/)

### Chord Diagram

Chord diagrams show you where to put your fingers on a guitar

**Also Known As**

- Fingering chart
- Chord Diagram
- Chord Chart
- Chord Box

**Terminology**

- string (vertical lines) - `string`, `string-letter`
- finger (black dot or suggested finger) - `dot` + `finger-dot`
- fret (horizontal lines) - technical - `fret`
- O (play string open) - technical - `oh`
- X (mute string) - `ex`
- finger bar (continous black line) `bar`

## Documentation

### Dynadocs

...

### Representing a Chord as Data

When describing to someone how to play a chord we need to tell them:

1.  The string to play
2.  The fret to play
3.  The finger to use (optional)

To communicate the above more efficiently, we have things like chord diagrams and chord notation. Chord notation looks like this `x32010`. I have also seen this written to include finger positions like so: `x3(3)2(2)01(1)0`. However, I have not seen these used to represent bar chords, so not sure how that is communicated.

Different representations of chord diagrams:

```clojure
;; chord numeric notation
"x32010"

;; chord numeric notation + finger positions
"x3(1)2(2)01(2)0"

;; chord numeric notation + finger positions + bars
"1(1)(bar) 3(3) 3(4) 2(2) 1(1) 1(1)"

;; Each index in a vector corresponds to the string to play
["E" "A" "G" "D" "B" "E"]

;; Combination of `keys`, `numbers` to represent something like `x32010`
[:mute 3 2 :open 1 :open]

;; Using vector tuples to include finger positions
[:mute [3 1] [2 2] :open [1 2] :open]

;; vector of vectors.  sub-vectors position: string, fret, finger (maybe bar)
[[1 1 :bar] [3 3] [3 4] [2 2] [1 1 :bar] [1 1 :bar]]

;; could use negative numbers to represent :mute and :open
-1 -0

;; Possible structure of a chord
{
  :f7 {
    :frets           [-1 3 2 0 1 0]
    :fret-finger-pos [0 1 2 0 3 0]
    :bar             []
  }
}
```

## Learnings

## Idiomatic Clojure

Here are three different ways of doing some JS interop things.

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

### Cool Transforms

Early on, when I had a different HTML structure, I had a need to take a vector of vectors and combine the first two vectors in the vector.

```clojure
[1 2 3 4 5 6] -> [[1 2] 3 4 5 6]
```

Here are some examples of the ways I, and fellow Clojurians, worked with this transform.

```clojure
(into (vector ((juxt first second) [1 2 3 4 5 6])) (drop 2 [1 2 3 4 5 6]))

(apply into ((juxt (comp vector (partial take 2)) (partial drop 2)) [1 2 3 4 5 6]))

(reduce conj (rseq (split-at 2 [1 2 3 4 5 6])))

(->> chord
     (split-at 2)
     (rseq)
     (reduce conj)
     (map-indexed vector))
```
