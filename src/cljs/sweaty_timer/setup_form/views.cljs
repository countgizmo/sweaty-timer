(ns sweaty-timer.setup-form.views
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [sweaty-timer.subs :as subs]
            [sweaty-timer.events :as events]))

(defn start
  [duration movs]
  [:div
   [:button#start-button
    {:on-click #(re-frame.core/dispatch [::events/start duration movs])}
    "Start!"]])

(defn setup-panel []
  (let [time (reagent/atom "20")
        movements (reagent/atom "")]
    (fn []
      [:div
        [:span "Duration (min): "]
        [:input {:type :text
                 :value @time
                 :on-change #(reset! time (-> % .-target .-value))}]
        [:div#movements
         [:div "Movements"]
         [:textarea {:value @movements
                        :on-change #(reset! movements (-> % .-target .-value))}]]
        [start @time @movements]])))
