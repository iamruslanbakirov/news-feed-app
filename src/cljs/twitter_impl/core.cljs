(ns twitter-impl.core
    (:require [reagent.core :as reagent :refer [atom]]
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]

              [twitter-impl.containers.home :refer [home-page]]))


(defonce page (atom #'home-page))
(defonce pending-request (atom 0))

(defn current-page []
  [:div [@page]])

(secretary/defroute "/" []
  (reset! page #'home-page))


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
