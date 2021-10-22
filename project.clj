(defproject todomvc-reframe-shadow-cljs "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [org.clojure/clojurescript "1.10.866" :scope "provided"]

                 ;; Also include shadow-cljs.edn deps here so that Cursive can find them
                 [reagent "1.1.0"]
                 [re-frame "1.2.0"]
                 [day8.re-frame/tracing "0.6.2"]
                 [day8.re-frame/re-frame-10x "1.1.11"]]

  :source-paths ["src"]

  :test-paths ["test"])
