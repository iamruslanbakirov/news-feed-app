(ns twitter-impl.routes.config
    (:require [compojure.core :refer [defroutes GET POST]]
              [compojure.route :refer [not-found resources]]
              [compojure.response :refer [render]]
              [hiccup.page :refer [include-js include-css html5]]
              [ring.util.response :refer [response redirect]]
              [ring.util.response :refer [redirect]]
              [buddy.auth.backends.session :refer [session-backend]]
              [buddy.auth :refer [authenticated?]]
              [config.core :refer [env]]
              [twitter-impl.routes.home :refer [home]]
              [twitter-impl.routes.login :refer [login login-authenticate]]))

(defn logout [req]
  (-> (redirect "/login")
    (assoc :session {})))

(defroutes my-routes
  (GET "/" [] home)
  (GET "/login" [] login)
  (POST "/login" [] login-authenticate)
  (GET "/logout" [] logout)
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
