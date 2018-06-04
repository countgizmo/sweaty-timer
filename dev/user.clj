(ns user
  (:require [figwheel-sidecar.repl :as r]
            [figwheel-sidecar.repl-api :as ra]))

(defn fig-start []
  (do (ra/start-figwheel!) (ra/cljs-repl)))

(println "Use (fig-start) to start figwheel+cljs repl")
