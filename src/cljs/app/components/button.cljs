(ns app.components.button
	(:require [garden.core :as garden]
			  [garden.units :refer [px]]
			  [garden.selectors :as s]
			  [garden.color :as c]
			  [app.util :refer [style-tag]]

			  [app.components.drop-circle-wrap :refer [drop-circle-wrap-cmpt]]))

(defn css []
	(style-tag
	 [:.button-default-component
	  {:align-self     "center"
	   :padding        "0 16px"
	   :height         (px 36)
	   :font-size      (px 14)
	   :border-radius  (px 2)
	   :background     "#0277BD"
	   :border         "none"
	   :color          "#fff"
	   :text-transform "uppercase"
	   :box-shadow     "1px 1px 5px rgba(0,0,0,0.3)"
	   :transition     "all 0.2s linear"
	   :cursor         "pointer"}
	  [:&:hover
	   {:background "rgba(2,119,189, 0.759)"
		:box-shadow "1px 1px 5px rgba(0,0,0,0.5)"}]

	  [(s/& (s/attr :disabled))
	   {:background "rgba(0,0,0,0.7)"
		:cursor     "default"}]]))

(defn btn [text handler disable?]
	(let [attr {:on-click handler
				:class    "button-default-component"}]
		[:button
		 (if disable? (assoc attr :disabled "disabled") attr)
		 (css)
		 text]))


(defn btn-component [& args]
	(apply btn args))
