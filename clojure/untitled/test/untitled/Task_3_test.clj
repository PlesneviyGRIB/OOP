(ns untitled.Task_3-test
  (:use untitled.Task-3)
  (:require [clojure.test :refer :all]))


(deftest p-filter-test
  (testing "1: FIXME, I fail."
    (is (= (->> (iterate inc 1)
                (p-filter #(not= 0 (mod % 2)))
                (take 3)) (list 1 3 5))
        )
    )
  (testing "2: FIXME, I fail."
    (is (= (->> (iterate inc 1)
                (p-filter #(not= 0 (mod % 2)))
                (take 5)) (list 1 3 5 7 9))
        )
    )
  )

(deftest inner-filter-test
  (testing "3: FIXME, I fail."
    (is (= (deref (inner-filter #(not= 0 (mod % 2)) [1 2 3])) (list 1 3))
        )
    )
  (testing "4: FIXME, I fail."
    (is (= (deref (inner-filter #(not= 0 (mod % 2)) [1 2 3 5])) (list 1 3 5))
        )
    )
  )