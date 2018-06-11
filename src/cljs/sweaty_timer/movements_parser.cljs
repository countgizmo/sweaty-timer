(ns sweaty-timer.movements-parser
  (:require [clojure.string :as str]))

(defn active-movement
  "Determines the active movements based on:
      total - total duration in seconds
      left - seconds left till the end of workout
      movs - vector of movements"
  [total left movs]
  (if (or (empty? movs)
          (<= left 0) "Rest")
    (let [pos (/ (- total left) 60)
          pos (mod pos (count movs))]
      (get movs pos "Rest"))))
