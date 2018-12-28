(defproject fitbit-fetch "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Apache 2.0 License"
            :url "https://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :plugins [[lein-tools-deps "0.4.1"]]
  :middleware [lein-tools-deps.plugin/resolve-dependencies-with-deps-edn]
  :lein-tools-deps/config {:config-files ["deps.edn" :install :user :project]
                           :clojure-executables ["/usr/bin/clojure"]}
  :main ^:skip-aot fitbit-fetch.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
