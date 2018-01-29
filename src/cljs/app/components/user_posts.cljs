(ns app.components.user-posts
	(:require [reagent.core :as reagent
			   :refer           [atom]]
			  [re-frame.core :refer [subscribe dispatch]]
			  [app.components.news-item :refer [news-item]]
			  [app.util :refer [get-user-posts]]))

(defn user-posts [user comp pop-up]
	(let [username (:username user)
		  auth-username   (:username @(subscribe [:user]))
		  current-user    (= auth-username username)
		  posts (if current-user (subscribe [:posts username]) (atom []))]
		(if current-user (dispatch [:get-user-posts username]) (get-user-posts username #(reset! posts %) (fn [])))
		(fn []
			[:ul
			 (doall
			  (for [item @posts]
				  ^{:key (:id item)}
				  [:li
				   (news-item pop-up comp (:username item) (:text item) (:time item))]))])))
