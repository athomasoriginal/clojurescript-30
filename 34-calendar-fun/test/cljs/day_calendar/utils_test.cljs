(ns day-calendar.utils-test
  (:require
    [cljs.test            :refer-macros [deftest testing is]]
    [day-calendar.actions :refer [add-event]]
    [day-calendar.utils   :refer [find-conflicts find-conflict]]))


;; Fixtures

(def with-conflicts [(add-event :name "1" :start-time 1 :end-time 3)
                     (add-event :name "2" :start-time 2 :end-time 4)
                     (add-event :name "3" :start-time 1 :end-time 5)])

(def another-with-conflicts [(add-event :name "1" :start-time 1 :end-time 2)
                             (add-event :name "2" :start-time 1 :end-time 2)])

(def without-conflicts [(add-event :name "1" :start-time 1 :end-time 3)
                        (add-event :name "2" :start-time 4 :end-time 5)
                        (add-event :name "3" :start-time 6 :end-time 7)])

(def empty-conflict-array [])


;; Tests

(deftest find-conflicts-test
  (testing "events with conflicts"
    (is (= (find-conflicts with-conflicts) '([36743 36743] [36743 36743] [36743 36743]))))

  (testing "events with conflicts - events at the exact same time"
    (is (= (find-conflicts another-with-conflicts) '([36743 36743]))))

  (testing "events without conflicts"
    (is (= (find-conflicts without-conflicts) '())))

  (testing "empty event vector"
    (is (= (find-conflicts empty-conflict-array) '()))))


(deftest find-conflict-test
  (testing "events should not conflict"
    (is (= (find-conflict (add-event :name "1" :start-time 1 :end-time 3) (add-event :name "1" :start-time 5 :end-time 6)) nil)))

  (testing "events should conflict"
    (is (= (find-conflict (add-event :name "1" :start-time 1 :end-time 3) (add-event :name "2" :start-time 2 :end-time 4)) [36743 36743]))))
