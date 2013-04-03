(ns stupid-rename.zip
  (require [me.raynes.fs :as fs])
  (require [clojure.java.io :as io])
  (require [me.raynes.fs.compression :as fsc])
  (import (org.apache.commons.compress.archivers.zip ZipFile ZipArchiveEntry))
  (:gen-class) )

(def zipPattern #".*\.zip$")

(def downloadsDir (fs/expand-home "~/Downloads/"))

(defn listZipFiles
  "I will return all zip files in the Downloads-Folder."
  []
  (map #(str downloadsDir "/" %)
    (filter #(re-matches zipPattern %) (fs/list-dir downloadsDir)) ))

(defn unzipTo
  "I will unzip a zip-file into a folder named after that file." 
  [zip_filepath
   unzip_to]
  (let [file (ZipFile. zip_filepath)
        entries (enumeration-seq (.getEntries file)) ]
    (doseq [entry entries
            :when (not (.isDirectory ^ZipArchiveEntry entry))
            :let [new_file_fn #(fs/file unzip_to (str %))
                  new_filepath (new_file_fn entry)
                  stream (.getInputStream file entry) ]]
      (fs/mkdirs (fs/parent new_filepath))
      (io/copy stream new_filepath) )))

(defn unzipToSelf
  "I will unzip a zip-file into a folder named after that file." 
  [zip_filepath]
  (unzipTo zip_filepath (fs/parent zip_filepath)) )

(defn extractFolderPath
  "I will extract the folderpath from a file"
  [filepath]
  (fs/parent filepath) )

(defn unzipAndRename
  "I will unzip a zipfile and rename all files."
  [zip_filepath]
  (unzipToSelf zip_filepath) )

(defn unzipAndRenameTo
  "I will unzip a zipfile and rename all files."
  [zip_filepath
   folderpath]
  (unzipTo zip_filepath folderpath) )
