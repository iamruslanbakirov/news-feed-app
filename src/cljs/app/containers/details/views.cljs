(ns app.containers.details.views
	(:require [reagent.core :as reagent
			   :refer           [atom]]
			  [re-frame.core :refer [subscribe dispatch]]

			  [app.util :as util]
			  [app.containers.details.style :as css]
			  [app.components.button :refer [btn-component]]
			  [app.components.add-post :refer [add-post-component]]
			  [app.containers.details.db]
			  [app.containers.details.subs]
			  [app.containers.details.events]
			  [app.components.user-posts :refer [user-posts]]
			  [app.components.users-list :refer [user-list]]))



(defn details-container [user pop-up]
	(let [username        (:username user)
		  status          (:status user)
		  auth-username   (:username @(subscribe [:user]))
		  current-user    (= auth-username username)
		  followers       (if current-user (subscribe [:followers]) (atom []))
		  followings      (if current-user (subscribe [:followings]) (atom []))
		  auth-followings (subscribe [:followings])
		  btn-state       (or current-user
							  (some #(= (:username %) username) @auth-followings))]
		(if current-user (dispatch [:get-followers]) (util/get-followers username #(reset! followers %) (fn [])))
		(if current-user (dispatch [:get-followings]) (util/get-followings username #(reset! followings %) (fn [])))
		(fn []
			[:div.wrap-paper
			 (css/wrap-paper-css)
			 [:header.paper-header
			  (css/paper-header-css)
			  [:div.pic-header (css/pic-header-css)]
			  [:div.profile-username (css/profile-username-css) username]
			  [:div.profile-status (css/profile-status-css) status]]
			 [:main
			  [:nav.profile-nav
			   (css/profile-nav-css)
			   (btn-component (if btn-state "following" "follow") (fn []) btn-state)
			   [:span
				{:class    "link"
				 :on-click #(swap! pop-up assoc
							 :comp  (user-list @followers details-container pop-up)
							 :title "Followers")}
				(str (count @followers) " followers")]
			   [:span
				{:class    "link"
				 :on-click #(swap! pop-up assoc
							 :comp  (user-list @followings details-container pop-up)
							 :title "Followings")}
				(str (count @followings) " following")]]
			  (when
				  current-user
				  [add-post-component (fn [text] (dispatch [:send-message @text]))])
			  [user-posts user details-container pop-up]]])))
