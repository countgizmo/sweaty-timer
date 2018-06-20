(ns sweaty-timer.progress-screen.views
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [sweaty-timer.subs :as subs]
            [sweaty-timer.events :as events]))

(defn clock []
  (let [time (re-frame/subscribe [::subs/time-diff-minutes])]
    [:span @time]))

(defn active-movement []
  (let [mov (re-frame/subscribe [::subs/active-movement])]
    [:div#active-movement {:class "centered"} @mov]))

(defn progress-bar-total []
  (let [p (re-frame/subscribe [::subs/time-diff-percentage])]
    [:div#progress-bar-total
     [:progress {:max 100 :value @p}]]))

(defn progress-bar-current []
  (let [t (re-frame/subscribe [::subs/current-min])]
    [:div#progress-bar-current
     [:progress {:max 100 :value @t}]]))

(defn exit []
  [:button.button
    {:on-click #(re-frame.core/dispatch [::events/exit])}
    "Exit"])

(defn progress-panel []
  [:div
   [exit]
   [active-movement]
   [progress-bar-total]
   [progress-bar-current]])
