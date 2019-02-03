(defproject my-test-project "0.1.0-SNAPSHOT"
  :description "FIXME"
  :url "https://github.com/FIXME/my-test-project"
  :min-lein-version "2.7.1"
  :license {:name "The Apache License, Version 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0"
            :distribution :repo}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/clojurescript "1.10.516"]
                 [mount "0.1.15"]
                 [reagent "0.8.1" :exclusions [[cljsjs/react]
                                               [cljsjs/react-dom]
                                               [cljsjs/create-react-class]]]
                 [re-frame "0.10.6"]]
  :source-paths ["src"]
  :aliases {"fig"       ["trampoline" "run" "-m" "figwheel.main"]
            "fig:build" ["trampoline" "run" "-m" "figwheel.main" "-b" "dev" "-r"]
            "fig:min"   ["trampoline" "run" "-m" "figwheel.main" "-O" "advanced" "-bo" "dev"]
            "fig:test"  ["trampoline" "run" "-m" "figwheel.main" "-co" "test.cljs.edn" "-m" my-test-project.test-runner]}
  
  :profiles {:dev {:dependencies [[cider/piggieback "0.3.10"]
                                  [org.clojure/tools.namespace "0.2.11"]
                                  [com.bhauman/figwheel-main "0.2.0"]
                                  [com.bhauman/rebel-readline-cljs "0.1.4"]]
                   :repl-options {:nrepl-middleware [cider.piggieback/wrap-cljs-repl]}
                   :clean-targets ["target" "node_modules"]
                   :source-paths ["src" "env/dev"]}})
