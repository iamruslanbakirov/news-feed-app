(ns app.containers.root.style
  (:require [garden.core :as garden]
            [garden.units :as u]
            [garden.color :as c]
            [app.util :refer [style-tag]]))


(defn root []
  (style-tag [:.root
              {:display "flex"
               :flex-direction "column"
               :background "#FAFAFA"
               :overflow "hidden"
               :min-height "100vh"}
              [:main.root-content
               {:display "flex"
                :height "calc(100vh - 66px)"}]]))

(defn root-header []
  (style-tag [:.root-header
              {:display "flex"
               :background "#0277BD"
               :height (u/px 66)
               :z-index "10"
               :justify-content "space-between"
               :box-shadow "0 0 9px rgba(0,0,0,0.5)"
               :color "#fff"}
              [:&--item {:flex-basis "33.3333333%"
                         :padding "0 20px"
                         :display "flex"
                         :align-items "center"}
               [(garden.selectors/& (garden.selectors/nth-of-type :2)) {:justify-content "center"}]
               [(garden.selectors/& (garden.selectors/nth-of-type :3)) {:justify-content "flex-end"}]]]))
