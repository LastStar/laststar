(ns laststar.routes
  (:require [bide.core :as router]))

(def config
  (router/router [["/:page" :laststar/page]]))
