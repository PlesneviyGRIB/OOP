(ns untitled.Task-5)

(defn forks [cnt]
  (map (fn [num] [(ref 0) (atom 0)]) (range cnt)))

(defn listOfForksPairs [forks]
  (conj
    (map (fn [num] [(nth forks num) (nth forks (inc num))]) (range (dec (count forks))))
    [(nth forks (dec (count forks))) (nth forks 0)]
    )
  )

(defn philosopherLife [pair thinking havingLunch repeats]
  (fn []
    (dotimes [n repeats]
            (Thread/sleep thinking)
            (dosync
              (swap! (nth (nth pair 0) 1) inc)
              (swap! (nth (nth pair 1) 1) inc)
              (alter (nth (nth pair 0) 0) inc)
              (alter (nth (nth pair 1) 0) inc)
              (Thread/sleep havingLunch))))
  )

(defn philosopherThread [forksPairs thinking havingLunch repeats]
  (doall (map (fn [pair] (new Thread (philosopherLife pair thinking havingLunch repeats))) forksPairs)))

(defn waitForThreads [threads]
  (doall (map #(.start %) threads))
  (doall (map #(.join %) threads))
  )

(defn report [currentForkNum cntOfTransactions cntOfTries]
  (print "current fork:" currentForkNum)
  (print "     count of transactions:" cntOfTransactions)
  (print "     count of tries:" cntOfTries)
  (println)
  )

(defn start [forksCnt thinking havingLunch repeats]
  (let [forks (forks forksCnt)]
        (waitForThreads (philosopherThread (listOfForksPairs forks) thinking havingLunch repeats))
        (dotimes [n forksCnt]
          (report n @(nth (nth forks n) 0) @(nth (nth forks n) 1)))
        )
  )

(start 10 50 200 3)