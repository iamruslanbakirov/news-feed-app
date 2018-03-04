(ns app.containers.root.subs
	(:require [re-frame.core :refer [reg-sub]]))

(reg-sub :current-route-name
		 (fn [db _]
			 (:route-current-name (:root-db db))))

(reg-sub :pop-up-sub
		 (fn [db]
			 (get-in db [:root-db :pop-up])))
