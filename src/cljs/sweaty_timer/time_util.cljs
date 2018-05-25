(ns sweaty-timer.time-util
  (:require [cljs-time.core :as time]
            [cljs-time.format :as time-format]
            [cljs-time.coerce :as time-coerce]))

(def timer-format
  (time-format/formatter "mm:ss"))

(defn time-left-str
  [t1 t2]
  (->> (- t2 t1)
      (time-coerce/from-long)
      (time-format/unparse timer-format)))
