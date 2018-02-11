(ns app.containers.search.subs
	(:require [re-frame.core :refer [reg-sub]]))

(reg-sub :search-users
		 (fn [db _]
			 (get-in db [:search-db :users])))

(reg-sub :loading-search-data
		 (fn [db _]
			 (get-in db [:search-db :loading-data?])))

(reg-sub :search-str
		 (fn [db _]
			 (get-in db [:search-db :search-str])))