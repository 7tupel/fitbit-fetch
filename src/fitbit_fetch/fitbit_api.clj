(ns fitbit-fetch.fitbit-api
  (:require
   [clj-http.client :as client]
   [clj-http.util :refer [url-encode]]
   [clojure.string]
   [fitbit-fetch.state :as state]))




(defn get-authorization-url [cnf]
  (str
   (:authorization-uri (:fitbit cnf))
   "?response_type=code"
   "&client_id=" (url-encode (:client-id (:fitbit cnf)))
   "&redirect_uri=" (url-encode (:redirect-uri (:fitbit cnf)))
   "&scope=" (url-encode (clojure.string/join " " (:scope (:fitbit cnf))))
   "&prompt=" (url-encode (:prompt (:fitbit cnf)))
   "&state=" (url-encode (state/get-state :csrf-token))))
