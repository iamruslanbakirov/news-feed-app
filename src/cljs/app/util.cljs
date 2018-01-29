(ns app.util
	(:require [garden.core :as garden]
			  [ajax.core :refer [GET POST]]))

(defn style-tag [gcss]
	[:style (garden/css gcss)])

(defn get-user-posts [username handler error-handler]
	(GET (str "/api/posts/" username)
		 {:handler         handler
		  :error-handler   error-handler
		  :response-format :json
		  :keywords?       true}))

(defn get-followers [username handler error-handler]
	(GET (str "/api/followers/" username)
		 {:handler        handler
		  :error-handler   error-handler
		  :response-format :json
		  :keywords?       true}))

(defn get-followings [username handler error-handler]
	(GET (str "/api/followings/" username)
		 {:handler        handler
		  :error-handler   error-handler
		  :response-format :json
		  :keywords?       true}))

(defn get-user [username handler]
	(GET (str "/api/users/" username)
		 {:handler handler
		  :response-format :json
		  :keywords?       true}))