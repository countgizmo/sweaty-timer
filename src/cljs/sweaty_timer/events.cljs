(ns sweaty-timer.events
  (:require [re-frame.core :as re-frame]
            [sweaty-timer.db :as db]
            [cljs-time.core :as time]
            [sweaty-timer.time-util :refer [time-diff
                                            time-diff-str
                                            seconds-between]]))

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
    (when (not (:paused? db))
      (when-let [end (:end db)]
        (let [diff (time-diff now end)
              paused (zero? diff)
              s-left (seconds-between now end)]
          {:db (assoc db
                 :diff (time-diff-str diff)
                 :paused? paused
                 :seconds-left s-left)})))))

(re-frame/reg-event-db
  ::set-active-panel
  (fn [db [_ value]]
    (assoc db :active-panel value)))

(re-frame/reg-event-fx
  ::start
  [(re-frame/inject-cofx :now)]
  (fn [{:keys [db now]} [_ duration]]
    (let [end (time/plus now (time/minutes (int duration)))]
      {:db (assoc db
             :end end
             :paused? false
             :duration duration
             :active-panel :progress-panel)})))
