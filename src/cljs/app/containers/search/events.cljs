(ns app.containers.search.events
	(:require [re-frame.core :refer [reg-event-db dispatch reg-event-fx]]
			  [ajax.core :refer [GET POST]]
			  [clojure.walk :refer [keywordize-keys]]

			  [app.util :refer [ajax-params]]
			  [app.containers.search.db :refer [default-search-db]]))

(reg-event-fx :init-search-db []
			  (fn [{:keys [db]} _]
				  {:db (assoc db :search-db default-search-db)}))

(reg-event-db :get-users
			  (fn [db [_ substr]]
				  (POST "/api/search"
						(merge ajax-params
							   {:params        {:search substr}
								:handler       #(dispatch [:get-users-resp %1])
								:error-handler #(dispatch [:get-users-error %1])}))
				  (update-in db [:search-db] assoc :loading-data? true)))

(reg-event-db :get-users-resp
			  (fn [db [_ resp]]
				  (update-in db [:search-db] assoc :users resp :loading-data? false)))

(reg-event-db :get-users-error
			  (fn [db [_ resp]]
				  (update-in db [:search-db] assoc :users [] :loading-data? false)))

(reg-event-db :change-search-str
			  (fn [db [_ str]]
				  (when (> (count str) 0)
					  (dispatch [:get-users str]))
				  (update-in db [:search-db] assoc :search-str str)))