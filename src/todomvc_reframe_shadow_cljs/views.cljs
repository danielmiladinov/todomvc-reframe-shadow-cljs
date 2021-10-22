(ns todomvc-reframe-shadow-cljs.views
  (:require [re-frame.core :as rf]
            [todomvc-reframe-shadow-cljs.components.footer :as footer]
            [todomvc-reframe-shadow-cljs.components.title :as title]
            [todomvc-reframe-shadow-cljs.components.todo-input :as todo-input]
            [todomvc-reframe-shadow-cljs.components.todos-clear :as todos-clear]
            [todomvc-reframe-shadow-cljs.components.todos-count :as todos-count]
            [todomvc-reframe-shadow-cljs.components.todos-filters :as todos-filters]
            [todomvc-reframe-shadow-cljs.components.todos-list :as todos-list]
            [todomvc-reframe-shadow-cljs.components.todos-toggle :as todos-toggle]
            [todomvc-reframe-shadow-cljs.subs :as subs]))

(defn todo-app []
  (let [[visible-todos todos-exist?] @(rf/subscribe [::subs/top-level-todos])]
    [:div
     [:section.todoapp
      [:header.header
       [title/component]
       [todo-input/component]]
      [:div {:style {:display (if todos-exist? "inline" "none")}}
       [:section.main
        [todos-toggle/component]
        [todos-list/component visible-todos]]
       [:footer.footer
        [todos-count/component]
        [todos-filters/component]
        [todos-clear/component]]]]
     [footer/component]]))
