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
(defonce config
  (let [v (-> (slurp "config.edn") (edn/read-string))]
    (assert (contains? v :server) "Server not configured!")
    v))


;; EVENTBUS
(defonce eventbus (async/chan))


;; STATE
(defonce state (atom nil))


;; The SERVER
(defonce server (atom nil))



(defn stop-server! []
  ; stop the running server with a short timeout period to finish
  ; running requests
  (when (some? @server)
    ;(log/info "stopping transactor")
    (@server :timeout 200)
    (reset! server nil)))

(defn start-server! [handler]
  ; start the server
  (when-not (some? @server)
    (.addShutdownHook (Runtime/getRuntime) (Thread. stop-server!))
    (reset! server (run-server handler (:server config)))))

(defn restart-server! [handler]
  ; restart the server
  (stop-server!)
  (start-server! handler))


(defn -main
  [& args]
  (println "starting the http server...")
  ;(run-server routes (:server config)))
  (start-server! routes))
