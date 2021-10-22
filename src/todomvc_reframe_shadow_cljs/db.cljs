(ns todomvc-reframe-shadow-cljs.db)

(def default-db
  {:todos           {1 {:id 1 :title "Deepen the Fiber" :completed? false}
                     2 {:id 2 :title "Name the Nodes" :completed false}
                     3 {:id 3 :title "Swap the Housings" :completed? false}
                     4 {:id 4 :title "Plan the Augments" :completed? false}
                     5 {:id 5 :title "Learn Clojure" :completed? false}}
   :next-todo-title ""
   :display-type    :all})
