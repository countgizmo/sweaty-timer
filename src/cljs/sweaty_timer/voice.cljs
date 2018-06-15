(ns sweaty-timer.voice)

(defn speak [message]
  (let [utterance (new js/SpeechSynthesisUtterance)]
    (set! (.-voice "Alex"))
    (set! (.-lang utterance) "en-US")
    (set! (.-text utterance) message)
    (js/speechSynthesis.speak utterance)))
