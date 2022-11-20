(ns untitled.Task-3)

(defn heavy-check [entry]
  (Thread/sleep 1000)
  (not= 0 (mod entry 2)))

(defn inner-filter [func block]
  (future (doall (filter func block))))

(defn p-filter [func seq]
  (let [block-size 2]
  (apply concat (map deref (pmap #(inner-filter func %) (partition-all block-size seq)))))
  )

(time (println (->> (iterate inc 1)
                  (p-filter heavy-check)
                  (take 5)
                  )
             )
      )

;(time (println (->> (iterate inc 1)
;                  (filter heavy-check)
;                  (take 5)
;                  )
;             )
;      )

;(shutdown-agents)