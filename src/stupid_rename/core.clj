(ns stupid-rename.core
  (use stupid-rename.files)
  (use clojopts.core) )

(declare handleOptions)

(defn -main
  "I just pass my options to handleOptions and... let the magic happen!"
  [& argv]
  (handleOptions argv) )

(defn handleOptions
  "I handle cmdline options."
  [argv]
  (let [opts (clojopts "stupid_rename"
                argv
                (no-arg dryrun n "Do not perform actions -- dry run") )
        files (:clojopts/more opts)]
    (if (contains? opts :dryrun)
      (forFiles files printConversion)
      (forFiles files (fn [f] (println "not implemented..."))) )))

