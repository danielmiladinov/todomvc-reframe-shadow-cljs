(ns todomvc-reframe-shadow-cljs.core
  (:require [re-frame.core :as re-frame]
            [reagent.dom :as rd]
            [todomvc-reframe-shadow-cljs.config :as config]
            [todomvc-reframe-shadow-cljs.events :as events]
            [todomvc-reframe-shadow-cljs.views :as views]))

(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rd/unmount-component-at-node root-el)
    (rd/render [views/todo-app] root-el)))

(defn init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
