(ns laststar.store
  (:require [potok.core :as ptk]))

(defonce initial-state
  {:state {:page/current :intro
           :category/current nil
           :page/technology
           [[:clojure
             "Clojure"
             "We strongly believe in Clojure and its ecosystem. We are on the forefront of this new way of programming."
             false]
            [:iot
             "IoT"
             "The future of human interaction is defined by this new area of information technologies."
             false]
            [:optimalization
             "Optimalization"
             "So many companies have problem with managing and connecting wide range of ICT systems on day to day basis. We can help with that."
             false]]
           :page/people
           [[:pepe
             "Josef 'pepe' Pospíšil"
             "First Rubyist in the Czech Republic. Father of three, world citizen and party animal."
             false]
            [:vendi
             "Vendula 'vendi' Pospíšilová"
             "The company executor also becoming the data scientist. Animal lover, forestry post-Doc."
             false]
            [:sheik
             "Aleš 'sheik' Chrenka"
             "Silent man in the background, solving hardest problems and advice the direction of the whole company."]]
           :page/about
           [[:about
             "About the Company"
             [:p.bigger
              "We are growing Software and Hardware developing company. Initially founded by\u00A0parents on the children leave, reborn as professional collective, trying to change\u00A0the world around us. We are currently  looking for opportunities to show off our craft. Feel free to contact us with any questions."]
             false]]
           :page/contact
           [[:contact
             "Contact"
             [:div
              [:h4
               [:a
                {:href "mailto:info@laststar.eu"}
                "info@laststar.eu"]]
              [:h4
               [:a
                {:href "https://or.justice.cz/ias/ui/rejstrik-firma.vysledky?subjektId=39004&typ=UPLNY"}
                "Reg. Id: 29051649"]]
              [:h4 "Husova 1200/63, Liberec 460 01 CZ"]]
             false]]}})

(defonce main
  (ptk/store initial-state))
