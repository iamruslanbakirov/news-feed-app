(ns app.containers.news-feed.style
  (:require [garden.core :as garden]
            [garden.units :as units]
            [garden.color :as color]

            [app.util :refer [style-tag]]))


(defn news-feed-style []
  (style-tag [:.news-feed
              {:width "100%"}]))
