(ns todomvc-reframe-shadow-cljs.components.todo-edit
  (:require [todomvc-reframe-shadow-cljs.events :as events]
            [reagent.core :as r]
            [reagent.dom :as rd]
            [re-frame.core :as rf]))

(def enter-key 13)

(def escape-key 27)

(defn on-key-down [k id title default editing]
  (let [key-pressed (.-which k)]
    (condp = key-pressed
      enter-key (rf/dispatch [::events/save-todo id title editing])
      escape-key (do (reset! title default)
                     (reset! editing false))
      nil)))

(defn component-render [{:keys [id title]} editing]
  (let [default    title
        edit-title (r/atom default)]
    (fn []
      [:input.edit {:type        "text"
                    :style       {:display (if @editing "inline" "none")}
                    :value       @edit-title
                    :on-change   #(reset! edit-title (-> % .-target .-value))
                    :on-blur     #(rf/dispatch [::events/save-todo id edit-title editing])
                    :on-key-down #(on-key-down % id edit-title default editing)}])))

(defn component-did-update [x]
  (.focus (rd/dom-node x)))

(defn component []
  (r/create-class {:reagent-render       component-render
                   :component-did-update component-did-update}))
