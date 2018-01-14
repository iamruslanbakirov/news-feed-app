(ns app.containers.root.views
  (:require [reagent.core :as r]
            [re-frame.core :refer [subscribe dispatch]]

            [app.containers.root.style :refer [root root-header]]
            [app.containers.news-feed.views :refer [news-feed-container]]
            [app.containers.details.views :refer [details-container]]
            [app.components.menu-list :refer [menu-list-component]]
            [app.containers.root.db]
            [app.containers.root.events]
            [app.containers.root.subs]))

(def menu-list [{:href "/" :title "My profile"}
                {:href "/news" :title "News"}
                {:href "/logout" :title "Logout"}])


(defn root-container [page]
  (dispatch [:get-user-data])
  (fn []
    (let [user @(subscribe [:user])]
        [:div.root (root)
          [:header.root-header (root-header)
           [:section.root-header--item [:p "Twitter"]]
           [:section.root-header--item [:p (:username user)]]
           [:section.root-header--item
            [menu-list-component menu-list]]]
          [:main {:class "root-content"}
           [@page]]])))
