(ns app.routes.home
    (:require [buddy.auth :refer [authenticated? throw-unauthorized]]
              [hiccup.page :refer [include-js include-css html5]]
              [compojure.response :refer [render]]
              [clojure.pprint :refer [pprint]]
              [config.core :refer [env]]))

(defn keyword-to-js-obj [keymap]
  (map (fn [[key val]]
         (let [str-key (if
                         (instance? clojure.lang.Keyword key)
                         (name key)
                         key)
               str-val (if
                         (instance? clojure.lang.Keyword val)
                         (name val)
                         val)]
           (format "window.user = {%s: '%s'};" str-key str-val))) keymap))

(defn head [user-data]
  [:head
   [:title "Twitter implementation"]
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1"}]
   (include-css "/css/style.css")
   [:script {:type "text/javascript"} (keyword-to-js-obj user-data)]])


(defn home [req]
    (if-not (authenticated? req)
      (throw-unauthorized {:message "Not authorized"})
      (let [content (html5 (head (:session req)) [:body [:div#root] (include-js "/js/app.js")])]
          (render content req))))
