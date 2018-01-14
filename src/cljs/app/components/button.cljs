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
      :box-shadow "1px 1px 5px rgba(0,0,0,0.3)"
      :transition "all 0.2s linear"
      :cursor "pointer"}
     [:&:hover {:background "rgba(2,119,189, 0.759)"
                :box-shadow "1px 1px 5px rgba(0,0,0,0.5)"}]]))

(defn btn [text handler]
  [:button {:on-click handler} (css)
   text])
