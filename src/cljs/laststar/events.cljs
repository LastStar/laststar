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
    (when (satisfies? rtr/IRouter routes/config)
      (rtr/navigate! routes/config page {}))))

