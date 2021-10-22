(ns todomvc-reframe-shadow-cljs.components.todos-list
  (:require [todomvc-reframe-shadow-cljs.components.todo-item :as todo-item]))

(defn component [todos]
  [:ul.todo-list
   (for [todo todos]
     ^{:key (:id todo)}
     [todo-item/component todo])])
