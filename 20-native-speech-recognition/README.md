# Native Speech Recognition

Customize the HTML5 Video Player with CLJS.

- [Quickstart](#quickstart)
- [Learnings](#learnings)
  - [JS Hot Reloading](#js-hot-reloading)
  - [Global Functions](#global-functions)

## Quickstart

- Build and watch the project

  ```bash
  clj -A:dev
  ```

Visit site at `http://localhost:3000`

## Learnings

### JS Hot Reloading

This would be a good example of how to perform hot reloading with vanilla javascript as you get an error every time you hit save in this project.

### Global Functions

In this lesson, all the global functions had to be in the HTML file vs my CLJS file. I don't actually think this is required, but to keep this exercise focused, I have left it as such. Would like to go back and change this up. The goal is to define `words` and `speech` object in the CLJS file.
