(ns laststar.app
  (:require
   [rum.core :refer [mount]]
   [potok.core :as ptk]
   [beicon.core :as rxt]
   [bide.core :as rtr]
   [cljsjs.material-components]
   [laststar.store :as store]
   [laststar.routes :as routes]
   [laststar.events :as events]
   [laststar.views :as views])
  (:import goog.events.EventType))

(defn init []
  (let [interval        250
        wheel-stream    (rxt/from-event js/document EventType.WHEEL)
        scroll-stream   (rxt/from-event js/document EventType.SCROLL)
        combined-stream (rxt/throttle interval (rxt/merge scroll-stream wheel-stream))]
    (rxt/on-value scroll-stream #(events/scrolling store/main))
    (rtr/start!
     routes/config
     {:default     :page/hero
      :on-navigate #(events/set-page store/main %1)})
  (mount (views/page store/main) (js/document.getElementById "container"))))
