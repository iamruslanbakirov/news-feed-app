(ns app.containers.details.events
	(:require [re-frame.core :refer [reg-event-db dispatch reg-event-fx]]
			  [ajax.core :refer [GET POST]]
			  [clojure.walk :refer [keywordize-keys]]

			  [app.containers.details.db :refer [default-details-db]]))


(reg-event-fx :init-details-db []
			  (fn [{:keys [db]} _]
				  {:db (assoc db :details-db default-details-db)}))


(reg-event-db :get-user-posts
			  (fn [db [_ username]]
				  (GET (str "/api/posts/" username)
					   {:handler       #(dispatch [:user-posts-resp username %1])
						:error-handler #(dispatch [:user-posts-error username %1])})
				  db))

(reg-event-db :user-posts-resp
			  (fn [db [_ username res]]
				  (update-in db [:details-db] assoc
							 :loading-data? true
							 :user-posts
											(assoc (get-in db [:details-db :user-posts])
												   (keyword username)
												   (keywordize-keys (js->clj res))))))

(reg-event-db :user-posts-error
			  (fn [db [_ username res]]
				  (update-in db [:details-db] assoc
							 :loading-data? true
							 :user-posts
											(assoc (get-in db [:details-db :user-posts])
												   (keyword username) []))))

(reg-event-db :get-followers
			  (fn [db [_ username]]
				  (GET (str "/api/followers/" username)
					   {:handler       #(dispatch [:get-followers-resp username %1])
						:error-handler #(dispatch [:get-followers-error username %1])})
				  db))

(reg-event-db :get-followers-resp
			  (fn [db [_ username res]]
				  (update-in db [:details-db] assoc
							 :followers
							 (assoc (get-in db [:details-db :followers])
									(keyword username)
									(keywordize-keys (js->clj res))))))

(reg-event-db :get-followers-error
			  (fn [db [_ username error]]
				  (update-in db [:details-db] assoc
							 :followers
							 (assoc (get-in db [:details-db :followers])
									(keyword username) []))))


(reg-event-db :get-followings
			  (fn [db [_ username]]
				  (GET (str "/api/followings/" username)
					   {:handler       #(dispatch [:get-followings-resp username %1])
						:error-handler #(dispatch [:get-followings-error username %1])})
				  db))

(reg-event-db :get-followings-resp
			  (fn [db [_ username res]]
				  (update-in db [:details-db] assoc
							 :followings
							 (assoc (get-in db [:details-db :followings])
									(keyword username)
									(keywordize-keys (js->clj res))))))

(reg-event-db :get-followings-error
			  (fn [db [_ username error]]
				  (update-in db [:details-db] assoc
							 :followings
							 (assoc (get-in db [:details-db :followings])
									(keyword username) []))))