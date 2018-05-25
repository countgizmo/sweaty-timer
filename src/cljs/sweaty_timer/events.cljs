(ns sweaty-timer.events
  (:require [re-frame.core :as re-frame]
            [sweaty-timer.db :as db]
            [cljs-time.core :as time]
            [sweaty-timer.time-util :refer [time-left-str]]))

(re-frame/reg-event-db
  ::initialize-db
  (fn [_ _]
    db/default-db))

(re-frame/reg-cofx
  :now
  (fn [coeffects _]
    (assoc coeffects :now (time/now))))

(re-frame/reg-event-fx
  ::tick
  [(re-frame/inject-cofx :now)]
  (fn [{:keys [db now]} _]
    (when-let [end (:end db)]
      {:db (assoc db :diff (time-left-str now end))})))

(re-frame/reg-event-fx
  ::start
  [(re-frame/inject-cofx :now)]
  (fn [{:keys [db now]} _]
    (let [end (time/plus now (time/minutes (:duration db)))]
      {:db (assoc db :end end)})))
