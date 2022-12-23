(ns untitled.Task-3)

(defn heavy-check [entry]
  (Thread/sleep 100)
  (not= 0 (mod entry 2)))

(defn inner-filter [func block]
  (future (doall (filter func block))))

(defn p-filter-inner [func seq]
   (let [block-size 4]
     (->> (partition-all block-size seq)
          (map #(inner-filter func %))
          (doall)
          (map deref)
          (concat)
          )))

(defn p-filter [func seq]
  (flatten (apply concat (map #(p-filter-inner func %) (partition-all 200 seq)))))

(time (println (->> (iterate inc 1)
                  (p-filter heavy-check)
                  (take 10)
                  )
             )
      )

(time (println (->> (iterate inc 1)
                  (filter heavy-check)
                  (take 10)
                  )
             )
      )

(shutdown-agents)