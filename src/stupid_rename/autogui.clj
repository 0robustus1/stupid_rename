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
                      (horizontal-panel
                        :items [
                          (button :id :perform 
                                  :text "unzip and rename!" )
                          (button :id :rezip
                                  :text "rezip renamed!" )])])))

(defn setupListeners
  "I will listen to events."
  [root]
  (invoke-later
    (listen (select root [:#perform])
      :action-performed (fn [e] 
                          (let [list_widget (select root [:#zipFiles])]
                            (if-let [zip_files (selection list_widget {:multi? true})]
                              (doseq [zip_file zip_files]
                                (unzipAndRename zip_file))
                              (println "none selected.") ))))))

(defn triggerShow
  "I will make everything visible"
  [root]
  (invoke-later
    (->
      root
      pack!
      show! )))
