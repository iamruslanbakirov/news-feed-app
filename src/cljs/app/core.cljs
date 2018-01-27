(ns app.core
	(:require [reagent.core :as reagent
			   :refer           [atom]]
			  [re-frame.core :refer [subscribe dispatch dispatch-sync]]
			  [secretary.core :as secretary
			   :include-macros    true]
			  [accountant.core :as accountant]

			  [app.containers.root.views :refer [root-container]]
			  [app.containers.details.views :refer [details-container]]
			  [app.containers.news-feed.views :refer [news-feed-container]]
			  [app.containers.pop-up.views :refer [pop-up-container]]))


(defonce page (atom #'details-container))
(defonce pop-up (atom {:comp nil :title nil}))

(secretary/defroute root "/" []
	(dispatch [:switch-route-name "root"])
	(reset! page (fn [] (details-container @(subscribe [:user]) pop-up))))

(secretary/defroute news "/news" []
	(dispatch [:switch-route-name "news"])
	(reset! page (fn [] (news-feed-container pop-up))))

(defn mount-root []
	(dispatch-sync [:init-root-db])
	(dispatch-sync [:init-details-db])
	(reagent/render [(root-container page pop-up)] (.getElementById js/document "root")))

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
