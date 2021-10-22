(ns todomvc-reframe-shadow-cljs.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
   [todomvc-reframe-shadow-cljs.events :as events]
   [todomvc-reframe-shadow-cljs.views :as views]
   [todomvc-reframe-shadow-cljs.config :as config]
   ))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel] root-el)))

(defn init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
