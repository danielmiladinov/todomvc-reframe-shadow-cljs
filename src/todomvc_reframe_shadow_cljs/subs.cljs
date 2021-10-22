(ns todomvc-reframe-shadow-cljs.subs
  (:require [re-frame.core :as rf]
            [todomvc-reframe-shadow-cljs.queries :as queries]))

(rf/reg-sub
  ::todos
  queries/todos)

(rf/reg-sub
  ::top-level-todos
  (fn [{:keys [display-type] :as db}]
    (let [todos (queries/todos db)
          visible-todos (->> todos
                             (filter (fn [{:keys [completed?]}]
                                       (case display-type
                                         :completed completed?
                                         :active (not completed?)
                                         true))))
          todos-exist? (->> todos count pos?)]
      [visible-todos todos-exist?])))

(rf/reg-sub
  ::todos-active
  (fn [db]
    (filter (fn [{:keys [completed?]}] (not completed?)) (queries/todos db))))

(rf/reg-sub
  ::todos-all-completed?
  queries/all-completed?)

(rf/reg-sub
  ::todos-any-completed?
  queries/any-completed?)

(rf/reg-sub
  ::todos-display-type
  (fn [{:keys [display-type]}]
    display-type))

(rf/reg-sub
  ::next-todo-title
  :next-todo-title)
