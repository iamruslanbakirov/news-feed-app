(ns app.containers.news-feed.db)

(def default-news-feed-db
	{:loading-news? false
	 :data-news nil
	 :new-msg nil
	 :error-news nil})