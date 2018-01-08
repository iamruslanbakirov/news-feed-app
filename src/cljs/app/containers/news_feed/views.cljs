(ns app.containers.news-feed.views
  (:require [reagent.core :as reagent :refer [atom]]

            [re-frame.core :refer [subscribe dispatch]]

            [app.containers.news-feed.style :refer [news-feed-style]]
            [app.components.news-item :refer [news-item]]))


; (defn gen-key []
;   (-> (swap! state update :uniqkey inc) (:uniqkey @state)))

(defn news-feed-container []
  [:section.news-feed (news-feed-style)
     (doall (for [item @(subscribe [:news])]
              ^{:key (:id item)} [:div (news-item
                                         (:author item)
                                         (:text item)
                                         (:time item))]))])
