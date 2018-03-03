(ns app.containers.pop-up.views
	(:require [app.containers.pop-up.style :refer [css]]
			  [re-frame.core :refer [dispatch]]
			  [secretary.core :refer [dispatch!]]))

(defn pop-up-container [comp title]
	[:div.pop-up
	 (css)
	 [:header
	  {:class "pop-up__header"}
	  [:i {:class "material-icons" :on-click #(dispatch [:reset-pop-up])} "close"]
	  [:span {:class "title"} title]]
	 [:main (comp)]])