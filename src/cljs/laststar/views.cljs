(ns laststar.views
  (:require
   [rum.core :as rum]
   [rum.mdl :as mdl]
   [potok.core :as ptk]
   [beicon.core :as rxt]
   [laststar.events :refer [->SetPage ->SetCategory]]))

(defonce white "rgba(247, 247, 253, 0.7)")
(defonce semi-white "rgba(247, 247, 253, 0.4)")

(rum/defc menu-item < rum/reactive [store key title]
  (let [state (rxt/to-atom store)
        page (rum/cursor (rxt/to-atom store) :page/current)]
    (mdl/menu-item
     {:disabled (= (rum/react page) key)
      :on-click #(ptk/emit! store (->SetPage key))}
     title)))

(rum/defc header [store]
  [:header
   [:h1
    "LastStar.eu"]
   [:div
    (mdl/button
     {:mdl [:icon] :id :menu}
     (mdl/icon "more_vert"))
    (mdl/menu
     {:mdl [:ripple :bottom-right] :for :menu}
     (for [[key title] {:intro      "Intro"
                        :about      "About"
                        :people     "People"
                        :technology "Technology"
                        :contact    "Contact"}]
       [:div {:key key}
        (menu-item store key title)]))]])

(rum/defc body < rum/reactive [store]
  (let [state (rxt/to-atom store)
        page (rum/react (rum/cursor state :page/current))
        card-texts (rum/react (rum/cursor state (keyword "page" page)))]
    [:main
     (when (seq card-texts)
       (for [[key title text action] card-texts]
         [:div {:key key :class page}
          (mdl/card
           {:mdl   [:shadow--2dp]
            :class (if (= (count card-texts) 1) :wide-card :standard-card)}
           (mdl/card-title title)
           (mdl/card-text text)
           (if action
             (mdl/card-action
              {:mdl [:border]}
              (mdl/button {:mdl [:colored :ripple]
                           :on-click #(ptk/emit! store (->SetCategory key))} "Tell me more"))
             [:div])
           (mdl/card-menu
            (mdl/button {:mdl [:icon :ripple]} (mdl/icon "share"))))]))]))

(rum/defc footer < rum/reactive [store]
  (let [page (rum/cursor (rxt/to-atom store) :page/current)]
    [:footer
     (if (= :intro (rum/react page))
       [:div.contact
        [:div
         [:a
          {:style {:color semi-white}
           :href "mailto:info@laststar.eu"}
          "info@laststar.eu"]]
        [:div "Reg. Id: 29051649"]
        [:div "Husova 1200/63, Liberec 460 01 CZ"]]
       [:span])
     [:div
      [:span.copy "Copyright © 2017\u00A0"]
      [:h1
       {:style {:display        :inline
                :font-size      "1rem"}}
       "LastStar.eu s.r.o."]
      [:span.copy "\u00A0All Right Reserved"]]]))

(rum/defc page [store]
  [:div
   (header store)
   (body store)
   (footer store)])
