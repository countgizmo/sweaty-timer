(ns sweaty-timer.events
  (:require [re-frame.core :as re-frame]
            [sweaty-timer.db :as db]))

(re-frame/reg-event-db
  ::initialize-db
  (fn [_ _]
    db/default-db))

(re-frame/reg-cofx
  :now
  (fn [coeffects _]
    (assoc coeffects :now (js/Date.))))

(re-frame/reg-event-fx
  ::tick
  [(re-frame/inject-cofx :now)]
  (fn [{:keys [db now]} _]
   {:db (assoc db :time now)}))
