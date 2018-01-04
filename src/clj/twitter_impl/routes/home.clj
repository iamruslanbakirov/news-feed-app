(ns twitter-impl.routes.home
    (:require [buddy.auth :refer [authenticated? throw-unauthorized]]
              [hiccup.page :refer [include-js include-css html5]]
              [compojure.response :refer [render]]))


(defn home [req]
    (if-not (authenticated? req)
      (throw-unauthorized {:message "Not authorized"})
      (let [content (html5 [:body "Hello world!"])]
          (render content req))))
