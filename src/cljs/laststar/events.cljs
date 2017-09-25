(ns laststar.events
  (:require [potok.core :as ptk]
            [bide.core :as rtr]
            [beicon.core :as rxt]
            [laststar.utils :as utils]
            [laststar.routes :as routes]))

(defrecord ChangeLanguage [language]
  ptk/UpdateEvent
  (update [_ state] (assoc state :ui.language/current language)))

(defn change-language [store language]
  (ptk/emit! store (->ChangeLanguage language)))


(defrecord SetPage [page]
  ptk/UpdateEvent
  (update [_ state]
    (cond-> state
      (not (= page (:page/current state)))
      (assoc
       :page/current page)))
  ptk/EffectEvent
  (effect [_ state _]
    (when (satisfies? rtr/IRouter routes/config)
      (rtr/navigate! routes/config page {})
      (utils/smooth-scroll (utils/element (name page))))))

(defn set-page [store page]
  (ptk/emit! store (->SetPage page)))


(defrecord Scrolling [offset]
  ptk/UpdateEvent
  (update [_ state] 
    (assoc state :ui/scrolled (< 100 offset))))

(defn scrolling [store]
  (ptk/emit! store (->Scrolling (.-pageYOffset js/window))))
