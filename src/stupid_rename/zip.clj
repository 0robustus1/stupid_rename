(ns stupid-rename.zip
  (require [me.raynes.fs :as fs])
  (import (java.util.zip ZipEntry))
  (:gen-class) )

(def zipPattern #".*\.zip$")

(def downloadsDir (fs/expand-home "~/Downloads"))

(defn listZipFiles
  "I will return all zip files in the Downloads-Folder."
  []
  (filter #(re-matches zipPattern %) (fs/list-dir downloadsDir)) )
