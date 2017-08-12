(set-env!
  :source-paths #{"src/cljs"}
  :resource-paths #{"static"}

  :dependencies '[[org.clojure/clojure       "1.8.0"]
                  [org.clojure/clojurescript "1.9.854"]
                  [adzerk/boot-cljs          "2.1.1"]])

(require '[adzerk.boot-cljs :refer [cljs]])
