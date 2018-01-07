(ns app.core
    (:require [reagent.core :as reagent :refer [atom]]
              [re-frame.core :as rf]
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]

              [app.containers.home :refer [home-page-container]]))

(defn dispatch-page [new-page]
  (rf/dispatch [:switch-page new-page]))

(rf/reg-event-db :initialize
  (fn [_ _] {:page home-page-container}))

(rf/reg-event-db :switch-page
  (fn [db [_ new-page-value]]
    (assoc db :page new-page-value)))

(rf/reg-sub :page (fn [db _] (:page db)))

(defn current-page []
  [:div [@(rf/subscribe [:page])]])

(secretary/defroute "/" []
  (dispatch-page home-page-container))

(defn mount-root []
  (rf/dispatch-sync [:initialize])
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
