(ns laststar.store
  (:require [potok.core :as ptk]))

(defonce initial-state
  {:state {:page/current :page/intro
           :ui.language/current :cz}})

(defonce main
  (ptk/store initial-state))
