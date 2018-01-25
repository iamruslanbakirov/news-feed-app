(ns app.handler
	(:require [ring.middleware.session :refer [wrap-session]]
			  [ring.middleware.params :refer [wrap-params]]

			  [app.middleware :refer [wrap-middleware]]

			  [buddy.auth.middleware :refer [wrap-authentication wrap-authorization]]
			  [app.routes.config :refer [my-routes auth-backend]]))


(def app
	(as-> my-routes $
		  (wrap-authorization $ auth-backend)
		  (wrap-authentication $ auth-backend)
		  (wrap-params $)
		  (wrap-session $)
		  (wrap-middleware $)))
