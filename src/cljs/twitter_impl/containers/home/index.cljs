(ns twitter-impl.containers.home
  (:require [reagent.core :as reagent :refer [atom]]
            [twitter-impl.containers.home.style :refer [home home-header nav]]))

(defonce nav-switch (atom false))

(defn home-page []
  [:div.home (home)
    [:header.home-header (home-header)
     [:section.home-header--item [:p "Twitter"]]
     [:section.home-header--item [:p "@admin"]]
     [:section.home-header--item
      [:nav.nav (nav)
       [:i {:class "material-icons"
            :on-click #(reset! nav-switch (not @nav-switch))} "more_vert"]
       (when @nav-switch
         [:ul
          [:li [:a {:href "logout"} "Logout"]]])]]
     [:main
      [:section "left side"]
      [:section "right side"]]]])
