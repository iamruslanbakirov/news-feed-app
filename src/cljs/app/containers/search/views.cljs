(ns app.containers.search.views
	(:require [reagent.core :as reagent
			   :refer           [atom]]
			  [re-frame.core :refer [subscribe dispatch]]

			  [app.containers.details.views :refer [details-container]]
			  [app.components.users-list :refer [user-list]]
			  [app.containers.search.subs]
			  [app.containers.search.events]
			  [app.containers.search.db]
			  [app.containers.search.style :refer [search-container-css]]))

(defn search-container [pop-up]
	(let [click-handler (fn [user] (swap! pop-up assoc
										  :comp  (details-container user pop-up)
										  :title (:username user)))]
		(fn []
			(let [users @(subscribe [:search-users])
				  search-str @(subscribe [:search-str])
				  str-len (count @(subscribe [:search-str]))
				  is-loading? @(subscribe [:loading-search-data])]
				[:div.search
				 (search-container-css)
				 [:label.search__field
				  [:input {:on-change #(dispatch [:change-search-str (-> % .-target .-value)])
						   :value search-str}]

				  [:span.border]
				  [:span.caption
				   {:class (when (> str-len 0) "is-active")}
				   "Entry username"]

				  (when (> str-len 0)
					  [:i {:class "material-icons"
						   :on-click #(dispatch [:change-search-str ""])}
					   "close"])]
				 [:div.users-list
				  (if (> (count users) 0)
					  [(user-list users details-container pop-up)]
					  [:div.is-empty "It's empty..."])]]))))