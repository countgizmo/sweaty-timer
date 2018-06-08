(ns sweaty-timer.views
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [sweaty-timer.subs :as subs]
            [sweaty-timer.events :as events]
            [sweaty-timer.setup-form.views :refer [setup-panel]]
            [sweaty-timer.progress-screen.views :refer [progress-panel]]))

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [:div#main-view
      (condp = @active-panel
        :setup-panel [setup-panel]
        :progress-panel [progress-panel])]))
