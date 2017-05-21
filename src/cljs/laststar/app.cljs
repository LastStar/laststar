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
   {:default     :page/intro
    :on-navigate (fn [page _ _] (ptk/emit! store/main (events/->SetPage page)))})
  (mount (views/page store/main) (js/document.getElementById "container")))
