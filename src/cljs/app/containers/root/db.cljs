(ns app.containers.root.db)

(def default-root-db
	{:loading-user?      false
	 :data-user          nil
	 :error-user         nil
	 :pop-up {:comp nil :title nil}
	 :route-current-name "root"})
