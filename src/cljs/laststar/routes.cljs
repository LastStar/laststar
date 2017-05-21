(ns laststar.routes
  (:require [bide.core :as router]))

(def config
  (router/router [["/" :page/intro]
                  ["/about" :page/about]
                  ["/people" :page/people]
                  ["/contact" :page/contact]
                  ["/technology" :page/technology]]))
