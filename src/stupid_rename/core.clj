(ns stupid-rename.core
  ; (:gen-class))
  (use [me.raynes.fs :only (file? directory? base-name parent absolute-path list-dir)])
  (require [clojure.string :as sfn]))

; Matches stupid numbering convention used by Stud.IP when
; bulking files in .zip-archives.
(def stupidRegex #"^\[\d+\]_")

(declare handleOptions
         shorten
         printConversion)

(defn -main
  "I just pass my options to handleOptions and... let the magic happen!"
  [& args]
  (handleOptions args))

(defn handleOptions
  "I handle cmdline options."
  [args]
  (doseq [arg args]
    (if (file? arg)
      (printConversion arg)
      (if (directory? arg)
        (do
          (doseq [file (list-dir arg)]
              (printConversion (absolute-path file)) ))
        (println "argument is not a file...") ))))

(defn printConversion
  "I will print the old filename alongside the new one..."
  [filename]
  (let [old_f filename
        new_f (shorten filename)]
    (println old_f
             "-->"
             new_f)))

(defn shorten
  "I try to short a given filename."
  [filename]
  (let [actualFileName (base-name filename)
        dirName (parent filename)
        modifiedFileName (sfn/replace actualFileName stupidRegex "")]
    (str dirName "/" modifiedFileName)))
