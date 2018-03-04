(ns app.components.empty
	(:require [garden.core :as garden]
			  [garden.units :refer [px]]
			  [garden.selectors :as s]
			  [garden.color :as c]
			  [app.util :refer [style-tag]]))

(defn css []
	(style-tag
	 [:div.is-empty
	  {:margin "10px"}]))

(defn empty-comp []
	[:div.is-empty (css) "It's empty..."])
