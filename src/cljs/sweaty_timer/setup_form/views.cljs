(ns sweaty-timer.setup-form.views
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [sweaty-timer.subs :as subs]
            [sweaty-timer.events :as events]))

(defn start
  [duration]
  [:div
   [:button#start-button
    {:on-click #(re-frame.core/dispatch [::events/start duration])}
    "Start!"]])

(defn setup-panel []
  (let [time (reagent/atom "20")]
    (fn []
      [:div
        [:span "Duration (min): "]
        [:input {:type :text
                 :value @time
                 :on-change #(reset! time (-> % .-target .-value))}]
        [start @time]])))
