(ns laststar.views
  (:require
   [rum.core :as rum]
   [potok.core :as ptk]
   [beicon.core :as rxt]
   [mdc-rum.core :as mdc]
   [mdc-rum.components :as mdcc]
   [laststar.utils :as utils]
   [laststar.events :as events]
   [laststar.i18n :as i18n]))

(defonce white "rgba(247, 247, 253, 0.7)")
(defonce semi-white "rgba(247, 247, 253, 0.4)")
(defonce languages #{:cz :en})
(defonce pages ["about" "technology" "contact"])

(rum/defc toolbar < rum/static [store current-lang scrolled? active-page]
  [mdc/fixed-toolbar
   {:id "toolbar"
    :class (when-not scrolled? "big")}
   [mdc/toolbar-row
    [mdc/toolbar-section-start
     [mdc/toolbar-title [:a {:href "#/"} (i18n/t current-lang :name)]]]
    [mdc/toolbar-section-end
     [:div.links
      (for [page pages]
        (rum/with-key
          (mdcc/link-button
           {:href (str "#/" page) 
            :class (when (and active-page (= (name active-page) page)) "active")}
           (i18n/t current-lang (keyword "pages" page)))
          (str page "-link")))]
     [:div.flags
      (let [alternate-language (first (clojure.set/difference languages #{current-lang}))]
        [:img {:key alternate-language
               :src (str "img/flags/" (name alternate-language) ".svg")
               :on-click #(events/change-language store alternate-language)}])]
     "\u00A0"]]])

(def svg-logo
  [:svg {:view-box "0 0 180 180"}
   [:title "Logo"]
   [:rect#background {:fill "#000000" :x "0" :y "0" :width "180" :height "180"}]
   [:text {:font-family "Monopol" :font-size "192" :font-weight "300" :fill "#FFFFFF"}
    [:tspan#L {:x 58 :y 155} "L"]
    [:tspan#S {:x 77 :y 155} "S"]]])


(rum/defc hero < rum/static [store current-lang]
  [mdc/section-elevation-3
   {:id :hero}
   [:div.contact [:a {:href "#/contact"} svg-logo]]
   [:div.logo svg-logo]
   [mdc/typo-display-2 (i18n/t current-lang :home/hero)]
   [:div.more
    (mdcc/link-button
    {:href "#/about" :class "more"}
    (i18n/t current-lang :home/more))]])


(rum/defc about < rum/static [current-lang]
  [:section#about.category
   [:div.text
    [mdc/typo-display-3 (i18n/t current-lang :pages/about)]
    [mdc/typo-body-1 "Pellentesque dapibus suscipit ligula.  Donec posuere augue in quam.  Etiam vel tortor sodales tellus ultricies commodo.  Suspendisse potenti.  Aenean in sem ac leo mollis blandit.  Donec neque quam, dignissim in, mollis nec, sagittis eu, wisi.  Phasellus lacus.  Etiam laoreet quam sed arcu.  Phasellus at dui in ligula mollis ultricies.  Integer placerat tristique nisl.  Praesent augue.  Fusce commodo.  Vestibulum convallis, lorem a tempus semper, dui dui euismod elit, vitae placerat urna tortor vitae lacus.  Nullam libero mauris, consequat quis, varius et, dictum id, arcu.  Mauris mollis tincidunt felis.  Aliquam feugiat tellus ut neque.  Nulla facilisis, risus a rhoncus fermentum, tellus tellus lacinia purus, et dictum nunc justo sit amet elit."]]
   [:div.image
    {:style {:background-image "url(img/about.jpg)"}}]])

(rum/defc technology < rum/static [current-lang]
  [:section#technology.category
   [:div.image.contain {:style {:background-image "url(img/clj.svg)"}}]
   [:div.text
    [mdc/typo-display-3 (i18n/t current-lang :pages/technology)]
    [mdc/typo-body-1 "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.  Donec hendrerit tempor tellus.  Donec pretium posuere tellus.  Proin quam nisl, tincidunt et, mattis eget, convallis nec, purus.  Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.  Nulla posuere.  Donec vitae dolor.  Nullam tristique diam non turpis.  Cras placerat accumsan nulla.  Nullam rutrum.  Nam vestibulum accumsan nisl."]]])


(rum/defc contact < rum/static [current-lang]
  [:section#contact.category
   [:div.text
    [mdc/typo-display-3 (i18n/t current-lang :pages/contact)]
    [:div
     [mdc/typo-body-1
      [:a {:href "mailto:info@laststar.eu"} "info@laststar.eu"]]
     [mdc/typo-body-1
      [:a
       {:href "https://or.justice.cz/ias/ui/rejstrik-firma.vysledky?subjektId=39004&typ=UPLNY"}
       (i18n/t current-lang :contact/regid)]]
     [mdc/typo-body-1
      "Husova 1200/63"
      [:br]
      "Liberec 460 01 CZ"]]]
   [:div.image {:style {:background-image "url(img/contact.jpg)"}}]])


(rum/defc footer < rum/static [current-lang]
  [:footer
   [mdc/typo-body-1 (i18n/t current-lang :name)]
   [mdc/typo-body-1 "All Rights Reserved 2017"]])


(rum/defc page < rum/reactive [store]
  (let [state        (utils/get-state store)
        current-lang (utils/get-lang-from state)
        scrolled?    (utils/get-from state :ui/scrolled)
        active-page  (utils/get-from state :page/active)]
    [:div.content
     (toolbar store current-lang scrolled? active-page)
     [:main
      [:div
       (hero store current-lang)
       (about current-lang)
       (technology current-lang)
       (contact current-lang)]]
     (footer current-lang)]))
