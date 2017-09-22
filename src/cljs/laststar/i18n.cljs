(ns laststar.i18n
  (:require [tongue.core :as tongue]))

(def dicts
  {:tongue/fallback :cz
   :cz
   {:name       "LastStar.eu"
    :pages      {:about      "O společnosti"
                 :technology "Technologie"
                 :contact    "Kontakt"}
    :home       {:hero "Pokrokové ICT"
                 :more "Chci se dozvědět více"}
    :contact    {:regid "IČO: 29051649"}}

   :en {:pages {:about      "About"
                :technology "Technology"
                :contact    "Contact"}
        :home {:hero "The Progressive ICT"
               :more "I want to know more"}}})


(def ^:const t
  (tongue/build-translate dicts))
