(ns laststar.store
  (:require [potok.core :as ptk]))

(defonce initial-state
  {:state {:page/current :intro
           :category/current nil
           :page/technology
           [[:clojure
             "Clojure"
             "We strongly believe in Clojure and its ecosystem. We are on the forefront of this new way of programming."
             "Tell me more"]
            [:iot
             "IoT"
             "The future of human interaction is defined by this new area of information technologies."
             true]
            [:optimalization
             "Optimalization"
             "So many companies have problem with managing and connecting wide range of ICT systems on day to day basis. We can help also."
             true]]
           :page/people
           [[:pepe
             "Josef 'pepe' Pospíšil"
             "First Rubyist in the Czech Republic. Father of three, world citizen and party animal."
             true]
            [:vendi
             "Vendula 'vendi' Pospíšilová"
             "The company executor also becoming the data scientist. Animal lover, forestry post-Doc."
             true]]
           :page/about
           [[:about
             "About the Company"
             [:p.bigger
              "We are growing Software and Hardware developing company. Initially founded by\u00A0parents on the children leave, reborn as professional collective, trying to change world around us. We are currently  looking for opportunities to show our craft. Feel free to contact us with any questions."]
             false]]
           :page/contact
           [[:contact
             "Contact"
             [:div
              [:h4
               [:a
                {:href "mailto:info@laststar.eu"}
                "info@laststar.eu"]]
              [:h4 "Reg. Id: 29051649"]
              [:h4 "Husova 1200/63, Liberec 460 01 CZ"]]
             false]]}})

(defonce main
  (ptk/store initial-state))
