(ns app.containers.root.subs
	(:require [re-frame.core :refer [reg-sub]]))

(reg-sub :user
		 (fn [db _]
			 (:data-user (:root-db db))))

(reg-sub :error-user
		 (fn [db _]
			 (:error-user (:news-feed-db db))))

(reg-sub :loading-user?
		 (fn [db _]
			 (:loading-user? (:root-db db))))

(reg-sub :current-route-name
		 (fn [db _]
			 (:route-current-name (:root-db db))))

(reg-sub :pop-up-sub
		 (fn [db]
			 (get-in db [:root-db :pop-up])))
