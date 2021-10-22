(ns todomvc-reframe-shadow-cljs.components.todo-item
  (:require [re-frame.core :as rf]
            [reagent.core :as r]
            [todomvc-reframe-shadow-cljs.components.todo-edit :as todo-edit]
            [todomvc-reframe-shadow-cljs.events :as events]))

(defn todo-item-class [completed editing]
  (str (when completed "completed ")
       (when @editing "editing")))

(defn todo-checkbox [id completed?]
  [:input.toggle {:type      "checkbox"
                  :checked   completed?
                  :on-change #(rf/dispatch [::events/toggle-todo id])}])

(defn component [_]
  (let [editing (r/atom false)]
    (fn [{:keys [id title completed?] :as todo}]
      [:li {:class (todo-item-class completed? editing)}
       [:div.view
        [todo-checkbox id completed?]
        [:label {:on-double-click #(reset! editing true)} title]
        [:button.destroy {:on-click #(rf/dispatch [::events/delete-todo id])}]]
       [todo-edit/component todo editing]])))

