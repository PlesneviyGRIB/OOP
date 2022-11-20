(ns untitled.Task_1)

(defn removeLastFromList [list1 list2]
  (remove  #{apply list ( last list1)} list2))

(defn foo [word alphabet]
  (apply list (map #(str word %)  (removeLastFromList (apply list (clojure.string/split word #"")) alphabet))))

(defn bar [words alphabet]
  (apply list (mapcat #(foo % alphabet) words)))

(defn perm [alphabet n]
  (reduce bar (repeat n alphabet)))

(print (perm (list "a" "b") 3))
