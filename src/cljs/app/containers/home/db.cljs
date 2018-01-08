(ns app.containers.home.db)

(def default-home-db
  {:my-posts []
   :friends []
   :loading-news? false
   :loading-user? false
   :data-user nil
   :error-user nil
   :data-news nil
   :error-news nil})
