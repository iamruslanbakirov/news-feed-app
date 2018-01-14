(ns app.containers.details.events
  (:require [re-frame.core :refer [reg-event-db dispatch reg-event-fx]]
            [ajax.core :refer [GET POST]]
            [clojure.walk :refer [keywordize-keys]]

            [app.containers.details.db :refer [default-details-db]]))


(reg-event-fx :init-details-db []
  (fn [{:keys [db]} _]
    {:db (assoc db :details-db default-details-db)}))




; (reg-event-db :get-followers
;   (fn [db _]
;     (GET "/followers"
;       {:handler #(dispatch [:])})))
