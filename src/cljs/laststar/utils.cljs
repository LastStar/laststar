(ns laststar.utils
  (:require [rum.core :as rum]
            [beicon.core :as rxt]))

(defn get-state [store]
  (rxt/to-atom store))


(defn get-from [state key]
  (-> state (rum/cursor key) rum/react))


(defn get-in-from [state keys]
  (-> state (rum/cursor-in keys) rum/react))


(defn get-lang-from [state]
  (keyword (get-from state :ui.language/current)))

