(ns fitbit-fetch.endpoints
  (:require
   [clj-http.client :as client]
   [clojure.data.json :as json]
   [taoensso.timbre :as timbre :refer [log warn]]
   [fitbit-fetch.util :as util]
   [fitbit-fetch.state :as state :only [set-csrf-token! print-state]]))



(defn require-param
  ;; validate a given request contains the given parameter
  ; used to validate if a request contains a given parameter. optionally a
  ; _value_ can be given and is compared to the parameters value.
  ; throws an clojure.lang.ExceptionInfo exception if the parameter is not
  ; available or the parameters value is a mismatch
  ([req param msg]
   (if (contains? (:params req) param)
       nil
       (throw (ex-info msg {:type :parameter-error}))))
  ([req param value msg]
   (if (and (contains? (:params req) param) (= value ('param (:params req))))
       nil
       (throw (ex-info msg {:type :parameter-error})))))


;; ENDPOINTS
(defn receive-authorization-code [req]
  ;; Receive Fitbit OAuth 2.0 Authorization Code
  ; this endpoint takes a callback request from the fitbit authorization
  ; server to receive an OAuth2 authorization code following the
  ; OAuth 2.0 Authorization Code Grant mechanism (see RFC 6749)
  ; TODO implement this!
  (try
   ((require-param req :access_token "missing access token")
    (require-param req :expires_in "missing expiration time")
    (require-param req :scopes "no scopes defined")
    (require-param req :state "missing state information")
    (require-param req :user_id "missing user")
    (require-param req :access_token "missing access token")
    (require-param req :token_type "Bearer" "missing or invalid token type")
    ({:status  200
      :headers {"Content-Type" "text/html"}
      :body    "ok"}))
   (catch clojure.lang.ExceptionInfo exc
    (timbre/warn (.getMessage exc))
    {:status  400
     :headers {"Content-Type" "text/html"}
     :body    ""})))



(defn ping [req]
  {:status  200
   :headers {"Content-Type" "text/plain"}
   :body    "ok"})
