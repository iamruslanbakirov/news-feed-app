(ns app.containers.news-feed
  (:require [reagent.core :as reagent :refer [atom]]
            [app.containers.news-feed.style :refer [news-feed-style]]
            [app.components.news-item :refer [news-item]]))

(defonce state (atom {:news [{:id 1
                              :author "@TestNik"
                              :text "Hello everyone!"
                              :time (js/Date.now)}
                             {:id 2
                              :author "@TestSecond"
                              :text "On friday i wanna go sleep"
                              :time (+ (js/Date.now) 3000)}]
                      :uniqkey 0}))

(defn gen-key []
  (-> (swap! state update :uniqkey inc) (:uniqkey @state)))

(defn news-feed-container []
  [:section.news-feed (news-feed-style)
     (doall (for [item (:news @state)]
              ^{:key (:id item)} [:div (news-item
                                         (:author item)
                                         (:text item)
                                         (:time item))]))])
