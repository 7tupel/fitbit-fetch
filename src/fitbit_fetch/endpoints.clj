(ns fitbit-fetch.endpoints
  (:require
   [clj-http.client :as client]
   [clojure.data.json :as json]))



;; ENDPOINTS
(defn receive-authorization-code [req]
  ;; Receive Fitbit OAuth2 Authorization Code
  ; this endpoint takes a callback request from the fitbit authorization
  ; server to receive an OAuth2 authorization code following the
  ; OAuth 2.0 Authorization Code Grant mechanism (see RFC 6749)
  ; TODO implement this!
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    ""})
