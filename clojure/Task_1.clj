(defn removeListFromList [list1 list2]
  (remove #(some #{%} list1) list2))

(defn foo [word, alphabet]
  (apply list (mapv #(str word %)  (removeListFromList (apply list (clojure.string/split word #"")) alphabet))))

(defn bar [words, alphabet]
  (apply list (mapcat #(foo % alphabet) words)))

(defn perm [alphabet n]
  (reduce bar (repeat n alphabet)))

(print (perm (list "a" "b" "c" "d") 4))