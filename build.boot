(set-env!
 :source-paths    #{"src/cljs"}
 :resource-paths  #{"resources"}
 :dependencies '[[adzerk/boot-cljs              "2.0.0"  :scope "test"]
                 [adzerk/boot-reload            "0.5.2"  :scope "test"]
                 [pandeiro/boot-http            "0.8.3"  :scope "test"]
                 [com.cemerick/piggieback       "0.2.2"  :scope "test"]
                 [org.clojure/tools.nrepl       "0.2.13" :scope "test"]
                 [weasel                        "0.7.0"  :scope "test"]
                 [org.clojure/clojurescript     "1.9.908"]
                 [rum                           "0.10.7"]
                 [tongue                        "0.2.2"]
                 [cljsjs/material-components    "0.13.0-0"]
                 [funcool/potok                 "2.2.0"]
                 [funcool/bide                  "1.4.0"]
                 [binaryage/devtools            "0.9.4"  :scope "test"]
                 [binaryage/dirac               "1.2.16" :scope "test"]
                 [powerlaces/boot-cljs-devtools "0.2.0"  :scope "test"]
                 [laststar/mdc-rum              "0.1.0-SNAPSHOT"]])

(require
 '[adzerk.boot-cljs      :refer [cljs]]
 '[adzerk.boot-reload    :refer [reload]]
 '[pandeiro.boot-http    :refer [serve]]
 '[powerlaces.boot-cljs-devtools :refer [cljs-devtools dirac]])


(deftask build
  "This task contains all the necessary steps to produce a build
   You can use 'profile-tasks' like `production` and `development`
   to change parameters (like optimizations level of the cljs compiler)"
  []
  (comp (speak)
        (cljs)))

(deftask run
  "The `run` task wraps the building of your application in some
   useful tools for local development: an http server, a file watcher
   and a hot reloading mechanism"
  []
  (comp (serve)
        (watch)
        (cljs-devtools)
        (dirac)
        (reload)
        (build)))

(deftask production []
  (task-options! cljs {:optimizations :advanced})
  identity)

(deftask development []
  (task-options! cljs {:optimizations :none}
                 reload {:on-jsload 'laststar.app/init})
  identity)

(deftask dev
  "Simple alias to run application in development mode"
  []
  (comp (development)
        (run)))


(deftask prod
  "Alias to build production in target directory"
  []
  (comp (production)
        (build)
        (target)))

(deftask serve-prod []
  "Serve production build in target directory on port 8080"
  (comp (serve :dir "target" :port 8080)
        (wait)))

