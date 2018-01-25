(ns app.components.drop-circle-wrap
	(:require [garden.core :as garden]
			  [reagent.core :as r]
			  [garden.units :refer [px]]
			  [garden.color :as c]
			  [app.util :refer [style-tag]])
	(:require-macros [garden.def :refer [defstyles defrule defkeyframes]]))


(defn css []
	(style-tag
		[:.circle-wrap
		 {:position "relative"
		  :overflow "hidden"}
		 [:svg
		  {:position "absolute"
		   :width    (px 50)
		   :height   (px 50)}
		  [:circle {:fill "rgba(255,255,255,0.3)"}]]]))

(defn drop-circle-wrap-cmpt [some-cmpt]
	[:div.circle-wrap
	 {:on-click (fn [event]
					(let [x (-> event .-pageX)
						  y (-> event .-pageY)]
						(println [x y])))}
	 (css)
	 some-cmpt
	 [:svg
	  [:circle
	   {:cx 20
		:cy 20
		:r  25}]]])
