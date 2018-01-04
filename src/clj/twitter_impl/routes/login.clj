(ns twitter-impl.routes.login
    (:require [hiccup.page :refer [include-js include-css html5]]
              [compojure.response :refer [render]]
              [ring.util.response :refer [redirect]]

              [config.core :refer [env]]))


(def authdata
  {:admin "admin"
   :test "test"})

(defn head []
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1"}]
   (include-css (if (env :dev) "/css/login-page.css" "/css/site.min.css"))])

(defn root-login [error]
  [:div#root-login
   (when (not= error nil) (error))
   [:h1 {:class "title"}"Login page"]
   [:form {:method "post"}
    [:label
     [:input {:name "username"}]
     [:span "Login"]]
    [:label
     [:input {:name "password"}]
     [:span "Password"]]
    [:button {:type "submit"} "Start"]]])

(defn error-login []
    [:div {:class "error"} "Login or password incorrect"])


(defn login [req]
    (let [content (html5 (head) [:body (root-login nil)])]
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
      (let [content (html5 (head) [:body (root-login error-login)])]
        (render content req)))))
