(ns laststar.events
  (:require [potok.core :as ptk]
            [bide.core :as rtr]
            [beicon.core :as rxt]
            [laststar.routes :as routes]))

(defrecord SetCategory [category]
  ptk/UpdateEvent
  (update [_ state]
    (assoc state :category/current category)))

(defrecord SetPage [page]
  ptk/UpdateEvent
  (update [_ state]
    (assoc state :page/current page))
  ptk/EffectEvent
  (effect [_ state _]
    (rtr/navigate! routes/config :laststar/page {:page page})))

(defrecord RouteMatched [name params query]
  ptk/WatchEvent
  (watch [_ state _]
    (let [page (keyword (:page params))]
         (case name
           :laststar/intro
           (rxt/just (->SetPage :intro))
           :laststar/page
           (rxt/just (->SetPage page))))))
