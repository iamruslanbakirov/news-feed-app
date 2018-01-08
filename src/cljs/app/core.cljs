(ns app.core
    (:require [reagent.core :as reagent :refer [atom]]
              [re-frame.core :as rf]
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]

              [app.containers.home.views :refer [home-page-container]]))


(defonce page (atom #'home-page-container))

(defn current-page []
  [:div [@page]])

(secretary/defroute "/" []
  (reset! page #'home-page-container))

(defn mount-root []
  (rf/dispatch-sync [:init-home-db])
  (rf/dispatch [:get-user-data])
  (rf/dispatch [:get-news-data])

  (reagent/render [current-page] (.getElementById js/document "root")))

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
