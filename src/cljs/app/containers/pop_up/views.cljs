(ns app.containers.pop-up.views
	(:require [app.containers.pop-up.style :refer [css]]))

(defn pop-up-container [comp title handler]
	[:div.pop-up
	 (css)
	 [:header
	  {:class "pop-up__header"}
	  [:i {:class "material-icons" :on-click handler} "close"]
	  [:span {:class "title"} title]]
	 [:main (comp)]])