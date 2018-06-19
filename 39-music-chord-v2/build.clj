(require
  '[cljs.build.api :as api]
  '[figwheel-sidecar.repl-api :as repl-api]
  '[dynadoc.core :as dynadoc])

;; to start dynadoc with live-reloading:
;; clj build.clj run-docs

;; to start dynadoc with exporting enabled:
;; clj build-clj build-docs

(defmulti task first)

(defmethod task :default
  [_]
  (let [all-tasks  (-> task methods (dissoc :default) keys sort)
        interposed (->> all-tasks (interpose ", ") (apply str))]
    (println "Unknown or missing task. Choose one of:" interposed)
    (System/exit 1)))

(def dynadoc-port 5000)

(defmethod task "run-docs"
  [_]
  (dynadoc/start {:port dynadoc-port :join? false})
  (repl-api/start-figwheel! {:all-builds [{:id "dev"
                                           :figwheel true
                                           :source-paths ["src"]
                                           :compiler {:main          'app.dynadoc
                                                      :optimizations :none
                                                      :output-to     "resources/dynadoc-extend/main.js"
                                                      :output-dir    "resources/dynadoc-extend/main"
                                                      :asset-path    "/main"}}]}))

(defmethod task "build-docs"
  [_]
  (println "Building ClojureScript...")
  (api/build "src" {:main          'app.dynadoc
                    :optimizations :simple
                    :output-to     "resources/dynadoc-extend/main.js"})
  (dynadoc/start {:port dynadoc-port}))

(task *command-line-args*)

