(ns app.containers.details.views
  (:require [reagent.core :as reagent :refer [atom]]
            [re-frame.core :refer [subscribe dispatch]]

            [app.containers.details.style :as css]
            [app.components.news-item :refer [news-item]]
            [app.components.button :refer [btn]]))


(defn details-container [user]
  (let [username (:username user)
        status (:status user)
        followers (:followers user)
        following (:following user)]
    [:div.wrap-paper (css/wrap-paper-css)
     [:header.paper-header (css/paper-header-css)
      [:div.pic-header (css/pic-header-css)]
      [:div.profile-username (css/profile-username-css) username]
      [:div.profile-status (css/profile-status-css) status]]
     [:main
      [:nav.profile-nav (css/profile-nav-css)
       (btn "follow" (fn []))
       [:span [:a
               {:href (str "/followers/" username)}
               (str followers " followers")]]
       [:span [:a
               {:href (str "/following/" username)}
               (str following " following")]]]
      [:div
       (news-item "@name" "My first post!" 1111111111)]]]))
