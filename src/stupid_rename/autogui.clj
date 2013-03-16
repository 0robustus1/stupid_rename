(ns stupid-rename.autogui
  (use seesaw.core)
  (use stupid-rename.zip)
  (:gen-class) )

(declare setupMainFrame
         setupListeners
         triggerShow )

(defn startAutoGUI
  "I start the automatic file-finder GUI."
  []
  (let [main_frame (setupMainFrame)]
    (setupListeners main_frame)
    (triggerShow main_frame) ))

(defn setupMainFrame
  "I will create the main frame."
  []
  (frame
    :title "Stup.ID rename"
    :on-close :exit
    :content (vertical-panel
               :items[
                      (scrollable (listbox :id :zipFiles
                                           :model (listZipFiles) ))
                      (button :id :perform 
                              :text "unzip and rename!" )])))

(defn setupListeners
  "I will listen to events."
  [root]
  (invoke-later
    (listen (select root [:#perform])
      :action-performed (fn [e] (println "button clicked!")) )))

(defn triggerShow
  "I will make everything visible"
  [root]
  (invoke-later
    (->
      root
      pack!
      show! )))
