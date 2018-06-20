(ns sweaty-timer.subs
  (:require [re-frame.core :as re-frame]
            [sweaty-timer.time-util :refer [time-left-percent]]
            [cljs-time.core :as time]
            [clojure.string :as string]))

(re-frame/reg-sub
  ::time
  (fn [db]
    (:time db)))

(re-frame/reg-sub
  ::duration
  (fn [db] (:duration db)))

(re-frame/reg-sub
  ::from
  (fn [db] (:from db)))

(re-frame/reg-sub
  ::time-diff-minutes
  (fn [db] (:diff db)))

(re-frame/reg-sub
  ::time-diff-percentage
  (fn [{:keys [seconds-left duration]}]
    (time-left-percent seconds-left (* 60 duration))))

(re-frame/reg-sub
  ::active-panel
  (fn [db _] (:active-panel db)))

(re-frame/reg-sub
  ::active-movement
  (fn [db _] (:active-movement db)))

(re-frame/reg-sub
  ::current-min
  (fn [db _] (:current-min db)))

(re-frame/reg-sub
  ::movements
  (fn [db _]
    (->> (:movements db)
         (string/join \newline))))
