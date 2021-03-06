(ns sweaty-timer.events
  (:require [re-frame.core :as re-frame]
            [sweaty-timer.db :as db]
            [cljs-time.core :as time]
            [clojure.string :as string]
            [sweaty-timer.time-util :refer [time-diff
                                            time-diff-str
                                            seconds-between
                                            percentage-left-current-min]]
            [sweaty-timer.movements-parser :refer [active-movement]]
            [sweaty-timer.voice :refer [speak]]))

(re-frame/reg-event-db
  ::initialize-db
  (fn [_ _]
    db/default-db))

(re-frame/reg-cofx
  :now
  (fn [coeffects _]
    (assoc coeffects :now (time/now))))

(re-frame/reg-fx
  :speak
  (fn [what] (speak what)))

(re-frame/reg-event-fx
  ::tick
  [(re-frame/inject-cofx :now)]
  (fn [{:keys [db now]} _]
    (when (not (:paused? db))
      (when-let [end (:end db)]
        (let [diff (time-diff now end)
              paused? (zero? diff)
              s-left (seconds-between now end)
              duration (* 60 (get db :duration 0))
              movs (get db :movements [])
              movement (active-movement duration s-left movs)
              say? (zero? (mod s-left 60))]
          (merge
            {:db (assoc db
                   :diff (time-diff-str diff)
                   :paused? paused?
                   :seconds-left s-left
                   :active-movement movement
                   :current-min (percentage-left-current-min duration s-left))}
            (when say? {:speak movement})))))))

(re-frame/reg-event-db
  ::set-active-panel
  (fn [db [_ value]]
    (assoc db :active-panel value)))

(re-frame/reg-event-fx
  ::exit
  (fn [{:keys [db]} _]
    {:db db/default-db
     :dispatch [::set-active-panel :setup-panel]}))

(re-frame/reg-event-fx
  ::start
  [(re-frame/inject-cofx :now)]
  (fn [{:keys [db now]} [_ duration movements]]
    (let [end (time/plus now (time/minutes (int duration)))
          movements (string/split-lines movements)
          active-movement (first movements)]
      {:db (assoc db
             :end end
             :paused? false
             :duration duration
             :active-panel :progress-panel
             :movements movements)
       :speak active-movement})))
