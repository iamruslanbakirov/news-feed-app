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
			  [app.containers.details.views :refer [details-container]]
			  [app.components.user-posts :refer [posts-list]]))


(defn news-feed-container [pop-up]
	(dispatch [:get-news-data])

	(fn []
		(let [news       @(subscribe [:news])
			  user-posts @(subscribe [:posts])
			  posts      (as-> (concat news user-posts) $
							   (sort #(compare (:time %2) (:time %1)) $))]
			[:div.news-feed
			 (news-feed-style)
			 [add-post-component]
			 (posts-list posts pop-up details-container)])))
