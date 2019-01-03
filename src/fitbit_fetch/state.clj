(ns fitbit-fetch.state
  (:require
   [clj-http.client :as client]
   [clj-time.core :as time]))


;; STATE
(defonce state (atom nil))


(defn mk-initial-state []
  ; create the inital state with/o any authorization credentials
  {:authorized false
   :user nil
   :access-token nil
   :refresh-token nil
   :csrf-token nil
   :last-token-refresh nil
   :last-fetch nil})


(defn print-state []
  (println state))

(defn get-state
  ([] @state)
  ([key] (get @state key)))

(defn set-csrf-token! [csrf]
  ; set the csrf token for a code grand request. the token is checked by the
  ; oauth 2.0 code grant redirect endpoint
  (swap! state #(-> %
                    (assoc :csrf-token csrf))))

(defn set-authorization-credentials! [user access-token refresh-token]
  ; set the authorization credentials after a successful authorization
  ; by the fitbit authorization server. set the user, access-and refresh token
  (swap! state #(-> %
                 (assoc :authorized true
                        :user user
                        :access-token access-token
                        :refresh-token refresh-token
                        :last-token-refresh (time/now)))))

(defn refresh-authorization! [access-token refresh-token]
  ; refresh the authorization credentials
  ; set a new value for booth the access-token and the refresh token.
  ; must be called after a refresh token was consumed to get an new access-and
  ; refresh token from the fitbit authorization server
  (swap! state #(-> %
                 (assoc :access-token access-token
                        :refresh-token refresh-token
                        :last-token-refresh (time/now)))))

(defn fetched-data! []
  ; update the time when data was last fetched from the fitbit API
  (swap! state #(-> %
                 (assoc :last-fetch (time/now)))))
