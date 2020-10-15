# Native Speech Recognition

Customize the HTML5 Video Player with CLJS.

- [Quickstart](#quickstart)
- [Learnings](#learnings)
  - [JS Hot Reloading](#js-hot-reloading)
  - [Global Functions](#global-functions)

## Quickstart

```bash
clj -M:dev
```

> `-M` assumes your using a Clojure Tools version greater than `1.10.1.708`.  Not sure what version your on?  Run `clj -h` and you should see output near the top of the output like:

```bash
➜ clj -h
Version: 1.10.1.708 # this is the version your on
```


Visit site at `http://localhost:3000`

## Learnings

### JS Hot Reloading

This would be a good example of how to perform hot reloading with vanilla javascript as you get an error every time you hit save in this project.

### Global Functions

In this lesson, all the global functions had to be in the HTML file vs my CLJS file. I don't actually think this is required, but to keep this exercise focused, I have left it as such. Would like to go back and change this up. The goal is to define `words` and `speech` object in the CLJS file.
