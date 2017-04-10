(ns laststar.events
  (:require [potok.core :as ptk]
            [beicon.core :as rxt]))

(defrecord SetCategory [category]
  ptk/UpdateEvent
  (update [_ state]
    (assoc state :category/current category)))

(defrecord SetPage [page]
  ptk/UpdateEvent
  (update [_ state]
    (assoc state :page/current page)))
