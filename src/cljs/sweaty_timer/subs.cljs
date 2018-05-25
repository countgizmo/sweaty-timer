(ns sweaty-timer.subs
  (:require [re-frame.core :as re-frame]))

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
