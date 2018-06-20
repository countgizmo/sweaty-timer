(ns sweaty-timer.db)

(def default-db
  {:time nil
   :paused? true
   :active-panel :setup-panel
   :movements ["squats" "push-ups"]
   :current-min 100})
