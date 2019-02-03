(ns ^:figwheel-no-load my-test-project.app
  (:require [my-test-project.core :as core]
            [my-test-project.debug.views :as debug]))

(defn main!
  [& args]
  (core/init! debug/root))
(main!)
(defn log-fn
  [& args]
  (swap! debug/logger conj (clojure.string/join " " args)))

(set! (.-log js/console) log-fn)
(set! (.-error js/console) log-fn)
(set! (.-info js/console) log-fn)
(set! (.-debug js/console) log-fn)
(re-frame.loggers/set-loggers! {:log log-fn
                                :warn log-fn})
