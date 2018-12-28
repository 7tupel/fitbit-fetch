(ns fitbit-fetch.core
  (:gen-class)
  (:require
   [clojure.edn :as edn]
   [clj-http.client :as client]
   [clojure.data.json :as json]))


;; CONFIGURATION
(def config
  (let [v (-> (slurp "config.edn") (edn/read-string))]
    ; (assert (contains? v :ident) "Service ident not configured.")
    ; (assert (contains? v :topic) "Log topic name not configured.")
    ; (assert (contains? v :auth) "No authentication configured.")
    ; (assert (contains? v :redis) "Redis connection not configured.")
    ; (assert (contains? v :server) "Server not configured.")
    v))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
