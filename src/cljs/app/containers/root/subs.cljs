(ns app.containers.root.subs
  (:require [re-frame.core :refer [reg-sub subscribe]]))

(reg-sub :user
  (fn [db _]
    (:data-user (:root-db db))))

(reg-sub :friends-list
  (fn [db _]
    (:friends (:root-db db))))

(reg-sub :loading-root-page
  (fn [db _]
    (let [root-db (:root-db db)]
      (or (:loading-news? root-db) (:loading-user? root-db)))))

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
