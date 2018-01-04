(ns ^:figwheel-no-load twitter-impl.dev
  (:require
    [twitter-impl.core :as core]
    [devtools.core :as devtools]))

(devtools/install!)

(enable-console-print!)

(core/init!)
