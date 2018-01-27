(ns app.components.user-posts
	(:require [reagent.core :as reagent
			   :refer           [atom]]
			  [re-frame.core :refer [subscribe dispatch]]
			  [app.components.news-item :refer [news-item]]))

(defn user-posts [user details-container pop-up]
	(let [username (:username user)]
		(dispatch [:get-user-posts username])
		(fn []
			[:ul
			 (doall
			  (for [item @(subscribe [:posts username])]
				  ^{:key (:id item)}
				  [:li
				   (news-item pop-up details-container (:username item) (:text item) (:time item))]))])))
