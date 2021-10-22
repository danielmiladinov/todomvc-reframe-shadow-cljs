(ns todomvc-reframe-shadow-cljs.events
  (:require [clojure.string :as str]
            [day8.re-frame.tracing :refer-macros [fn-traced]]
            [re-frame.core :as rf]
            [todomvc-reframe-shadow-cljs.db :as db]
            [todomvc-reframe-shadow-cljs.queries :as queries]))

(rf/reg-event-db
  ::initialize-db
  (fn-traced [_ _] db/default-db))

(rf/reg-event-db
  ::add-next-todo
  (fn [{:keys [next-todo-title] :as db} _]
    (let [next-todo-id (queries/next-todo-id db)]
      (-> db
          (assoc :next-todo-title "")
          (update :todos assoc next-todo-id {:id         next-todo-id
                                             :title      next-todo-title
                                             :completed? false})))))

(rf/reg-event-db
  ::set-next-todo-title
  (fn [db [_ next-title]]
    (assoc db :next-todo-title next-title)))

(rf/reg-event-db
  ::save-todo
  (fn [db [_ id title editing?]]
    (reset! editing? false)
    (let [trimmed-title (str/trim @title)]
      (if-not (empty? trimmed-title)
        (update db :todos assoc-in [id :title] trimmed-title)
        (update db :todos dissoc id)))))

(rf/reg-event-db
  ::toggle-todo
  (fn [db [_ id]]
    (update-in db [:todos id :completed?] not)))

(rf/reg-event-db
  ::toggle-all-todos
  (fn [db _]
    (let [all-completed? (queries/all-completed? db)]
      (update db :todos (fn [todos-by-id]
                          (into {}
                                (for [[id todo] todos-by-id]
                                  [id (assoc todo :completed? (not all-completed?))])))))))

(rf/reg-event-db
  ::delete-todo
  (fn [db [_ id]]
    (update db :todos dissoc id)))

(rf/reg-event-db
  ::clear-completed-todos
  (fn [db _]
    (update db :todos (fn [todos-by-id]
                        (->> todos-by-id
                             (remove (comp :completed? second))
                             (into {}))))))

(rf/reg-event-db
  ::set-display-type
  (fn [db [_ display-type]]
    (assoc db :display-type display-type)))
