(ns my-test-project.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
  :db
  (fn [db _]
    db))
