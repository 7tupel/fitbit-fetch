(ns fitbit-fetch.core
  (:gen-class)
  (:require
   [clojure.edn :as edn]
   [clj-http.client :as client]
   [clojure.data.json :as json]
   [compojure.core :refer [defroutes GET POST DELETE ANY context]]
   [org.httpkit.server :refer [run-server]]
   [me.shenfeng.mustache :refer :all]
   [clojure.core.async :as async]
   [fitbit-fetch.routes :refer [routes]]))


;; CONFIGURATION
(def config
  (let [v (-> (slurp "config.edn") (edn/read-string))]
    (assert (contains? v :server) "Server not configured!")
    v))


;; EVENTBUS
(def eventbus (async/chan))


;; STATE
(defonce state (atom nil))



(defn -main
  [& args]
  (println "starting the http server...")
  (run-server routes (:server config)))
