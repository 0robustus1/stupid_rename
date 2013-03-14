(ns stupid-rename.files
  (use [me.raynes.fs :only (file? directory?
                            base-name parent absolute-path
                            list-dir )])
  (require [clojure.string :as sfn]) )

(declare shorten)

; Matches stupid numbering convention used by Stud.IP when
; bulking files in .zip-archives.
(def stupidRegex #"^\[\d+\]_")

(defn forFiles
  "I will do something for every file provided."
  [files function]
  (doseq [file files]
    (if (file? file)
      (function file)
      (if (directory? file)
        (doseq [child_file (list-dir file)]
          (function (absolute-path child_file)) )))))

(defn printConversion
  "I will print the old filename alongside the new one..."
  [filename]
  (let [old_f filename
        new_f (shorten filename)]
    (println old_f
             "-->"
             new_f )))

(defn shorten
  "I try to short a given filename."
  [filename]
  (let [actualFileName (base-name filename)
        dirName (parent filename)
        modifiedFileName (sfn/replace actualFileName stupidRegex "") ]
    (str dirName "/" modifiedFileName) ))
