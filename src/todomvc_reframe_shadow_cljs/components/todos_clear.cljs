(ns todomvc-reframe-shadow-cljs.components.todos-clear
  (:require [re-frame.core :as rf]
            [todomvc-reframe-shadow-cljs.events :as events]
            [todomvc-reframe-shadow-cljs.subs :as subs]))

(defn component []
  [:button.clear-completed {:on-click #(rf/dispatch [::events/clear-completed-todos])
                            :style    {:display (if @(rf/subscribe [::subs/todos-any-completed?])
                                                  "inline"
                                                  "none")}}
   "Clear Completed"])
