(ns stupid-rename.core
  (use stupid-rename.files
       stupid-rename.autogui )
  (use clojopts.core) )

(declare handleOptions)

(defn -main
  "I just pass my options to handleOptions and... let the magic happen!"
  [& argv]
  (if (not= (count argv) 0)
    (handleOptions argv)
    (startAutoGUI) ))

(defn handleOptions
  "I handle cmdline options."
  [argv]
  (let [opts (clojopts "stupid_rename"
                argv
                (no-arg dryrun n "Do not perform actions -- dry run") )
        files (:clojopts/more opts)]
    (forFiles files (if (contains? opts :dryrun)
              printConversion
              performConversion ))))

