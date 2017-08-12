# 02-Setup-Reload-Custom
---

In-depth details for this can be found at [Modern CLJS Part 3](https://github.com/magomimmo/modern-cljs/blob/master/doc/second-edition/tutorial-03.md)

# Requirements

Before trying out this repo please ensure you have a cljs environment setup.  See the [Getting Started Guide](https://github.com/tkjone/clojurescript-30#getting-started)

# Overview

If you have seen the structure of `00` and `01` dirs you noticed that the output of all files in development go into the `target`.  so instead, maybe you want to have all your `cljs` compiled files to go into a specific directory, with a specic name.  To do this in boot, you use a `main.cljs.edn` file.  See the `src` dir of this repo to see how that can be done.

So result is that we get:

```bash
└── target
    ├── index.html
    └── js
        └── main.js
```

instead of:

```bash
└── target
    ├── index.html
    ├── main.js
    └── main.out
```

The biggest thing to take away here is the idea that, like in webpack, you can customize where you code is output to.

# Quickstart

**1.  Build and watch the project**

```bash
boot dev
```

**2.  Sanity Check**

The above step will compile your `cljs` code in the `src` directory and create a new directory in the root of this project called `target`.  Inside of `target` you will find an `index.html` file.  Open that file in a browser and you should see a blank white screen. Open the web developer console and you should see a console log of `Hello World!`.


**3.  Open new terminal tab**

```bash
cmd + t
```

**4.  Move into this repo**

```bash
cd /path/to/setup-reload
```

**5.  Connect to the repl opened in step 1**

```bash
boot repl -c
```

**6.  Lanuch a brepl**

```bash
$ (start-repl)
```

The above will output something like this:

```bash
<< started Weasel server on ws://127.0.0.1:52180 >>
<< waiting for client to connect ... Connection is ws://localhost:52180
Writing boot_cljs_repl.cljs...
```

**7.  Connect your brepl to the browser**

> visit http://localhost:3000/

When you visit your app at the above URL, it is going to connect your brepl to that browser tab instance.  From there we can do super cool things like send commands from our repl to the browser.  For example, go into your repl, the same one where you ran the `(start-repl)` command and run the following:

```bash
$ (.log js/console "Hello, ClojureScript")
```

you should see `Hello, ClojureScript` in the console.
