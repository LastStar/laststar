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

(rum/defc toolbar < rum/reactive [state]
  (let [current-lang (-> state (rum/cursor :ui/language) rum/react)
        change-lang (fn [state lang]
                      (fn [ev]
                        (.stopPropagation ev)
                        (swap! state assoc :ui/language lang)
                        (js/localStorage.setItem "language" lang)))]
    [mdc/fixed-toolbar
     [mdc/toolbar-row
      [mdc/toolbar-section-start
       [mdc/toolbar-title (i18n/t current-lang :name)]]
      [mdc/toolbar-section-end
       [:div.links
        (mdcc/link-button {:href "#/about"} (i18n/t current-lang :pages/about))
        (mdcc/link-button {:href "#/technology"} (i18n/t current-lang :pages/technology))
        (mdcc/link-button {:href "#/contact"} (i18n/t current-lang :pages/contact))]
       [:div.flags
        (let [lang (if (= current-lang "cz") "en" "cz" )]
          [:img {:key lang
                 :src (str "img/flags/" lang ".svg")
                 :on-click (when (not= lang current-lang) (change-lang state lang))}])]
       "\u00A0"]]]))


(rum/defc hero < rum/static [current-lang]
  [mdc/section-elevation-3
   {:class    :hero}
   [:img {:src "img/logo.svg"}]
   [mdc/typo-display-2 (i18n/t current-lang :home/hero)]
   (mdcc/link-button
    {}
    (i18n/t current-lang :home/more))])


(rum/defc about < rum/static [current-lang]
  [:section#about.category
   [:div.text
    [mdc/typo-display-3 (i18n/t current-lang :about/title)]
    [mdc/typo-body-1 "Pellentesque dapibus suscipit ligula.  Donec posuere augue in quam.  Etiam vel tortor sodales tellus ultricies commodo.  Suspendisse potenti.  Aenean in sem ac leo mollis blandit.  Donec neque quam, dignissim in, mollis nec, sagittis eu, wisi.  Phasellus lacus.  Etiam laoreet quam sed arcu.  Phasellus at dui in ligula mollis ultricies.  Integer placerat tristique nisl.  Praesent augue.  Fusce commodo.  Vestibulum convallis, lorem a tempus semper, dui dui euismod elit, vitae placerat urna tortor vitae lacus.  Nullam libero mauris, consequat quis, varius et, dictum id, arcu.  Mauris mollis tincidunt felis.  Aliquam feugiat tellus ut neque.  Nulla facilisis, risus a rhoncus fermentum, tellus tellus lacinia purus, et dictum nunc justo sit amet elit."]]
   [:div.image
    {:style {:background-image "url(img/about.jpg)"}}]])

(rum/defc technology < rum/static [current-lang]
  [:section#technology.category
   [:div.image.contain {:style {:background-image "url(img/clj.svg)"}}]
   [:div.text
    [mdc/typo-display-3 (i18n/t current-lang :technology/title)]
    [mdc/typo-body-1 "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.  Donec hendrerit tempor tellus.  Donec pretium posuere tellus.  Proin quam nisl, tincidunt et, mattis eget, convallis nec, purus.  Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.  Nulla posuere.  Donec vitae dolor.  Nullam tristique diam non turpis.  Cras placerat accumsan nulla.  Nullam rutrum.  Nam vestibulum accumsan nisl."]]])


(rum/defc contact < rum/static [current-lang]
  [:section#contact.category
   [:div.text
    [mdc/typo-display-3 (i18n/t current-lang :contact/title)]
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
  (js/console.log current-lang)
  [:footer
   [:div (i18n/t current-lang :name)]
   [:div"All Rights Reserved 2017"]])


(rum/defc page < rum/reactive [store]
  (let [state (utils/get-state store)
        current-lang (-> state (rum/cursor :ui/language) rum/react)]
    (js/console.log current-lang)
    [:div.content
     (toolbar state)
     [:main
      [:div
       (hero current-lang)
       (about current-lang)
       (technology current-lang)
       (contact current-lang)]]
     (footer current-lang)]))
