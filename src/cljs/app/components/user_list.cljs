(ns app.components.users-list
	(:require [garden.core :as g]
			  [garden.units :refer [px]]
			  [garden.color :as c]
			  [re-frame.core :refer [dispatch]]
			  [app.util :refer [style-tag]]))

(defn css []
	(style-tag
		[:.user-list
		 [:li:hover {:background "rgba(0,0,0,0.1)"
					 :cursor     "pointer"}]
		 [:li
		  {:width       "100%"
		   :padding     "0 20px"
		   :height      (px 60)
		   :display     "flex"
		   :align-items "center"}
		  [:img
		   {:height        (px 40)
			:width         (px 40)
			:border-radius "50%"
			:background    "rgba(0,0,0,0.7)"
			:border        "none"}]
		  [:.username {:margin "0 10px"}]]]))

(defn user-list [list details-container]
	(let [handler #(dispatch [:switch-pop-up (details-container %) (:username %)])]
		(fn []
			[:div.user-list
			 (css)
			 [:ul
			  (for [item list]
				  [:li
				   {:key      (:id item)
					:on-click (fn [] (handler item))}
				   [:img {:src (:pic item)}]
				   [:span {:class "username"} (:username item)]])]])))