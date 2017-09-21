(ns laststar.i18n
  (:require [tongue.core :as tongue]))

(def dicts
  {:tongue/fallback :cz
   :cz
   {:name  "LastStar.eu"
    :pages {:about      "O společnosti"
            :technology "Technologie"
            :contact    "Kontakt"}
    :home {:hero "Pokrokové Informační Technologie"
           :more "Chci se dozvědět více"}
    :about {:title "O společnosti"}
    :technology {:title "Technologie"}
    :contact {:title "Kontakt"
              :regid "IČO: 29051649"}}

   :en {}})


(def ^:const t
  (tongue/build-translate dicts))
