(ns app.components.add-post
	(:require [garden.core :as garden]
			  [reagent.core :as r]
			  [garden.units :refer [px]]
			  [garden.color :as c]
			  [app.util :refer [style-tag]]

			  [app.components.button :refer [btn-component]]))

(defn css []
	(style-tag
	 [:.add-post-component
	  {:display         "flex"
	   :flex-wrap       "wrap"
	   :justify-content "space-between"
	   :padding         "10px"}
	  [:textarea
	   {:height        (px 80)
		:padding       (px 10)
		:width         "100%"
		:resize        "none"
		:border        "1px solid rgba(0,0,0,0.2)"
		:border-radius (px 3)
		:outline       "none"}]
	  [:&__status {:margin "10px 0"
				   :color  "rgba(0,0,0,0.5)"}]
	  [:.is-error {:color "red"}]
	  [:button {:align-self "flex-end"
				:margin     "10px 0"}]]))


(defn add-post-component [handler]
	(let [input-val (r/atom "")
		  max-len   140
		  error? #(> (count @input-val) max-len)]
		(fn []
			[:div.add-post-component
			 (css)
			 [:textarea
			  {:placeholder "What do you think?"
			   :autoFocus   ""
			   :value       @input-val
			   :on-change   #(reset! input-val (-> % .-target .-value))}]
			 [:span
			  {:class
			   (str "add-post-component__status "
					(when (error?) "is-error"))}
			  (str (count @input-val) "/" max-len)]
			 (btn-component "Send" #(handler input-val) (error?))])))
