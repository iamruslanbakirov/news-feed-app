(ns twitter-impl.handler
  (:require [clojure.pprint :refer [pprint]]
            [compojure.core :refer [GET POST defroutes]]
            [compojure.route :refer [not-found resources]]
            [compojure.response :refer [render]]

            [ring.util.response :refer [response redirect content-type]]
            [ring.middleware.session :refer [wrap-session]]
            [ring.middleware.params :refer [wrap-params]]

            [hiccup.page :refer [include-js include-css html5]]
            [twitter-impl.middleware :refer [wrap-middleware]]
            [config.core :refer [env]]

            [buddy.auth :refer [authenticated? throw-unauthorized]]
            [buddy.auth.backends.session :refer [session-backend]]
            [buddy.auth.middleware :refer [wrap-authentication wrap-authorization]]))


(def mount-target
  [:div#app
      [:h3 "ClojureScript has not been compiled!"]
      [:p "please run "
       [:b "lein figwheel"]
       " in order to start the compiler"]])

(defn head []
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1"}]
   (include-css (if (env :dev) "/css/site.css" "/css/site.min.css"))])

(defn loading-page []
  (html5
    (head)
    [:body {:class "body-container"}
     mount-target
     (include-js "/js/app.js")]))

(defn home [req]
    (if-not (authenticated? req)
      (throw-unauthorized {:message "Not authorized"})
      (let [content (html5 (head) [:body "Hello world!"])]
          (render content req))))

(defn login [req]
    (let [content (html5 (head) [:body "Login"])]
      (render content req)))

(defn logout [req]
    (-> (redirect "/login")
        (assoc :session {})))

(def authdata
    {:admin "admin"
     :test "test"})

(defn login-authenticate [req]
    (let [username (get-in req [:form-params "username"])
          password (get-in req [:form-params "password"])
          session (:session req)
          found-password (get authdata (keyword username))]
         (if (and found-password (= found-password password))
           (let [next-url (get-in req [:query-params :next] "/")
                 updated-session (assoc session :identity (keyword username))]
             (-> (redirect next-url)
                 (assoc :session updated-session)))
           (let [content (html5 (head) [:body "login-false"])]
              (render content req)))))



(defn unauthorized-handler [req metadata]
    (cond
        (authenticated? req)
        (-> (render (html5 (head) [:body "Error"]) req)
            (assoc :status 403))
        :else
        (let [current-url (:uri req)]
          (redirect (format "login?next=%s" current-url)))))

(def auth-backend
  (session-backend {:unauthorized-handler unauthorized-handler}))

(defroutes my-routes
  ; (GET "/" [] (loading-page))
  ; (GET "/about" [] (loading-page))
  (GET "/" [] home)
  (GET "/login" [] login)
  (POST "/login" [] login-authenticate)
  (GET "/logout" [] logout)

  (resources "/")
  (not-found "Not Found"))

(def app
  (as-> my-routes $
    (wrap-authorization $ auth-backend)
    (wrap-authentication $ auth-backend)
    (wrap-params $)
    (wrap-session $)
    (wrap-middleware $)))
