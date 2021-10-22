(ns todomvc-reframe-shadow-cljs.components.todos-filters
  (:require [todomvc-reframe-shadow-cljs.subs :as subs]
            [todomvc-reframe-shadow-cljs.events :as events]
            [re-frame.core :as rf]))

(defn selected-class [display-type todos-display-type]
  (if (= display-type todos-display-type)
    "selected"
    ""))

(defn component []
  (let [todos-display-type @(rf/subscribe [::subs/todos-display-type])]
    [:ul.filters
     [:li [:a {:class (selected-class :all todos-display-type) :on-click #(rf/dispatch [::events/set-display-type :all])} "All"]]
     [:li [:a {:class (selected-class :active todos-display-type) :on-click #(rf/dispatch [::events/set-display-type :active])} "Active"]]
     [:li [:a {:class (selected-class :completed todos-display-type) :on-click #(rf/dispatch [::events/set-display-type :completed])} "Completed"]]]))
