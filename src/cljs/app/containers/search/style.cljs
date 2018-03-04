(ns app.containers.search.style
	(:require [garden.core :as garden]
			  [garden.units :as u]
			  [garden.color :as c]
			  [app.util :refer [style-tag]]))

(def caption-active
	{:font-size (u/px 13)
	 :top (u/px -13)
	 :color "rgb(2,119,189)"})

(defn search-field []
	[:.search__field
	 {:position "relative"
	  :margin "20px 10px"}
	 [:input
	  {:width "100%"
	   :background "transparent"
	   :height (u/px 30)
	   :font-size (u/px 19)
	   :border "none"
	   :border-bottom "2px solid rgba(2,119,189,0.54)"}
	  [:&:focus
	   {:outline "none"}]]
	 [(garden.selectors/+ :input:focus :.border)
	  {:width "100%"
	   :left 0}]
	 [:.border
	  {:border-bottom "2px solid rgb(2,119,189)"
	   :width 0
	   :left "50%"
	   :transition "all 0.2s ease"
	   :position "absolute"
	   :bottom 0}]
	 [:.caption
	  {:position "absolute"
	   :left 0
	   :transition "all 0.2s ease"
	   :top (u/px 7)}]
	 [(garden.selectors/- :input:focus :.caption)
	  caption-active]
	 [:.caption.is-active caption-active]
	 [:i
	  {:position "absolute"
	   :right 0
	   :cursor "pointer"
	   :padding "1.5px"}]])

(defn search-container-css []
	(style-tag
	 [:.search
	  {:width "100%"
	   :display "flex"
	   :flex-direction "column"}
	   (search-field)]))