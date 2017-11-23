(set-env!
  :source-paths #{"src/cljs" "test/cljs"}
  :resource-paths #{"resources"}

  :dependencies '[[org.clojure/clojure         "1.8.0"]
                  [org.clojure/clojurescript   "1.9.854"]
                  [org.clojure/tools.nrepl     "0.2.12" :scope "test"]
                  [proto-repl                  "0.3.1"  :scope "test"]
                  [proto-repl-charts           "0.3.2" :scope "test"]
                  [adzerk/boot-cljs            "2.1.1" :scope "test"]
                  [pandeiro/boot-http          "0.8.3" :scope "test"]
                  [adzerk/boot-reload          "0.5.1" :scope "test"]
                  [adzerk/boot-cljs-repl       "0.3.3" :scope "test"]
                  [crisptrutski/boot-cljs-test "0.3.5-SNAPSHOT" :scope "test"]
                  [com.cemerick/piggieback     "0.2.1" :scope "test"]
                  [weasel                      "0.7.0" :scope "test"]])


(require '[adzerk.boot-cljs             :refer [cljs]]
         '[adzerk.boot-cljs-repl        :refer [cljs-repl start-repl]]
         '[crisptrutski.boot-cljs-test  :refer [test-cljs]]
         '[pandeiro.boot-http           :refer [serve]]
         '[adzerk.boot-reload           :refer [reload]])



(deftask dev
  "Launch immediate feedback development environment for cljs"
  []
  (comp (serve :dir "target")
        (watch)
        (reload)
        (cljs-repl)
        (cljs)
        (target :dir #{"target"})))
