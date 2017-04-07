(ns laststar.app
  (:require
   [rum.core :as rum]
   [rum.mdl :as mdl]))

(defonce white "rgba(247, 247, 253, 0.7)")
(defonce semi-white "rgba(247, 247, 253, 0.4)")

(rum/defc header []
  [:header
   {:style {:display         :flex
            :justify-content :space-between
            :color           white}}
   [:h1
    {:style {:margin         "0 2rem"
             :font-size      "10rem"
             :font-family    "Monopol"
             :font-weight    "100"
             :letter-spacing "0"}}
    "LastStar.eu"]
   [:div {:style {:margin "3rem 2rem"}}
    (mdl/button
     {:mdl   [:icon]
      :id    :menu
      :style {:width "3rem" :height "3rem" :background semi-white}}
     (mdl/icon {:style {:font-size "2rem" :width "2rem" :left "40%"}}
               "more_vert"))
    (mdl/menu
     {:mdl [:ripple :bottom-right] :for :menu}
     (mdl/menu-item "People")
     (mdl/menu-item {:mdl [:divider]} "Technology")
     (mdl/menu-item "About"))]])

(rum/defc body []
  [:main
   {:style {:margin          "0 2rem"
            :display         :flex
            :flex-wrap       :wrap
            :justify-content :space-around}}
   (for [_ (range 3)]
     (mdl/card
      {:mdl   [:shadow--2dp]
       :class :wide-card
       :style {:margin    "1rem 0"
               :width     "45vw"
               :min-width "19rem"}}
      (mdl/card-title "Welcome")
      (mdl/card-text
       "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris
         sagittis pellentesque lacus eleifend lacinia...")
      (mdl/card-action
       {:mdl [:border]}
       (mdl/button {:mdl [:colored :ripple]} "Get Started"))
      (mdl/card-menu
       (mdl/button {:mdl [:icon :ripple]} (mdl/icon "share")))))])

(rum/defc footer []
  [:footer
   {:style {:color          semi-white
            :font-family    "Roboto, Helvetica, Arial, sans-serif" ;
            :font-size      "1.44rem"
            :line-height    "2rem"
            :position       "fixed"
            :bottom         "1rem"
            :left           "2rem"
            :display        :flex
            :flex-direction :column
            :align-items    :flex-start}}
   [:div
    [:a
     {style {:color semi-white}
      :href "mailto:info@laststar.eu"}
     "info@laststar.eu"]]
   [:div
    "Reg. Id: 29051649"]
   [:div
    "Husova 1200/63, Liberec 460 01 CZ"]
   [:div
    [:span "Copyright © 2017\u00A0"]
    [:h1
     {:style {:display        :inline
              :font-size      "1rem"
              :font-family    "Monopol"
              :font-weight    "100"
              :letter-spacing "0"}}
     "LastStar.eu s.r.o."]
    [:span "\u00A0All Right Reserved"]]])

(rum/defc page []
  [:div
   (header)
   (body)
   (footer)])

(defn init []
  (rum/mount (page) (. js/document (getElementById "container"))))
