(ns laststar.events
  (:require [potok.core :as ptk]
            [bide.core :as rtr]
            [beicon.core :as rxt]
            [laststar.routes :as routes]))

(defrecord ChangeLanguage [language]
  ptk/UpdateEvent
  (update [_ state] (assoc state :ui.language/current language)))

(defn change-language [store language]
  (ptk/emit! store (->ChangeLanguage language)))


(defrecord SetPage [page]
  ptk/UpdateEvent
  (update [_ state]
    (js/console.log page)
    (assoc state :page/current page
           :ui/scrolled (if (= page :page/hero) false true)))
  ptk/EffectEvent
  (effect [_ state _]
    (when (satisfies? rtr/IRouter routes/config)
      (rtr/navigate! routes/config page {})
      (-> page name js/document.getElementById
          (.scrollIntoView (clj->js {:behavior "smooth" :block "end" :inline "nearest"}))))))

(defn set-page [store page]
  (ptk/emit! store (->SetPage page)))

(defrecord Scrolling [offset]
  ptk/UpdateEvent
  (update [_ state]
    (assoc state :ui/scrolled (if (zero? offset) false true))))

(defn scrolling [store]
  (ptk/emit! store (->Scrolling (.-pageYOffset js/window))))

