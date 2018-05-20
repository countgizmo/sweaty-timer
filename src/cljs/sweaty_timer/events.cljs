(ns sweaty-timer.events
  (:require [re-frame.core :as re-frame]
            [sweaty-timer.db :as db]
            ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))
