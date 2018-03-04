(ns app.containers.details.db)

(def default-details-db
	{:followers        []
	 :followings       []
	 :user-posts       []
	 :last-post        nil
	 :sending-message? false
	 :loading-data?    false
	 :loading-user?    false
	 :data-user        nil})
