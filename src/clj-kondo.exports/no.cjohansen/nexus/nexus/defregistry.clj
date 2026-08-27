(ns nexus.defregistry
  (:require [clj-kondo.hooks-api :as api]))

(defn register-keyword [m reg-fn-symbol]
  (-> m
      (update-in [:node :children] vec)
      (update-in [:node :children 1]
                 #(with-meta (api/reg-keyword! % reg-fn-symbol)
                    (meta %)))))

(defn register-action! [m]
  (register-keyword m 'nexus.registry/register-action!))

(defn register-expansion! [m]
  (register-keyword m 'nexus.registry/register-expansion!))

(defn register-effect! [m]
  (register-keyword m 'nexus.registry/register-effect!))

(defn register-placeholder! [m]
  (register-keyword m 'nexus.registry/register-placeholder!))

(comment

  (def code
    '(nxr/register-action! :actions/assoc-in
                           (fn [_ path v]
                             [[:effect/assoc-in path v]])))

  (register-action! {:node (api/parse-string (str code))})

  )
