(ns laststar.app
  (:require
   [rum.core :refer [mount]]
   [potok.core :as ptk]
   [bide.core :as rtr]
   [laststar.store :as store]
   [laststar.routes :as routes]
   [laststar.events :as events]
   [laststar.views :as views]))

(defn init []
  (rtr/start!
   routes/config
   {:default     :laststar/intro
    :on-navigate (fn [name params query]
                   (ptk/emit! store/main (events/->RouteMatched name params query)))})
  (mount (views/page store/main) (js/document.getElementById "container")))
