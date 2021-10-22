(ns todomvc-reframe-shadow-cljs.components.todo-input
  (:require [re-frame.core :as rf]
            [reagent.core :as r]
            [reagent.dom :as rd]
            [todomvc-reframe-shadow-cljs.events :as events]
            [todomvc-reframe-shadow-cljs.subs :as subs]))

(def enter-key 13)

;; TODO - convert to stateless, sub from db, event?
(defn on-key-down [keypress-event]
  (let [key-pressed (.-which keypress-event)]
    (condp = key-pressed
      enter-key (rf/dispatch [::events/add-next-todo])
      nil)))

(defn component-render []
  (let [next-todo-title @(rf/subscribe [::subs/next-todo-title])]
    [:input.new-todo {:type        "text"
                      :value       next-todo-title
                      :placeholder "What needs to be done?"
                      :on-change   #(rf/dispatch [::events/set-next-todo-title (-> % .-target .-value)])
                      :on-key-down on-key-down}]))

(defn component-did-mount [x]
  (.focus (rd/dom-node x)))

(defn component []
  (r/create-class {:reagent-render      component-render
                   :component-did-mount component-did-mount}))
