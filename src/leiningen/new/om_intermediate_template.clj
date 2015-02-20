(ns leiningen.new.om-intermediate-template
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "om-intermediate-template"))

(defn om-intermediate-template
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' om-intermediate-template project.")
    (->files data
      ["project.clj" (render "project.clj" data)]
      [".gitignore" (render "gitignore" data)]
      ["resources/data/schema.edn" (render "schema.edn" data)]
      ["resources/data/initial.edn" (render "initial.edn" data)]
      ["resources/public/html/index.html" (render "index.html" data)]
      ["resources/public/js/.gitkeep" (render "gitkeep" data)]
      ["src/clj/{{sanitized}}/core.clj" (render "server.clj" data)]
      ["src/clj/{{sanitized}}/util.clj" (render "server_util.clj" data)]
      ["src/cljs/{{sanitized}}/core.cljs" (render "client.cljs" data)])))
