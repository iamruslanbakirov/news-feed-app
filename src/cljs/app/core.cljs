(ns app.core
    (:require [reagent.core :as reagent :refer [atom]]
              [re-frame.core :as rf]
              [re-frame.core :refer [subscribe dispatch]]
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]

              [app.containers.root.views :refer [root-container]]
              [app.containers.details.views :refer [details-container]]
              [app.containers.news-feed.views :refer [news-feed-container]]))



(defonce page (atom #'details-container))

(secretary/defroute "/" []
  (reset! page (fn [] (details-container @(subscribe [:user])))))

(secretary/defroute "/news" []
  (reset! page (fn [] (news-feed-container))))

(defn mount-root []
  (rf/dispatch-sync [:init-root-db])
  (reagent/render [(root-container page)] (.getElementById js/document "root")))

(defn init! []
  (accountant/configure-navigation!
    {:nav-handler
     (fn [path]
       (secretary/dispatch! path))
     :path-exists?
     (fn [path]
       (secretary/locate-route path))})
  (accountant/dispatch-current!)
  (mount-root))
