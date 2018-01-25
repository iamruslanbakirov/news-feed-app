(ns app.components.news-item
	(:require [garden.core :as garden]
			  [garden.units :as u]
			  [garden.color :as c]
			  [reagent.core :as reagent
			   :refer           [atom]]
			  [app.util :refer [style-tag]]
			  [cljsjs.moment]))

(defn css []
	(style-tag
		[:.news-item
		 {:margin         "20px 20px 0"
		  :display        "flex"
		  :flex-direction "column"
		  :border-bottom  "1px solid rgba(0,0,0,0.11)"}
		 [:&--name
		  {:font-size       (u/px 19)
		   :cursor          "pointer"
		   :text-decoration "underline"
		   :color           "#01579B"
		   :margin-bottom   (u/px 7)}]
		 [:&--text
		  {:font-size     (u/px 15)
		   :padding-left  (u/px 25)
		   :text-overflow "ellipsis"}]
		 [:&--time
		  {:align-self "flex-end"
		   :order      2
		   :font-size  (u/px 11)}]]))

(defn news-item [name text date]
	[:div.news-item
	 (css)
	 [:span.news-item--name name]
	 [:div.news-item--text text]
	 [:span.news-item--time (-> (js/moment date) (.fromNow))]])
