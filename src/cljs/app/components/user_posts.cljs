(ns app.components.user-posts
	(:require [reagent.core :as reagent
			   :refer           [atom]]
			  [re-frame.core :refer [subscribe dispatch]]
			  [app.components.news-item :refer [news-item]]
			  [app.util :refer [to-details]]))

(defn user-posts [user pop-up]
	(let [username (:username user)]
		(dispatch [:get-user-posts username])
		(fn []
			[:ul
			 (doall
			  (for [item @(subscribe [:posts username])]
				  ^{:key (:id item)}
				  [:li {:on-click (to-details pop-up user)}
				   (news-item pop-up (:username item) (:text item) (:time item))]))])))
