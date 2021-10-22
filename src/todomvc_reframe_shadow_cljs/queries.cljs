(ns todomvc-reframe-shadow-cljs.queries)

(defn todos [{todos-by-id :todos}]
  (vals todos-by-id))

(defn all-completed? [db]
  (every? :completed? (todos db)))

(defn any-completed? [db]
  (some :completed? (todos db)))

(defn next-todo-id [{:keys [todos]}]
  (if (seq todos)
    (->> todos
         vals
         (map :id)
         (apply max)
         inc)
    1))
