(ns app.routes.config
	(:require [compojure.core :refer [defroutes GET POST]]
			  [compojure.route :refer [not-found resources]]
			  [compojure.response :refer [render]]
			  [hiccup.page :refer [include-js include-css html5]]

			  [ring.util.response :refer [response redirect]]
			  [ring.middleware.json :refer [wrap-json-response wrap-json-body]]

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
	;spa
	(GET "/" [] home)
	(GET "/news" [] home)
	(GET "/search" [] home)
	(GET "/popup" [] home)

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

	;; new
	(POST "/api/message" []
		  (wrap-json-response
		   (wrap-json-body
			(fn [req]
				(mock/add-message-handler
				 (get-in req [:body :message])
				 (name (:identity req))))
			{:keywords? true :bigdecimals? true})))

	(GET "/api/unfollow/:username" [username]
		 (println username))

	(GET "/api/follow/:username" [username]
		 (println username))

	(POST "/api/search" []
		  (wrap-json-response
		   (wrap-json-body
			(fn [req]
				(mock/search-users (get-in req [:body :search])))
			{:keywords? true})))

	(resources "/")
	(not-found "Not Found"))

(defn unauthorized-handler [req metadata]
	(if
		(authenticated? req)
		(-> (render (html5 [:body "Error"]) req)
			(assoc :status 403))
		(redirect (format "login?next=%s" (:uri req)))))

(def auth-backend
	(session-backend {:unauthorized-handler unauthorized-handler}))
