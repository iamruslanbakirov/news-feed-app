(ns app.util
	(:require [garden.core :as garden]
			  [app.containers.details.views :refer [details-container]]))


(defn style-tag [gcss]
	[:style (garden/css gcss)])

(defn to-details [pop-up user]
	 #(swap! pop-up assoc
	   :comp (details-container user pop-up)
	   :title (:username user)))