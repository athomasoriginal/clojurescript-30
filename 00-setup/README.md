# 00-Setup

In-depth details for this can be found at [Modern CLJS Part 1](https://github.com/magomimmo/modern-cljs/blob/master/doc/second-edition/tutorial-01.md)

# Requirements

Before trying out this repo please ensure you have a cljs environment setup.  See the [Getting Started Guide](https://github.com/tkjone/clojurescript-30#getting-started)

# Quickstart

**1.  Build the project**

```bash
boot cljs target
```

**2.  Sanity Check**

The above step will compile your `cljs` code in the `src` directory and create a new directory in the root of this project called `target`.  Inside of `target` you will find an `index.html` file.  Open that file in a browser and you should see a blank white screen. Open the web developer console and you should see a console log of `Hello World!`.
