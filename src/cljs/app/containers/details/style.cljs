(ns app.containers.details.style
  (:require [garden.core :as g]
            [garden.units :refer [px]]
            [garden.color :as c]

            [app.util :refer [style-tag]]))

(defn details-profile-css []
  (style-tag [:.profile
              {:display "flex"
               :flex-basis "50%"
               :width "50%"
               :flex-direction "column"}]))

(defn paper-header-css []
  (style-tag [:.paper-header
              {:height (px 130)
               :background "linear-gradient(to bottom, rgba(2,119,189, 0.6), rgba(2,119,189, 0))"
               :display "flex"
               :flex-direction "row"
               :padding (px 10)
               :justify-content "center"
               :align-items "center"}]))


(defn wrap-paper-css []
  (style-tag [:.wrap-paper
              {:min-height "calc(100% - 20px)"
               :margin (px 10)
               :background "#fff"
               :box-shadow "1px 1px 9px rgba(0,0,0,0.3)"
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
