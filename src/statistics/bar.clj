(ns statistics.bar
  (:require [clojure.edn :as edn]
            [incanter.core :refer [view]]
            [incanter.charts :refer [bar-chart*]]))

(def m->kv (juxt keys vals))

(defn map->chart
  [m]
  (->> m m->kv (apply bar-chart*)))

(defn usage
  []
  (println "lein bar <A MAP>")
  (System/exit 2))

(defn -main
  [& args]
  (let [[m] args]
    (if-let [m (edn/read-string m)]
      (try
        (view (map->chart m))
        (catch Throwable t
          (.printStackTrace t)
          (System/exit 1)))
      (usage))))
