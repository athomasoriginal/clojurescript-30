# Drumkit

## Quickstart

- Build and watch the project

  ```bash
  clj --main cljs.main --repl-opts '{:static-dir ["." "out" "resources"]}' --watch src --compile drumkit.core --repl
  ```

## Development Notes

> please note that `deps.edn` is only for managing dependencies and classpath. Also note that in intermediate, the path is `<script src="out/main.js"></script>`. I also know that it seems like there are some scary parts to this command, but really we are just telling the cli that we want it to also look into specified static dirs here `{:static-dir ["." "out" "resources"]}`. The browser CLI. [Here are the options that we can pass to repl-opts](https://github.com/mfikes/clojurescript-site/blob/issue-187/content/reference/repl-options.adoc#static-dir)

if we want to read about how the cljs.main tool actually works, can be found [here](https://github.com/clojure/clojurescript/blob/7a5a65cb4d1eeca63746dd21e138ee9e56676ae4/src/main/clojure/cljs/repl/browser.clj)/. We don't need to specify the output dir if I am running `--compile` - it defaults to `out` in this case.

If we are wondering why `.wav` files cannot be found, its because of this section https://github.com/clojure/clojurescript/blob/master/src/main/clojure/cljs/repl/browser.clj#L31
