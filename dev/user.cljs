(ns cljs.user
  "Commonly used symbols for easy access in the ClojureScript REPL during
  development."
  (:require
    [cljs.repl :refer (Error->map apropos dir doc error->str ex-str ex-triage
                                  find-doc print-doc pst source)]
    [clojure.pprint :refer (pprint)]
    [clojure.string :as str]
    [re-frame.core :as rf]
    [todomvc-reframe-shadow-cljs.events :as events]))

(defn add-todo [title]
  (rf/dispatch [::events/set-next-todo-title title])
  (rf/dispatch [::events/add-next-todo]))
