(ns twitter-impl.prod
  (:require [twitter-impl.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
