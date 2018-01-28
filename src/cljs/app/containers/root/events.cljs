(ns app.containers.root.events
	(:require [re-frame.core :refer [reg-event-db dispatch reg-event-fx]]
			  [ajax.core :refer [GET POST]]
			  [clojure.walk :refer [keywordize-keys]]

			  [app.containers.root.db :refer [default-root-db]]))

(reg-event-fx :init-root-db []
			  (fn [{:keys [db]} _]
				  {:db (assoc db :root-db default-root-db)}))

(reg-event-db :get-user-data
			  (fn [db _]
				  (GET "/api/user"
					   {:handler       #(dispatch [:user-resp %1])
						:error-handler #(dispatch [:user-error %1])})
				  db))

(reg-event-db :user-resp
			  (fn [db [_ res]]
				  (update-in db [:root-db] assoc :loading-user? true :data-user (keywordize-keys (js->clj res)))))

(reg-event-db :user-error
			  (fn [db [_ res]]
				  (update-in db [:root-db] assoc :loading-user? true :error-user (keywordize-keys (js->clj res)))))

(reg-event-db :switch-route-name
			  (fn [db [_ new-name]]
				  (update-in db [:root-db] assoc :route-current-name new-name)))
