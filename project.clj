(defproject paint-term "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-RC2"]]
  :main paint-term.main
  :target-path "target/%s"
  :uberjar-name "paint-term.jar"
  :profiles {:uberjar {:aot :all}})
