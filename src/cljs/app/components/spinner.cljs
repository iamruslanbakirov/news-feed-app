(ns app.components.spinner)


(defn spiner-component []
	[:div.spinner-wrap
	 [:span "Loading..."]
	 [:div.spinner-loader
	  [:div.spinner-runner]]])