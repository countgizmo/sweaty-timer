(ns sweaty-timer.time-util
  (:require [cljs-time.core :as time]
            [cljs-time.format :as time-format]
            [cljs-time.coerce :as time-coerce]))

(def timer-format
  (time-format/formatter "mm:ss"))

(defn time-diff
  [t1 t2]
  (if (< t2 t1)
    0
    (- t2 t1)))

(defn time-diff-str
  [diff]
  (->> diff
      (time-coerce/from-long)
      (time-format/unparse timer-format)))

(defn time-left-str
  [t1 t2]
  (-> (time-diff t1 t2) (time-diff-str)))
