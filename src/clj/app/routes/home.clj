(ns app.routes.home
	(:require [buddy.auth :refer [authenticated? throw-unauthorized]]
			  [hiccup.page :refer [include-js include-css html5]]
			  [compojure.response :refer [render]]
			  [clojure.pprint :refer [pprint]]
			  [config.core :refer [env]]))

(defn head []
	[:head
	 [:title "Twitter implementation"]
	 [:meta {:charset "utf-8"}]
	 [:meta
	  {:name    "viewport"
	   :content "width=device-width, initial-scale=1"}]
	 (include-css "/css/style.css")])


(defn spiner-component []
	[:div.spinner-wrap
	 [:span "Loading..."]
	 [:div.spinner-loader
	  [:div.spinner-runner]]])

(defn home [req]
	(if-not (authenticated? req)
		(throw-unauthorized {:message "Not authorized"})
		(let [content (html5 (head) [:body [:div#root (spiner-component) (include-js "/js/app.js")]])]
			(render content req))))
