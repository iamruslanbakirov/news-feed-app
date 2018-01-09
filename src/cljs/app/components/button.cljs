(ns app.components.button
  (:require [garden.core :as garden]
        [garden.units :refer [px]]
        [garden.color :as c]
        [app.util :refer [style-tag]]))

(defn css []
  (style-tag
    [:button
     {:align-self "center"
      :padding "0 16px"
      :height (px 36)
      :font-size (px 14)
      :border-radius (px 2)
      :background "#0277BD"
      :border "none"
      :color "#fff"
      :text-transform "uppercase"
      :cursor "pointer"}]))

(defn btn [text handler]
  [:button (css) text])
