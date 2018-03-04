(ns app.containers.details.subs
	(:require [re-frame.core :refer [reg-sub subscribe]]))

(reg-sub :user
		 (fn [db _]
			 (:data-user (:details-db db))))

(reg-sub :error-user
		 (fn [db _]
			 (:error-user (:details-db db))))

(reg-sub :loading-user?
		 (fn [db _]
			 (:loading-user? (:details-db db))))

(reg-sub :posts
		 (fn [db _]
			 (as-> (get-in db [:details-db :user-posts]) $
				   (sort #(compare (:time %2) (:time %1)) $))))

(reg-sub :followers
		 (fn [db _]
			 (get-in db [:details-db :followers])))

(reg-sub :followings
		 (fn [db _]
			 (get-in db [:details-db :followings])))