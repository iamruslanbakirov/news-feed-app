(ns twitter-impl.handler
  (:require [ring.middleware.session :refer [wrap-session]]
            [ring.middleware.params :refer [wrap-params]]

            [twitter-impl.middleware :refer [wrap-middleware]]

            [buddy.auth.middleware :refer [wrap-authentication wrap-authorization]]
            [twitter-impl.routes.config :refer [my-routes auth-backend]]))


(def app
  (as-> my-routes $
    (wrap-authorization $ auth-backend)
    (wrap-authentication $ auth-backend)
    (wrap-params $)
    (wrap-session $)
    (wrap-middleware $)))
