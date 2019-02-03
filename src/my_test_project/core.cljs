(ns my-test-project.core
  (:require
   ["react-blessed" :as react-blessed]
   [blessed :as blessed]
   [cljs.nodejs :as nodejs]
   ;; TODO: Which of these do we need?
   [my-test-project.debug.views :as debug]
   [my-test-project.keys :as keys]
   [my-test-project.subs]
   [my-test-project.events]
   [my-test-project.views :as views]
   [fs :as fs]
   [mount.core :refer [defstate] :as mount]
   [re-frame.core :as rf]
   [reagent.core :as r]
   [tty :as tty]))

(mount/in-cljc-mode)

(defstate tty-fd :start (fs/openSync "/dev/tty" "r+"))
(defstate program :start (blessed/program #js
                                          {:input (tty/ReadStream @tty-fd)
                                           :output (tty/WriteStream @tty-fd)}))

(defstate screen :start (doto
                         (blessed/screen #js {:program @program
                                              :autoPadding true
                                              :smartCSR true
                                              :title "my-test-project"})
                         keys/setup))

(defonce render (react-blessed/createBlessedRenderer blessed))

(defn init!
  [view]
  (mount/start)
  (rf/dispatch-sync [:init])
  (-> (r/reactify-component view)
      (r/create-element #js {})
      (render @screen)))

(defn main!
  []
  (init! views/root))

(set! *main-cli-fn* main!)
