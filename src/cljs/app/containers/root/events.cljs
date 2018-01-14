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
      {:handler #(dispatch [:user-resp %1])
       :error-handler #(dispatch [:user-error %1])})
    (assoc-in db [:root-db :loading-user?] true)))

(reg-event-db :get-news-data
  (fn [db _]
    (GET "/api/news"
      {:handler #(dispatch [:news-resp %1])
       :erro-handler #(dispatch [:news-error 1%])})
    (assoc-in db [:root-db :loading-news?] true)))

(reg-event-db :user-resp
  (fn [db [_ res]]
    (update-in db [:root-db] assoc :loading-user? false :data-user (keywordize-keys (js->clj res)))))

(reg-event-db :user-error
  (fn [db [_ res]]
    (update-in db [:root-db] assoc :loading-user? false :error-user (keywordize-keys (js->clj res)))))

(reg-event-db :news-resp
  (fn [db [_ res]]
    (update-in db [:root-db] assoc :loading-news? false :data-news (keywordize-keys (js->clj res)))))

(reg-event-db :news-error
  (fn [db [_ res]]
    (update-in db [:root-db] assoc :loading-news? false :error-news (keywordize-keys (js->clj res)))))

(reg-event-db :switch-route-name
  (fn [db [_ new-name]]
    (update-in db [:root-db] assoc :route-current-name new-name)))
