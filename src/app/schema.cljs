
(ns app.schema )

(def config {:storage "copycat"})

(def snippet {:id nil, :title "", :content nil, :created-at nil, :updated-at nil})

(def store {:states {}, :snippets {}, :router nil})
