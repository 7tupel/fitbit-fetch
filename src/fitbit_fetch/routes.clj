(ns fitbit-fetch.routes
  (:require
   [compojure.core :refer [defroutes GET POST DELETE ANY context]]
   [compojure.route :as route]
   [fitbit-fetch.views :as vw]
   [fitbit-fetch.endpoints :as endp]
   [compojure.handler :as compj :refer [api]]))


;; ROUTES
(defroutes routes
  ; first set all routes for views
  (GET "/" [] vw/landing-page)  ; landing page
  (GET "/terms" [] vw/terms-of-use) ; terms of use page
  ;(GET "/ws" [] chat-handler)     ;; websocket
  ; set all endpoint routes
  (GET "/authorization/oauth2/codegrant" [] endp/receive-authorization-code)
  (GET "/ping" [] endp/ping)
  ;(GET "/foo/:id" [] endp/foo)
  ; default
  (route/not-found "Page not found")) ;; all other, return 404


(def handler (-> routes compj/api))
