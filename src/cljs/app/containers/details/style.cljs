(ns app.containers.details.style
  (:require [garden.core :as g]
            [garden.units :refer [px]]
            [garden.color :as c]

            [app.util :refer [style-tag]]))

(defn paper-header-css []
  (style-tag [:.paper-header
              {:height (px 130)
               ; :background "linear-gradient(to bottom, rgba(2,119,189, 1), rgba(2,119,189, 0))"
               :display "flex"
               :flex-direction "row"
               :padding (px 10)
               :justify-content "center"
               :align-items "center"}]))


(defn wrap-paper-css []
  (style-tag [:.wrap-paper
              {:min-height "calc(100% - 20px)"
               :width "100%"
               :background "#fff"
               :border-radius (px 3)}]))

(defn pic-header-css []
  (style-tag [:.pic-header
              {:width (px 80)
               :height (px 80)
               :background "rgba(0,0,0,0.7)"
               :border-radius (px 2)}]))

(defn profile-username-css []
  (style-tag [:.profile-username
              {:flex-grow 1
               :margin "30px 0 0 20px"
               :align-self "flex-start"}]))

(defn profile-status-css []
  (style-tag [:.profile-status
              {:align-self "flex-end"
               :font-size (px 14)}]))

(defn profile-nav-css []
  (style-tag [:.profile-nav
              {:height (px 60)
               :display "flex"
               :justify-content "space-around"
               :align-items "center"}]))
