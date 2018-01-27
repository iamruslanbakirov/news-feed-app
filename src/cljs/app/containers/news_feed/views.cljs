(ns app.containers.news-feed.views
	(:require [reagent.core :as reagent
			   :refer           [atom]]

			  [re-frame.core :refer [subscribe dispatch]]

			  [app.containers.news-feed.style :refer [news-feed-style]]
			  [app.components.news-item :refer [news-item]]
			  [app.components.add-post :refer [add-post-component]]))

; (defn gen-key []
;   (-> (swap! state update :uniqkey inc) (:uniqkey @state)))

(defn news-feed-container [pop-up]
	(dispatch [:get-news-data])
	(fn []
		[:section.news-feed
		 (news-feed-style)
		 [add-post-component (fn [])]
		 (doall
		  (for [item @(subscribe [:news])]
			  ^{:key (:id item)} [:div
								  (news-item
								   pop-up
								   (:username item)
								   (:text item)
								   (:time item))]))]))
