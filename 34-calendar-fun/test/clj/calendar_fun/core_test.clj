(ns calendar-fun.core-test
  (:require [clojure.test :refer :all]
            [calendar-fun.core :refer :all]))


(deftest find-event-conflicts-test
  (testing "events with conflicts"
    (is (= (find-event-conflicts [[1 3] [2 4] [1 5]]) '([[1 3] [1 5]] [[1 3] [2 4]] [[1 5] [2 4]]))))

  (testing "events without conflicts"
    (is (= (find-event-conflicts [[1 3] [4 5] [6 7]]) '())))

  (testing "empty event vector"
    (is (= (find-event-conflicts []) '()))))


(deftest compare-events-test
  (testing "events should not conflict"
    (is (= (compare-events [1 3] [4 5]) nil)))
    
  (testing "events should conflict"
    (is (= (compare-events [1 3] [2 4]) [[1 3] [2 4]]))))
