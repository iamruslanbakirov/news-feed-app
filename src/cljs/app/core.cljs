(ns app.core
    (:require [reagent.core :as reagent :refer [atom]]
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]

              [app.containers.home :refer [home-page-container]]))


(defonce page (atom #'home-page-container))
(defonce pending-request (atom 0))

(defn current-page []
  [:div [@page]])

(secretary/defroute "/" []
  (reset! page #'home-page-container))


(defn mount-root []
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
