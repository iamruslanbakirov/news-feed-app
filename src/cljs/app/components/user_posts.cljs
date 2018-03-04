(ns app.components.user-posts
	(:require [reagent.core :as reagent
			   :refer           [atom]]
			  [re-frame.core :refer [subscribe dispatch]]
			  [app.components.news-item :refer [news-item]]
			  [app.components.empty :refer [empty-comp]]
			  [app.util :refer [get-user-posts]]))


(defn posts-list [list comp]
	[:ul
	 (doall
	  (for [item list]
		  ^{:key (:id item)}
		  [:li
		   (news-item comp (:username item) (:text item) (:time item))]))])

(defn user-posts [user comp]
	(let [username        (:username user)
		  auth-username   (:username @(subscribe [:user]))
		  current-user    (= auth-username username)
		  posts           (if current-user (subscribe [:posts username]) (atom []))]
		(when (not current-user)
			(get-user-posts username #(reset! posts %) (fn [])))
		(fn []
			(if (= 0 (count @posts))
				(empty-comp)
				(posts-list @posts comp)))))
