(ns app.components.users-list
	(:require [reagent.core :as r]
			  [garden.core :as garden]
			  [garden.units :refer [px]]
			  [garden.color :as c]
			  [app.util :refer [style-tag]]
			  [app.containers.details.views :refer [details-container]]
			  [app.util :refer [to-details]]))

(defn css []
	(style-tag [:.user-list
				[:li:hover {:background "rgba(0,0,0,0.1)"
							:cursor "pointer"}]
				[:li {:width "100%"
					  :padding "0 20px"
					  :height (px 60)
					  :display "flex"
					  :align-items "center"}
				 [:img {:height (px 40)
						:width (px 40)
						:border-radius "50%"
						:background "rgba(0,0,0,0.7)"
						:border "none"}]
				 [:.username {:margin "0 10px"}]]]))

(defn user-list [list pop-up]
	(fn []
		[:div.user-list (css)
		 [:ul
		   (for [item list]
			   [:li
				{:key (:id item)
				 :on-click (to-details pop-up item)}
				[:img {:src (:pic item)}]
				[:span {:class "username"} (:username item)]])]]))