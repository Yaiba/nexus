(ns nexus.defregistry
  (:require [clj-kondo.hooks-api :as api]))

(defn register-keyword [node reg-fn-symbol]
  (let [[kw & args] (rest (:children node))]
    {:node (api/list-node
            (list* (api/reg-keyword! kw reg-fn-symbol)
                   args))}))

(defn register-action! [{:keys [node]}]
  (register-keyword node 'nexus.registry/register-action!))

(defn register-expansion! [{:keys [node]}]
  (register-keyword node 'nexus.registry/register-expansion!))

(defn register-effect! [{:keys [node]}]
  (register-keyword node 'nexus.registry/register-effect!))

(defn register-placeholder! [{:keys [node]}]
  (register-keyword node 'nexus.registry/register-placeholder!))


(comment

  (def code
    '(nxr/register-action! :actions/assoc-in
                           (fn [_ path v]
                             [[:effect/assoc-in path v]])))

  (register-action! {:node (api/parse-string (str code))})

  )
