(ns app.util
	(:require [garden.core :as garden]
			  [ajax.core :refer [GET POST]]))

(def ajax-params
	{:response-format :json
	 :keywords?       true
	 :format          :json})

(defn style-tag [gcss]
	[:style (garden/css gcss)])

(defn get-user-posts [username handler error-handler]
	(GET (str "/api/posts/" username)
		 (merge ajax-params
				{:handler       handler
				 :error-handler error-handler})))

(defn get-followers [username handler error-handler]
	(GET (str "/api/followers/" username)
		 (merge ajax-params
				{:handler       handler
				 :error-handler error-handler})))

(defn get-followings [username handler error-handler]
	(GET (str "/api/followings/" username)
		 (merge ajax-params
				{:handler       handler
				 :error-handler error-handler})))

(defn get-user [username handler]
	(GET (str "/api/users/" username)
		 (merge ajax-params {:handler handler})))