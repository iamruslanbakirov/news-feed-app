(ns app.containers.home.subs
  (:require [re-frame.core :refer [reg-sub subscribe]]))

(reg-sub :user
  (fn [db _]
    (:data-user (:home-db db))))

(reg-sub :friends-list
  (fn [db _]
    (:friends (:home-db db))))

(reg-sub :loading-home-page
  (fn [db _]
    (let [home-db (:home-db db)]
      (or (:loading-news? home-db) (:loading-user? home-db)))))

(reg-sub :error-news
  (fn [db _]
    (:error-news (:home-db db))))

(reg-sub :error-user
  (fn [db _]
    (:error-user (:home-db db))))

(reg-sub :news
  (fn [db _]
    (:data-news (:home-db db))))
