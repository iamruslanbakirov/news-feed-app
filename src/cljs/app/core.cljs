(ns app.core
	(:require [reagent.core :as reagent
			   :refer           [atom]]
			  [re-frame.core :refer [subscribe dispatch dispatch-sync]]
			  [secretary.core :as secretary
			   :include-macros    true]
			  [accountant.core :as accountant]

			  [app.containers.root.views :refer [root-container]]
			  [app.containers.details.views :refer [details-container]]
			  [app.containers.news-feed.views :refer [news-feed-container]]))


(defonce page (atom #'details-container))

(secretary/defroute root "/" []
	(dispatch [:switch-route-name "root"])
	(reset! page (fn [] (details-container @(subscribe [:user])))))

(secretary/defroute news "/news" []
	(dispatch [:switch-route-name "news"])
	(reset! page (fn [] (news-feed-container))))

(defn mount-root []
	(dispatch-sync [:init-root-db])
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
