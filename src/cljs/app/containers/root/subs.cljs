(ns app.containers.root.subs
	(:require [re-frame.core :refer [reg-sub]]))

(reg-sub :user
		 (fn [db _]
			 (:data-user (:root-db db))))

(reg-sub :friends-list
		 (fn [db _]
			 (:friends (:root-db db))))

(reg-sub :loading-user?
		 (fn [db _]
			 (:loading-user? (:root-db db))))

(reg-sub :error-news
		 (fn [db _]
			 (:error-news (:root-db db))))

(reg-sub :error-user
		 (fn [db _]
			 (:error-user (:root-db db))))

(reg-sub :news
		 (fn [db _]
			 (:data-news (:root-db db))))

(reg-sub :current-route-name
		 (fn [db _]
			 (:route-current-name (:root-db db))))
