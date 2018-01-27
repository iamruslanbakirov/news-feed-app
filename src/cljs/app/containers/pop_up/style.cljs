(ns app.containers.pop-up.style
	(:require [garden.core :as garden]
			  [garden.units :as u]
			  [garden.color :as c]
			  [app.util :refer [style-tag]]))

(defn css []
	(style-tag
		[:.pop-up
		 {:position "absolute"
		  :left 0
		  :top 0
		  :bottom 0
		  :right 0
		  :background "#fff"
		  :z-index 10}
		 [:.pop-up__header
		  {:height (u/px 66)
		   :display "flex"
		   :align-items "center"
		   :padding "0 20px"
		   :border-bottom "1px solid rgba(0,0,0,0.2)"}
		  [:i
		   {:color "rgba(0,0,0,0.7)"
			:cursor "pointer"
			:font-size (u/px 36)}]
		  [:i:hover
		   {:color "rgba(0,0,0,0.9)"
		   	:background "rgba(0,0,0,0.2)"
			:border-radius "50%"}]
		  [:.title {:padding "0 10px"}]]]))