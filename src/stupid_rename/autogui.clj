(ns stupid-rename.autogui
  (use seesaw.core)
  (:gen-class) )

(defn startAutoGUI
  "I start the automatic file-finder GUI."
  []
  (invoke-later
    (->
      (frame
        :title "Stup.ID rename"
        :on-close :exit
        :content (vertical-panel
                   :items[] ))
      pack!
      show!)))
