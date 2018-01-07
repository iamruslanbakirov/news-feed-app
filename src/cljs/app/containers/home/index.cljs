(ns app.containers.home
  (:require [reagent.core :as reagent :refer [atom]]
            [app.containers.home.style :refer [home home-header nav]]
            [app.containers.news-feed :refer [news-feed-container]]
            [app.containers.details :refer [details-container]]))

(defonce nav-switch (atom false))

(defn home-page-container []
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
          [:li [:a {:href "logout"} "Logout"]]])]]]
    [:main
     (news-feed-container)
     (details-container)]])
