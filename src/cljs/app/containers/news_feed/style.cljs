(ns app.containers.news-feed.style
  (:require [garden.core :as garden]
            [garden.units :as units]
            [garden.color :as color]

            [app.util :refer [style-tag]]))


(defn news-feed-style []
  (style-tag [:.news-feed
              {:display "flex"
               :flex-basis "100%"
               :width "100%"
               :flex-direction "column"}]))
