(ns app.containers.details.subs
	(:require [re-frame.core :refer [reg-sub subscribe]]))


(reg-sub :posts
		 (fn [db [_ username]]
			 (as-> (get-in db [:details-db :user-posts]) $
				   (sort #(compare (:time %2) (:time %1)) $))))

(reg-sub :followers
		 (fn [db [_ username]]
			 (get-in db [:details-db :followers])))

(reg-sub :followings
		 (fn [db [_ username]]
			 (get-in db [:details-db :followings])))