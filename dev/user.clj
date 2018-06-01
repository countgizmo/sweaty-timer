(ns user
  (:require [figwheel-sidecar.repl :as r]
            [figwheel-sidecar.repl-api :as ra]))

(defn fig-start []
  (do (ra/start-figwheel!) (ra/cljs-repl)))

(println "hello from the other side")
