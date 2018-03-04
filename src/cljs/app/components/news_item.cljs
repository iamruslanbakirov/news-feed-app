(ns app.components.news-item
	(:require [garden.core :as garden]
			  [garden.units :as u]
			  [garden.color :as c]
			  [reagent.core :as reagent
			   :refer           [atom]]
			  [app.util :refer [style-tag]]
			  [cljsjs.moment]
			  [app.util :refer [get-user]]
			  [re-frame.core :refer [dispatch]]
			  [clojure.walk :refer [keywordize-keys]]))

(defn css []
	(style-tag
	 [:.news-item
	  {:margin         "20px 10px 0"
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

(defn news-item [details-container & param]
	(let [req-handler          (fn [user]
								   (dispatch [:switch-pop-up
											  (details-container user)
											  (:username user)]))
		  switch-state-handler (fn [] (get-user (nth param 0) req-handler))]
		[:div.news-item
		 (css)
		 [:span.news-item__name
		  {:on-click switch-state-handler}
		  (nth param 0)]
		 [:div.news-item__text
		  (nth param 1)]
		 [:span.news-item__time
		  (-> (js/moment (nth param 2)) (.fromNow))]]))
