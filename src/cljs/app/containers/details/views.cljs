(ns app.containers.details.views
  (:require [reagent.core :as reagent :refer [atom]]
            [app.containers.details.style :as css]
            [app.components.news-item :refer [news-item]]
            [app.components.button :refer [btn]]))

(def state (atom {:username "@admin"
                  :status "Hello world!"
                  :followers (range 1000)
                  :following (range 222)}))

(defn details-container []
  (let [username (:username @state)]
    [:section.profile (css/details-profile-css)
     [:div.wrap-paper (css/wrap-paper-css)
      [:header.paper-header (css/paper-header-css)
       [:div.pic-header (css/pic-header-css)]
       [:div.profile-username (css/profile-username-css) username]
       [:div.profile-status (css/profile-status-css) (:status @state)]]
      [:main
       [:nav.profile-nav (css/profile-nav-css)
        (btn "follow" (fn []))
        [:span [:a
                {:href (str "/followers/" username)}
                (count (:followers @state)) " followers"]]
        [:span [:a
                {:href (str "/following/" username)}
                (count (:following @state)) " following"]]]
       [:div
        (news-item "@name" "My first post!" 1111111111)]]]]))
