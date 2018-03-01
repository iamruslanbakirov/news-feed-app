(ns app.containers.news-feed.events
	(:require [re-frame.core :refer [reg-event-db dispatch reg-event-fx]]
			  [ajax.core :refer [GET POST]]

			  [app.util :refer [ajax-params]]
			  [app.containers.news-feed.db :refer [default-news-feed-db]]))

(reg-event-fx :init-news-feed-db []
			  (fn [{:keys [db]} _]
				  {:db (assoc db :news-feed-db default-news-feed-db)}))

(reg-event-db :get-news-data
			  (fn [db _]
				  (GET "/api/news"
					   (merge ajax-params
							  {:handler       #(dispatch [:news-resp %1])
							   :error-handler #(dispatch [:news-error %1])}))
				  db))

(reg-event-db :news-resp
			  (fn [db [_ res]]
				  (update-in db [:news-feed-db] assoc :loading-news? true :data-news res)))

(reg-event-db :news-error
			  (fn [db [_ res]]
				  (update-in db [:news-feed-db] assoc :loading-news? true :error-news res)))

(reg-event-db :change-new-msg
			  (fn [db [_ text]]
				  (update-in db [:news-feed-db] assoc :new-msg text)))