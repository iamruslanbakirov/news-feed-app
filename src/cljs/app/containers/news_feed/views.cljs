(ns app.containers.news-feed.views
	(:require [reagent.core :as reagent
			   :refer           [atom]]

			  [re-frame.core :refer [subscribe dispatch]]

			  [app.containers.news-feed.style :refer [news-feed-style]]
			  [app.containers.news-feed.db]
			  [app.containers.news-feed.events]
			  [app.containers.news-feed.subs]

			  [app.components.news-item :refer [news-item]]
			  [app.components.add-post :refer [add-post-component]]
			  [app.containers.details.views :refer [details-container]]))

; (defn gen-key []
;   (-> (swap! state update :uniqkey inc) (:uniqkey @state)))

(defn news-feed-container [pop-up]
	(dispatch [:get-news-data])
	(fn []
		[:section.news-feed
		 (news-feed-style)
		 [add-post-component]
		 (doall
		  (for [item @(subscribe [:news])]
			  ^{:key (:id item)} [:div
								  (news-item
								   pop-up
								   details-container
								   (:username item)
								   (:text item)
								   (:time item))]))]))
