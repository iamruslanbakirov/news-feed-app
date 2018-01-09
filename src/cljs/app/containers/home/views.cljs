(ns app.containers.home.views
  (:require [reagent.core :as r]
            [re-frame.core :refer [subscribe dispatch]]

            [app.containers.home.style :refer [home home-header nav]]
            [app.containers.news-feed.views :refer [news-feed-container]]
            [app.containers.details.views :refer [details-container]]

            [app.containers.home.db]
            [app.containers.home.events]
            [app.containers.home.subs]))

(defonce nav-switch (r/atom false))

(defn home-page-container []
  (let [username (:username @(subscribe [:user]))]
    [:div.home (home)
      [:header.home-header (home-header)
       [:section.home-header--item [:p "Twitter"]]
       [:section.home-header--item [:p username]]
       [:section.home-header--item
        [:nav.nav (nav)
         [:i {:class "material-icons"
              :on-click #(reset! nav-switch (not @nav-switch))} "more_vert"]
         (when @nav-switch
           [:ul
            [:li [:a {:href "logout"} "Logout"]]])]]]
      [:main {:class "home-content"}
       (news-feed-container)
       (details-container)]]))
