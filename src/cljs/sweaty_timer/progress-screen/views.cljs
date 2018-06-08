(ns sweaty-timer.progress-screen.views
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [sweaty-timer.subs :as subs]
            [sweaty-timer.events :as events]))

(defn clock []
  (let [time (re-frame/subscribe [::subs/time-diff-minutes])]
    [:div (str @time)]))

(defn progress-bar []
  (let [p (re-frame/subscribe [::subs/time-diff-percentage])]
    [:div
     [:progress {:max 100 :value @p}]]))

(defn exit []
  [:button#exit-button
    {:on-click #(re-frame.core/dispatch [::events/set-active-panel :setup-panel])}
    "Exit"])

(defn progress-panel []
  [:div
   [clock]
   [progress-bar]
   [exit]])
