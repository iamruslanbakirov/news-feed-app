(ns twitter-impl.routes.login
    (:require [hiccup.page :refer [include-js include-css html5]]
              [compojure.response :refer [render]]
              [ring.util.response :refer [redirect]]))


(def authdata
  {:admin "admin"
   :test "test"})

(defn login [req]
    (let [content (html5 [:body "Login"])]
      (render content req)))

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
      (let [content (html5 [:body "login-false"])]
        (render content req)))))
