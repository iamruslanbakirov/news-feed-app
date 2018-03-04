(ns app.containers.details.events
	(:require [re-frame.core :refer [reg-event-db dispatch reg-event-fx subscribe]]
			  [ajax.core :refer [GET POST]]

			  [app.util :as util]
			  [app.containers.details.db :refer [default-details-db]]))


(reg-event-fx
 :init-details-db []
 (fn [{:keys [db]} _]
	 {:db (assoc db :details-db default-details-db)}))

(reg-event-db :get-user-data
			  (fn [db _]
				  (GET "/api/user"
					   (merge util/ajax-params
							  {:handler       #(dispatch [:user-resp %1])
							   :error-handler #(dispatch [:user-error %1])}))
				  db))

(reg-event-db :user-resp
			  (fn [db [_ res]]
				  (do
					  (dispatch [:get-user-posts (:username res)])
					  (update-in db [:details-db] assoc :loading-user? true :data-user res))))

(reg-event-db :user-error
			  (fn [db [_ res]]
				  (update-in db [:details-db] assoc :loading-user? true :error-user res)))

(reg-event-db
 :get-user-posts
 (fn [db [_]]
	 (let [username (get-in db [:details-db :data-user :username])]
		 (util/get-user-posts
		  username
		  #(dispatch [:user-posts-resp %1])
		  #(dispatch [:user-posts-error %1]))
		 db)))

(reg-event-db
 :user-posts-resp
 (fn [db [_ res]]
	 (update-in db [:details-db] assoc
				:loading-data? true
				:user-posts    res)))

(reg-event-db
 :user-posts-error
 (fn [db [_ res]]
	 (update-in db [:details-db] assoc
				:loading-data? true
				:user-posts    [])))

(reg-event-db
 :get-followers
 (fn [db [_]]
	 (let [username (get-in db [:details-db :data-user :username])]
		 (util/get-followers
		  username
		  #(dispatch [:get-followers-resp %1])
		  #(dispatch [:get-followers-error %1]))
		 db)))

(reg-event-db
 :get-followers-resp
 (fn [db [_ res]]
	 (update-in db [:details-db] assoc :followers res)))

(reg-event-db
 :get-followers-error
 (fn [db [_ username error]]
	 (update-in db [:details-db] assoc :followers [])))

(reg-event-db
 :get-followings
 (fn [db [_]]
	 (let [username (get-in db [:details-db :data-user :username])]
		 (util/get-followings
		  username
		  #(dispatch [:get-followings-resp %1])
		  #(dispatch [:get-followings-error %1]))
		 db)))

(reg-event-db :get-followings-resp
			  (fn [db [_ res]]
				  (update-in db [:details-db] assoc
							 :followings res)))

(reg-event-db
 :get-followings-error
 (fn [db [_ error]]
	 (update-in db [:details-db] assoc
				:followings [])))

(reg-event-db
 :send-message
 (fn [db [_ text]]
	 (POST "/api/message"
		   (merge util/ajax-params
				  {:params        {:message text}
				   :handler       #(dispatch [:send-message-resp %1])
				   :error-handler #(dispatch [:send-message-error %1])}))
	 (update-in db [:details-db] assoc :sending-message? true)))

(reg-event-db
 :send-message-resp
 (fn [db [_ res]]
	 (dispatch [:add-message res])
	 (update-in db [:details-db] assoc
				:sending-message? false)))

(reg-event-db
 :send-message-error
 (fn [db [_ res]]
	 (update-in db [:details-db] assoc
				:sending-message? false)))

(reg-event-db
 :add-message
 (fn [db [_ message-obj]]
	 (let [username (:username (get-in db [:details-db :data-user]))]
		 (dispatch [:change-new-msg ""])
		 (update-in db [:details-db :user-posts] conj message-obj))))

(reg-event-db :follow
			  (fn [db [_ username]]
				  (GET (str "/api/follow/" username)
					   (merge util/ajax-params {:handler #(dispatch [:follow-resp %1 username])}))
				  db))

(reg-event-db :unfollow
			  (fn [db [_ username]]
				  (GET (str "/api/unfollow/" username)
					   (merge util/ajax-params {:handler #(dispatch [:unfollow-resp %1])}))
				  db))

(reg-event-db :follow-resp
			  (fn [db [_ resp]]
				  (update-in db [:details-db :followings] conj resp)))

(reg-event-db :unfollow-resp
			  (fn [db [_ resp]]
				  (update-in db [:details-db] assoc
							 :followings
							 (filterv #(not= (:username resp) (:username %))
									  (get-in db [:details-db :followings])))))

