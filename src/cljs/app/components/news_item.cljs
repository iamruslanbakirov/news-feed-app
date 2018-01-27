(ns app.components.news-item
	(:require [garden.core :as garden]
			  [garden.units :as u]
			  [garden.color :as c]
			  [reagent.core :as reagent
			   :refer           [atom]]
			  [app.util :refer [style-tag]]
			  [cljsjs.moment]
			  [ajax.core :refer [GET]]
			  [app.util :refer [to-details]]))

(defn css []
	(style-tag
	 [:.news-item
	  {:margin         "20px 20px 0"
	   :display        "flex"
	   :flex-direction "column"
	   :border-bottom  "1px solid rgba(0,0,0,0.11)"}
	  [:&__name
	   {:font-size       (u/px 19)
		:cursor          "pointer"
		:text-decoration "underline"
		:color           "#01579B"
		:margin-bottom   (u/px 7)
		:align-self      "flex-start"}]
	  [:&__text
	   {:font-size     (u/px 15)
		:padding-left  (u/px 25)
		:text-overflow "ellipsis"}]
	  [:&__time
	   {:align-self "flex-end"
		:order      2
		:font-size  (u/px 11)}]]))

(defn news-item [pop-up & param]
	[:div.news-item
	 {:on-click
	  #(GET (str "/api/users/" (nth param 0))
		{:handler (to-details pop-up %1)})}
	 (css)
	 [:span.news-item__name (nth param 0)]
	 [:div.news-item__text (nth param 1)]
	 [:span.news-item__time (-> (js/moment (nth param 2)) (.fromNow))]])
