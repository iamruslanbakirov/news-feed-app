(ns app.containers.root.style
	(:require [garden.core :as garden]
			  [garden.units :as u]
			  [garden.color :as c]
			  [app.util :refer [style-tag]]))


(defn root []
	(style-tag
		[:.root
		 {:display        "flex"
		  :flex-direction "column"
		  :background     "#FAFAFA"
		  :overflow       "hidden"
		  :min-height     "100vh"}
		 [:main.root-content
		  {:display "flex"
		   :height  "calc(100vh - 105px)"}]]))

(defn root-header []
	(style-tag
		[:.root-header
		 {:background "#0277BD"
		  :z-index    "10"
		  :box-shadow "0 0 9px rgba(0,0,0,0.5)"
		  :color      "#fff"}
		 [:&--info
		  {:display         "flex"
		   :justify-content "space-between"
		   :height          (u/px 66)}]
		 [:&--item
		  {:flex-basis  "33.3333333%"
		   :padding     "0 20px"
		   :display     "flex"
		   :align-items "center"}
		  [(garden.selectors/& (garden.selectors/nth-of-type :2))
		   {:justify-content "flex-end"}]]]))

(defn root-header-nav []
	(style-tag
		[:.root-header-nav
		 {:display "flex"}
		 [:ul
		  {:display    "flex"
		   :width      "100%"
		   :list-style "none"}]
		 [:li
		  {:padding "8px 16px 10px"}
		  [:&:hover {:background "rgba(255,250,250, 0.3)"}]
		  [:&.is-active {:border-bottom "2px solid #fff"}]
		  [:a {:text-decoration "none"
			   :color           "#fff"}]]]))
