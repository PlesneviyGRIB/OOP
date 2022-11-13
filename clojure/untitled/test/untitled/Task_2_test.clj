(ns untitled.Task_2-test
  (:use untitled.Task_2)
  (:require [clojure.test :refer [deftest testing is]])
  )

(deftest getPrimes-test
  (testing "1: FIXME, I fail."
    (is (= (->> (getPrimes {} 3)
                (concat [2])
                (take 3)
                ) (list 2 3 5))
        )
    )
  (testing "2: FIXME, I fail."
    (is (= (->> (getPrimes {} 3)
                (concat [2])
                (take 4)
                ) (list 2 3 5 7))
        )
    )
  (testing "3: FIXME, I fail."
    (is (= (->> (getPrimes {} 3)
                (concat [2])
                (take 6)
                ) (list 2 3 5 7 11 13))
        )
    )
  )


(deftest tryGet-test
  (testing "4: FIXME, I fail."
    (is (= (tryGet {} 3) {9 6})
        )
    )
  (testing "5: FIXME, I fail."
    (is (= (tryGet {} 8) {24 16})
        )
    )
  (testing "6: FIXME, I fail."
    (is (= (tryGet {} 30) {90 60})
        )
    )
  )

(deftest add-test
  (testing "7: FIXME, I fail."
    (is (= (add {} 7 2) {9 2})
        )
    )
  (testing "8: FIXME, I fail."
    (is (= (add {} 5 2) {7 2})
        )
    )
  )

