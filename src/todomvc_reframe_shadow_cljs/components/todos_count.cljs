(ns todomvc-reframe-shadow-cljs.components.todos-count
  (:require [re-frame.core :as rf]
            [todomvc-reframe-shadow-cljs.subs :as subs]))

(defn items-left [active-todos]
  (let [active-count (count active-todos)]
    (str (if (= 1 active-count) " item " " items ")
         "left")))

(defn component []
  (let [active-todos @(rf/subscribe [::subs/todos-active])]
    [:span.todo-count
     [:strong (count active-todos)]
     (items-left active-todos)]))
