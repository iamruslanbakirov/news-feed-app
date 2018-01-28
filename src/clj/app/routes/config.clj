(ns app.routes.config
	(:require [compojure.core :refer [defroutes GET POST]]
			  [compojure.route :refer [not-found resources]]
			  [compojure.response :refer [render]]
			  [hiccup.page :refer [include-js include-css html5]]

			  [ring.util.response :refer [response redirect]]
			  [ring.middleware.json :refer [wrap-json-response]]

			  [buddy.auth.backends.session :refer [session-backend]]
			  [buddy.auth :refer [authenticated?]]
			  [config.core :refer [env]]

			  [app.routes.home :refer [home]]
			  [app.routes.login :refer [login login-authenticate]]
			  [app.routes.mock :as mock]))

(defn logout [req]
	(-> (redirect "/login")
		(assoc :session {})))

(defroutes my-routes
	(GET "/" [] home)
	(GET "/news" [] home)
	(GET "/login" [] login)
	(POST "/login" [] login-authenticate)
	(GET "/logout" [] logout)


	; REST API
	(GET "/api/user" [] (wrap-json-response mock/user-handler))
	(GET "/api/news" [] (wrap-json-response mock/get-news-resp))

	(GET "/api/users/:username" [username]
		 (wrap-json-response (mock/get-user-resp username)))

	(GET "/api/posts/:username" [username]
		 (wrap-json-response (mock/get-posts-resp username)))

	(GET "/api/followers/:username" [username]
		 (wrap-json-response (mock/get-followers-resp username)))

	(GET "/api/followings/:username" [username]
		 (wrap-json-response (mock/get-followings-resp username)))

	(resources "/")
	(not-found "Not Found"))

(defn unauthorized-handler [req metadata]
	(cond
		(authenticated? req)
		(-> (render (html5 [:body "Error"]) req)
			(assoc :status 403))
		:else
		(let [current-url (:uri req)]
			(redirect (format "login?next=%s" current-url)))))

(def auth-backend
	(session-backend {:unauthorized-handler unauthorized-handler}))
