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

(defn time-left-percent
  [time total]
  (int (/ (* 100 time) total)))

(defn seconds-between
  [t1 t2]
  (if (<= t1 t2)
    (time/in-seconds (time/interval t1 t2))
    0))

(defn current-min-seconds
  [total left]
  (if (<= left 0)
    0
    (- 60 (mod (- total left) 60))))

(defn percentage-left-current-min
  [total left]
  (let [s (current-min-seconds total left)]
    (int (/ (* 100 s) 60))))
