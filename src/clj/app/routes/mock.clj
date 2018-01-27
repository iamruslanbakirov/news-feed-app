(ns app.routes.mock
	(:require [ring.util.response :refer [response]]))

(defn user-handler [req]
	(response
	 {:username  (:identity req)
	  :id        1
	  :status    "Hello world!"}))

(defn news-handler [req]
	(response
	 [{:id     1
	   :username "@TestNik"
	   :text   "Hello everyone!"
	   :time   1515440957898}
	  {:id     2
	   :username "@TestSecond"
	   :text   "On friday i wanna go sleep"
	   :time   1515440857898}]))

(defn my-posts-handler [req]
	(response
	 [{:username "admin"
	   :text "Hello"
	   :time 12312
	   :id 1}
	  {:username "admin"
	   :text "world"
	   :time 123123
	   :id 2}]))

(defn my-followers-handler [req]
	(response [{:username  "test followers"
				:id        2
				:status    "Test status"}
			   {:username  "Mock"
				:id        3
				:status    "Hello test!"}]))

(defn my-followings-handler [req]
	(response [{:username  "test followings"
				:id        2
				:status    "Test status"}
			   {:username  "Mock"
				:id        3
				:status    "Hello test!"}]))