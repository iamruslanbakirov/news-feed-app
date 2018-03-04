(ns app.containers.root.events
	(:require [re-frame.core :refer [reg-event-db dispatch reg-event-fx]]
			  [ajax.core :refer [GET]]

			  [app.util :refer [ajax-params]]
			  [app.containers.root.db :refer [default-root-db]]))

(reg-event-fx :init-root-db []
			  (fn [{:keys [db]} _]
				  {:db (assoc db :root-db default-root-db)}))

(reg-event-db :switch-route-name
			  (fn [db [_ new-name]]
				  (update-in db [:root-db] assoc :route-current-name new-name)))

(reg-event-db :switch-pop-up
			  (fn [db [_ comp title]]
				  (update-in db [:root-db :pop-up] assoc :comp comp :title title)))

(reg-event-db :reset-pop-up
			  (fn [db _]
				  (update-in db [:root-db] assoc :pop-up {:comp nil :title nil})))