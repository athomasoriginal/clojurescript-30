(ns day-calendar.event-test
  (:require
    [cljs.test           :refer-macros [deftest testing is]]
    [day-calendar.event  :refer [event find-conflicts find-conflict]]))


;; Fixtures

(def with-conflicts [(event :name "1" :start-time 1 :end-time 3)
                     (event :name "2" :start-time 2 :end-time 4)
                     (event :name "3" :start-time 1 :end-time 5)])

(def without-conflicts [(event :name "1" :start-time 1 :end-time 3)
                        (event :name "2" :start-time 4 :end-time 5)
                        (event :name "3" :start-time 6 :end-time 7)])

(def empty-conflict-array [])


;; Tests

(deftest find-conflicts-test
  (testing "events with conflicts"
    (is (= (find-conflicts with-conflicts) '([36743 36743] [36743 36743] [36743 36743]))))

  (testing "events without conflicts"
    (is (= (find-conflicts without-conflicts) '())))

  (testing "empty event vector"
    (is (= (find-conflicts empty-conflict-array) '()))))


(deftest find-conflict-test
  (testing "events should not conflict"
    (is (= (find-conflict (event :name "1" :start-time 1 :end-time 3) (event :name "1" :start-time 5 :end-time 6)) nil)))

  (testing "events should conflict"
    (is (= (find-conflict (event :name "1" :start-time 1 :end-time 3) (event :name "2" :start-time 2 :end-time 4) [36743 36743])))))
