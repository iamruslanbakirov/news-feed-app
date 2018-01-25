(ns app.components.menu-list
  (:require [reagent.core :as r]
            [garden.core :as garden]
            [garden.units :refer [px]]
            [garden.color :as c]
            [app.util :refer [style-tag]]))


(defn css []
  (style-tag [:.menu-paper-list
              {:position "relative"}
              [:i {:padding (px 5)}]
              [:i:hover
                {:background "rgba(255,255,255,0.11)"
                 :border-radius (px 3)
                 :cursor "pointer"}]
              [:&__wrapper {:position "fixed"
                            :top 0
                            :bottom 0
                            :right 0
                            :z-index 11
                            :left 0}]
              [:ul {:position "absolute"
                    :display "block"
                    :z-index 12
                    :right 0
                    :top 0
                    :width (px 120)
                    :padding "8px 0"
                    :background "#fff"
                    :box-shadow "0 0 9px rgba(0,0,0,0.7)"
                    :font-size (px 15)}
               [:li {:padding-left (px 24)
                     :height (px 32)
                     :align-items "center"
                     :display "flex"
                     :cursor "pointer"
                     :color "rgba(0,0,0,0.87)"}
                  [:&:hover {:background "rgba(0,0,0,0.11)"}]
                  [:a {:text-decoration "none"
                       :color "rgba(0,0,0,0.87)"}]]]]))


(defn menu-list-component [links]
  (let [nav-switch (r/atom false)
        handler #(reset! nav-switch (not @nav-switch))]
    (fn []
      [:nav.menu-paper-list (css)
       [:i {:class "material-icons"
            :on-click handler}
        "more_vert"]
       (when @nav-switch
         [:div
          [:div {:class "menu-paper-list__wrapper"
                 :on-click handler}]
          [:ul
           (for [link links]
             [:li
              {:key (link :href)}
              [:a {:href (link :href)} (link :title)]])]])])))
