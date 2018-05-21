(ns sweaty-timer.views
  (:require [re-frame.core :as re-frame]
            [sweaty-timer.subs :as subs]))

(defn clock[]
  (let [time (re-frame/subscribe [::subs/time])
        mins (.getMinutes @time)
        secs (.getSeconds @time)]
    [:div (str mins ":" secs)]))

(defn main-panel []
  [:div#main-view "Sweaty Timer is Here!"
   [clock]])
