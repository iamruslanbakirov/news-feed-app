(ns app.routes.mock
	(:require [ring.util.response :refer [response]]
			  [compojure.route :refer [not-found]]))

(defonce posts-store
	(atom
	 [{:username "admin"
	   :text     "Hello"
	   :time     12312
	   :id       1}
	  {:username "admin"
	   :text     "world"
	   :time     123123
	   :id       2}
	  {:id       3
	   :username "test"
	   :text     "Hello everyone!"
	   :time     1515440957898}
	  {:id       4
	   :username "mock"
	   :text     "On friday i wanna go sleep"
	   :time     1515440857898}]))

(defonce users-store
	(atom
	 [{:username "admin"
	   :id       1
	   :status   "Hello world!"}
	  {:username "test"
	   :id       2
	   :status   "Test status"}
	  {:username "mock"
	   :id       3
	   :status   "Hello test!"}]))

(defonce followers-store
	(atom
	 {:admin [2 3]
	  :test  [1]
	  :mock  [1 2]}))

(defonce followings-store
	(atom
	 {:admin [2]
	  :test  [3]
	  :mock  [2]}))

(defn get-user [username]
	(first (filterv #(= username (:username %)) @users-store)))

(defn get-user-resp [username]
	(fn [req]
		(let [user (get-user username)]
			(cond
				(not= user nil)
				(response user)
				:else (not-found "404 not found")))))

(defn get-posts [username]
	(filterv #(= username (:username %)) @posts-store))

(defn get-posts-resp [username]
	(fn [req]
		(response
		 (get-posts username))))

(defn get-subs [username list]
	(filterv
	 (fn [obj]
		 (some #(= (:id obj) %)
			   (get list (keyword username))))
	 @users-store))

(defn get-followers-resp [username]
	(fn [req]
		(response (get-subs username @followers-store))))

(defn get-followings-resp [username]
	(fn [req]
		(response (get-subs username @followings-store))))

(defn get-news [username]
	(let [followings          (get-subs username @followings-store)
		  followings-username (map #(:username %) followings)]
		(filterv
		 (fn [post]
			 (some #(= (:username post) %) followings-username))
		 @posts-store)))

(defn get-news-resp [req]
	(response (get-news (:identity req))))

(defn user-handler [req]
	(let [user (get-user (name (:identity req)))]
		(cond
			(not= user nil)
			(response user)
			:else (not-found "404 not found"))))
