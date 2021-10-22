(ns todomvc-reframe-shadow-cljs.components.todos-toggle
  (:require [todomvc-reframe-shadow-cljs.events :as events]
            [todomvc-reframe-shadow-cljs.subs :as subs]
            [re-frame.core :as rf]))

(defn component []
  [:span
   [:input#toggle-all.toggle-all {:type      "checkbox"
                                  :checked   @(rf/subscribe [::subs/todos-all-completed?])
                                  :on-change #(rf/dispatch [::events/toggle-all-todos])}]
   [:label {:for "toggle-all"} "Mark all as complete"]])
