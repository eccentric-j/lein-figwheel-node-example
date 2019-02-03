(ns my-test-project.views
  (:require [my-test-project.debug.views :as debug]))

(defn root [_]
  [:box#base {:left   0
              :right  0
              :width  "100%"
              :height "100%"}
   [debug/debug-box {:height 10}]])
