(ns app.containers.news-feed.subs
	(:require [re-frame.core :refer [reg-sub]]))

(reg-sub :error-news
		 (fn [db _]
			 (:error-news (:news-feed-db db))))

(reg-sub :news
		 (fn [db _]
			 (:data-news (:news-feed-db db))))

(reg-sub :loading-news?
		 (fn [db _]
			 (:loading-news? (:news-feed-db db))))

(reg-sub :new-msg-text
		 (fn [db _]
			 (get-in db [:news-feed-db :new-msg])))