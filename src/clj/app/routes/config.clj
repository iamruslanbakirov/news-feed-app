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
              [app.routes.login :refer [login login-authenticate]]))

(defn logout [req]
  (-> (redirect "/login")
    (assoc :session {})))

(defn user-handler [req]
  (response {:username "admin" :id 1}))

(defn news-handler [req]
  (response [{:id 1
              :author "@TestNik"
              :text "Hello everyone!"
              :time 1515440957898}
             {:id 2
              :author "@TestSecond"
              :text "On friday i wanna go sleep"
              :time 1515440857898}]))

(defroutes my-routes
  (GET "/" [] home)
  (GET "/login" [] login)
  (POST "/login" [] login-authenticate)
  (GET "/logout" [] logout)
  ; REST API
  (GET "/user" [] (wrap-json-response user-handler))
  (GET "/news" [] (wrap-json-response news-handler))

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
