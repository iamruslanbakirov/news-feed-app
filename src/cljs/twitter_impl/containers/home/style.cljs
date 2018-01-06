(ns twitter-impl.containers.home.style
  (:require [garden.core :as garden]
            [garden.units :as units]
            [garden.color :as color]))

(defn style-tag [gcss]
  [:style (garden/css gcss)])

(defn home []
  (style-tag [:.home
              {:display "flex"
               :flex-direction "column"
               :background "#FAFAFA"
               :min-height "100vh"}]))

(defn home-header []
  (style-tag [:.home-header
              {:display "flex"
               :background "#0277BD"
               :height (units/px 66)
               :justify-content "space-between"
               :box-shadow "0 0 9px rgba(0,0,0,0.5)"
               :color "#fff"}
              [:&--item {:flex-basis "33.3333333%"
                         :padding "0 20px"
                         :display "flex"
                         :align-items "center"}
               [(garden.selectors/& (garden.selectors/nth-of-type :2)) {:justify-content "center"}]
               [(garden.selectors/& (garden.selectors/nth-of-type :3)) {:justify-content "flex-end"}]]]))

(defn nav []
  (style-tag [:.nav
              {:position "relative"}
              [:i:hover
                {:color "rgba(0,0,0,0.11)"
                 :cursor "pointer"}]
              [:ul {:position "absolute"
                    :display "block"
                    :right 0
                    :width (units/px 120)
                    :padding "8px 0"
                    :background "#fff"
                    :box-shadow "0 0 9px rgba(0,0,0,0.7)"
                    :font-size (units/px 15)}
               [:li {:padding-left (units/px 24)
                     :height (units/px 32)
                     :align-items "center"
                     :display "flex"
                     :color "rgba(0,0,0,0.87)"}
                  [:&:hover {:background "rgba(0,0,0,0.11)"}]
                  [:a {:text-decoration "none"
                       :color "rgba(0,0,0,0.87)"}]]]]))
