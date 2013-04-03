(ns stupid-rename.files
  (use [me.raynes.fs :only (file? directory?
                            base-name parent absolute-path
                            rename
                            list-dir )])
  (require [clojure.string :as sfn]) )

(declare shorten)

; Matches stupid numbering convention used by Stud.IP when
; bulking files in .zip-archives.
(def stupidRegex #"^\[\d+\]_")

(defn forFiles
  "I will do something for every file provided."
  [files function]
  (doseq [filename files]
    (let [file (absolute-path filename)]
      (if (file? file)
        (function file)
        (if (directory? file)
          (doseq [child_filename (list-dir file)]
            (let [child_file (absolute-path (str file "/" child_filename))]
              (function (absolute-path child_file)) )))))))

(defn printConversion
  "I will print the old filename alongside the new one..."
  [filename]
  (let [old_f filename
        new_f (shorten filename)]
    (println old_f
             "-->"
             new_f )))

(defn performConversion
  "I will rename a given file by shortening the filename."
  [filename]
  (rename 
    filename
    (shorten filename) ))

(defn performConversionForFolder
  "I will perform the conversion on
  each file in the folder (first-level)"
  [folderpath]
  (forFiles [folderpath] performConversion) )

(defn shorten
  "I try to short a given filename."
  [filename]
  (let [actualFileName (base-name filename)
        dirName (parent filename)
        modifiedFileName (sfn/replace actualFileName stupidRegex "") ]
    (str dirName "/" modifiedFileName) ))
