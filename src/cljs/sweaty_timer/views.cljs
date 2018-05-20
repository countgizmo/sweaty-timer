(ns sweaty-timer.views
  (:require [re-frame.core :as re-frame]
            [sweaty-timer.subs :as subs]))


(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div#main-view "Sweaty Timer is Here!"]))
