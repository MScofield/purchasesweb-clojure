(defproject purchasesweb-clojure "0.0.1-SNAPSHOT"
  :description "a crazy little thing called love"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring "1.4.0"]
                 [hiccup "1.0.5"]
                 [compojure "1.5.0"]]
  :javac-options ["-target" "1.6" "-source" "1.6" "-Xlint:-options"]
  :aot [purchasesweb-clojure.core]
  :main purchasesweb-clojure.core)
