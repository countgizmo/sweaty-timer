(ns sweaty-timer.views
  (:require [re-frame.core :as re-frame]
            [sweaty-timer.subs :as subs]
            [sweaty-timer.events :as events]))

(defn clock []
  (let [time (re-frame/subscribe [::subs/time-diff-minutes])]
    [:div (str @time)]))

(defn start []
  [:button#start-button
   {:on-click #(re-frame.core/dispatch [::events/start])}
   "Start!"])

(defn main-panel []
  [:div#main-view "Sweaty Timer is Here!"
   [clock]
   [start]])
