(ns fitbit-fetch.views
  (:require
   [clojure.data.json :as json]
   [me.shenfeng.mustache :refer :all]))



;; TEMPLATES
(deftemplate tmpl-landing-page (slurp "templates/landing.tpl"))
(deftemplate tmpl-terms-of-use (slurp "templates/terms-of-use.tpl"))


;; VIEWS
(defn landing-page [req]
  ;; Langing Page View
  ; show the landing page to a user
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (tmpl-landing-page nil)})

(defn terms-of-use [req]
  ;; Terms of Use View
  ; show the terms of use page to a user
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (tmpl-terms-of-use nil)})
