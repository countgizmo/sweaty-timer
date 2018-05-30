(ns sweaty-timer.views
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [sweaty-timer.subs :as subs]
            [sweaty-timer.events :as events]))

(defn clock []
  (let [time (re-frame/subscribe [::subs/time-diff-minutes])]
    [:div (str @time)]))

(defn start
  [duration]
  [:div
   [:button#start-button
    {:on-click #(re-frame.core/dispatch [::events/start duration])}
    "Start!"]])

(defn setup-timer-form []
  (let [time (reagent/atom "20")]
    (fn []
      [:div
        [:span "Duration (min): "]
        [:input {:type :text
                 :value @time
                 :on-change #(reset! time (-> % .-target .-value))}]
        [start @time]])))

(defn main-panel []
  [:div#main-view "Sweaty Timer is Here!"
   [clock]
   [setup-timer-form]])
