(set-env!
  :source-paths #{"src/cljs" "src/cljc" "test/cljc"}
  :resource-paths #{"resources"}

  :dependencies '[[org.clojure/clojure       "1.8.0"]
                  [org.clojure/clojurescript "1.9.854"]
                  [org.clojure/tools.nrepl   "0.2.12"]
                  [proto-repl                "0.3.1"]
                  [proto-repl-charts         "0.3.2"]
                  [adzerk/boot-cljs          "2.1.1"]
                  [pandeiro/boot-http        "0.8.3"]
                  [adzerk/boot-reload        "0.5.1"]
                  [adzerk/boot-cljs-repl     "0.3.3"]
                  [adzerk/boot-test          "1.2.0" :scope "test"]
                  [com.cemerick/piggieback   "0.2.1"]
                  [weasel                    "0.7.0"]])


(require '[adzerk.boot-cljs      :refer [cljs]]
         '[pandeiro.boot-http    :refer [serve]]
         '[adzerk.boot-reload    :refer [reload]]
         '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]])


(deftask dev
  "Launch immediate feedback development environment for cljs"
  []
  (comp (serve :dir "target")
        (watch)
        (reload)
        (cljs-repl)
        (cljs)
        (target :dir #{"target"})))


(deftask run
  "Run the clj portion of the project."
  [a args ARG [str] "the arguments for the application."]
  (require '[events.core :as app])
  (apply (resolve 'app/-main) args))


(require '[adzerk.boot-test :refer [test]])
