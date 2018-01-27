(ns app.util
	(:require [garden.core :as garden]))

(defn style-tag [gcss]
	[:style (garden/css gcss)])