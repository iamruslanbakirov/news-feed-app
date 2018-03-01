(ns app.components.user-posts
	(:require [reagent.core :as reagent
			   :refer           [atom]]
			  [re-frame.core :refer [subscribe dispatch]]
			  [app.components.news-item :refer [news-item]]
			  [app.util :refer [get-user-posts]]))


(defn posts-list [list pop-up comp]
	[:ul
	 (doall
	  (for [item list]
		  ^{:key (:id item)}
		  [:li
		   (news-item pop-up comp (:username item) (:text item) (:time item))]))])

(defn user-posts [user comp pop-up]
	(let [username (:username user)
		  auth-username   (:username @(subscribe [:user]))
		  current-user    (= auth-username username)
		  posts (if current-user (subscribe [:posts username]) (atom []))]
		(if current-user
			(when (= (count @posts) 0) (dispatch [:get-user-posts username]))
			(get-user-posts username #(reset! posts %) (fn [])))
		(fn []
			(posts-list @posts pop-up comp))))
