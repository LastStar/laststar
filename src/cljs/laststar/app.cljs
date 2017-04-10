(ns laststar.app
  (:require
   [rum.core :refer [mount]]
   [laststar.store :as store]
   [laststar.views :as views]))

(defn init []
  (mount (views/page store/main) (js/document.getElementById "container")))
