# Speech Synthesis

## Quickstart

Run the following commands from the root of the `25-speech-synthesis` repo

* **1. Build and watch the project**

```bash
clj --main cljs.main --repl-opts '{:static-dir ["." "out" "resources"]}' --watch src --compile speech-synthesis.core --repl
```

## Learnings

### aset and aget

[Great article that should be read](https://clojurescript.org/news/2017-07-14-checked-array-access)

### CLJ Tool

This is the first project where I worked start to finish using the CLJ tool. Things to keep in mind:

* Refreshing the browser

  Using Boot w/ reload or figwheel means that your files are being watched for changes and when changes are seen, your browser automatically refreshes to pickup these changes. However, if we are just using the CLJ tool, your browser is not automatically refreshed. You have to do this manually. This is not a big deal, just an FYI.

* Understanding Errors

  When you make mistakes in your code, and boot w/ reload and figwheel properly setup, both systems give you some indication of what you might be doing wrong. This is not exactly how it works with the CLJ tool. Clojure will tell you whats wrong, but it logs this to a file in your `out` directory called `watch.log`. For intermediate developers this is fine, but this is actually a bit of a deal breaker for new developers. It is challenging enough to understand the CLJS errors, but now you have to go find the file, try to read through a wall of unformatted text and then decipher. I am sure there is something we can do about this, but that would like be an extra step or configuration and at that point, we might as well use figwheel.

  This is not a critique of the CLJ Tool, just something to watch out for and consider as a new learner.
