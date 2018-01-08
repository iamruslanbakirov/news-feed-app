(ns app.containers.home.events
  (:require [re-frame.core :refer [reg-event-db dispatch reg-event-fx]]
            [ajax.core :refer [GET POST]]
            [clojure.walk :refer [keywordize-keys]]

            [app.containers.home.db :refer [default-home-db]]))

(reg-event-fx :init-home-db []
  (fn [{:keys [db]} _]
    {:db (assoc db :home-db default-home-db)}))

(reg-event-db :get-user-data
  (fn [db _]
    (GET "/user"
      {:handler #(dispatch [:user-resp %1])
       :error-handler #(dispatch [:user-error %1])})
    (assoc-in db [:home-db :loading-user?] true)))

(reg-event-db :get-news-data
  (fn [db _]
    (GET "/news"
      {:handler #(dispatch [:news-resp %1])
       :erro-handler #(dispatch [:news-error 1%])})
    (assoc-in db [:home-db :loading-news?] true)))

(reg-event-db :user-resp
  (fn [db [_ res]]
    (update-in db [:home-db] assoc :loading-user? false :data-user (keywordize-keys (js->clj res)))))

(reg-event-db :user-error
  (fn [db [_ res]]
    (update-in db [:home-db] assoc :loading-user? false :error-user (keywordize-keys (js->clj res)))))

(reg-event-db :news-resp
  (fn [db [_ res]]
    (update-in db [:home-db] assoc :loading-news? false :data-news (keywordize-keys (js->clj res)))))

(reg-event-db :news-error
  (fn [db [_ res]]
    (update-in db [:home-db] assoc :loading-news? false :error-news (keywordize-keys (js->clj res)))))
