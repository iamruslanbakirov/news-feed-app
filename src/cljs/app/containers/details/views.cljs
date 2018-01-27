(ns app.containers.details.views
	(:require [reagent.core :as reagent
			   :refer           [atom]]
			  [re-frame.core :refer [subscribe dispatch]]

			  [app.containers.details.style :as css]
			  [app.components.button :refer [btn-component]]
			  [app.components.add-post :refer [add-post-component]]
			  [app.containers.details.db]
			  [app.containers.details.subs]
			  [app.containers.details.events]
			  [app.components.users-list :refer [user-list]]
			  [app.components.user-posts :refer [user-posts]]))


(defn details-container [user pop-up]
	(let [username  (:username user)
		  status    (:status user)]
;;		(add-watch  pop-up :logger #(-> %4 clj->js js/console.log))
		(dispatch [:get-followers username])
		(dispatch [:get-followings username])
		(fn []
			(let [followers (subscribe [:followers username])
				  followings (subscribe [:followings username])]
				[:div.wrap-paper (css/wrap-paper-css)
				 [:header.paper-header (css/paper-header-css)
				  [:div.pic-header (css/pic-header-css)]
				  [:div.profile-username (css/profile-username-css) username]
				  [:div.profile-status (css/profile-status-css) status]]
				 [:main
				  [:nav.profile-nav (css/profile-nav-css)
				   (btn-component "follow" (fn []) (= (:username @(subscribe [:user])) username))
				   [:span {:class "link"
						   :on-click #(swap! pop-up assoc :comp (user-list  @followers pop-up) :title "Followers")}
					 (str (count @followers) " followers")]
				   [:span {:class "link"
						   :on-click #(swap! pop-up assoc :comp (user-list @followings pop-up) :title "Followings")}
					 (str (count @followings) " following")]]
				  [add-post-component (fn [])]
				  [user-posts user pop-up]]]))))

