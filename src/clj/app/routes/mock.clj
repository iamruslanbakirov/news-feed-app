(ns app.routes.mock
	(:require [ring.util.response :refer [response]]))

(defn user-handler [req]
	(response
	 {:username  (:identity req)
	  :id        1
	  :status    "Hello world!"}))

(defn news-handler [req]
	(response
	 [{:id       1
	   :username "@TestNik"
	   :text     "Hello everyone!"
	   :time     1515440957898}
	  {:id       2
	   :username "@TestSecond"
	   :text     "On friday i wanna go sleep"
	   :time     1515440857898}]))

(defn my-posts-handler [req]
	(response
	 [{:username "admin"
	   :text     "Hello"
	   :time     12312
	   :id       1}
	  {:username "admin"
	   :text     "world"
	   :time     123123
	   :id       2}]))

(defn my-followers-handler [req]
	(response
	 [{:username "test followers"
	   :id       2
	   :status   "Test status"}
	  {:username "Mock"
	   :id       3
	   :status   "Hello test!"}]))

(defn my-followings-handler [req]
	(response
	 [{:username "test"
	   :id       2
	   :status   "Test status"}
	  {:username "Mock"
	   :id       3
	   :status   "Hello test!"}]))

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
	 {:admin [2 3]
	  :test  [3]
	  :mock  [2]}))

(defn get-user [username]
	(fn [req]
		(response
		 (first (filterv #(= username (:username %)) @users-store)))))

(defn get-posts [username]
	(fn [req]
		(response
		 (filterv #(= username (:username %)) @posts-store))))

(defn get-followers [username]
	(fn [req]
		(response
		 (filterv
		  (fn [obj]
			  (some #(= (:id obj) %)
					(get @followers-store (keyword username))))
		  @users-store))))

(defn get-followings [username]
	(fn [req]
		(response
		 (filterv
		  (fn [obj]
			  (some #(= (:id obj) %)
					(get @followings-store (keyword username))))
		  @users-store))))

;;(defn user-handler [req]
;;	(response
;;	 (first
;;	  (filterv
;;	   #(= (:identity req) (:username %))
;;	   @users-store))))
