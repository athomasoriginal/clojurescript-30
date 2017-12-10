(ns day-calendar.utils-test
  (:require
    [cljs.test            :refer-macros [deftest testing is]]
    [day-calendar.actions :refer [add-event]]
    [day-calendar.utils   :refer [find-conflicts]]))


;; Fixtures

(def with-conflicts [(add-event :id "36743" :name "1" :start-time 1 :end-time 3)
                     (add-event :id "36744" :name "2" :start-time 2 :end-time 4)
                     (add-event :id "36745" :name "3" :start-time 1 :end-time 5)])

(def another-with-conflicts [(add-event :id "36743" :name "1" :start-time 1 :end-time 2)
                             (add-event :id "36744" :name "2" :start-time 1 :end-time 2)])

(def same-events-should-be-ignored [(add-event :id "36744" :name "1" :start-time 1 :end-time 2)
                                    (add-event :id "36744" :name "1" :start-time 1 :end-time 2)])

(def without-conflicts [(add-event :id "36743" :name "1" :start-time 1 :end-time 3)
                        (add-event :id "36744" :name "2" :start-time 4 :end-time 5)
                        (add-event :id "36745" :name "3" :start-time 6 :end-time 7)])

(def empty-conflict-array [])


;; Tests

(deftest find-conflicts-test
  (testing "events with conflicts"
    (is (= (find-conflicts with-conflicts) '(["36743" "36745"] ["36743" "36744"] ["36745" "36744"]))))

  (testing "events with conflicts - events at the exact same time"
    (is (= (find-conflicts another-with-conflicts) '(["36743" "36744"]))))

  (testing "no conflicts - events should be ignored"
    (is (= (find-conflicts same-events-should-be-ignored) '())))

  (testing "events without conflicts"
    (is (= (find-conflicts without-conflicts) '())))

  (testing "empty event vector"
    (is (= (find-conflicts empty-conflict-array) '()))))
