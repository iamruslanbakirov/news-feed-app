(ns app.containers.details.subs
	(:require [re-frame.core :refer [reg-sub subscribe]]))


(reg-sub :posts
		 (fn [db [_ username]]
			 (get-in db [:details-db :user-posts (keyword username)])))

(reg-sub :followers
		 (fn [db [_ username]]
			 (get-in db [:details-db :followers (keyword username)])))

(reg-sub :followings
		 (fn [db [_ username]]
			 (get-in db [:details-db :followings (keyword username)])))