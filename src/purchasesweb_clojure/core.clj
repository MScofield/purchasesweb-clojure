(ns purchasesweb-clojure.core
 (:require [clojure.string :as tostring]
           [compojure.core :as webcode]
           [ring.adapter.jetty :as servweb]
           [hiccup.core :as htmlout]
  (:gen-class)))

;;str = tostring
;;c = webcode
;;j = servweb
;;h = htmlout
;;customer_id,date,credit_card,cvv,category

(defn read-purchases []
 (let [
       purchases(slurp "purchases.csv")
       purchases(tostring/split-lines purchases)
       purchases (map (fn [line] (tostring/split line #",")) purchases)
       header (first purchases)
       purchases (rest purchases)
       purchases (map (fn [line] (zipmap header line)) purchases)]
   purchases))

(defn purchases-html [category]
  (let [purchases (read-purchases)
        purchases (if (= 0 (count category))
                    purchases
                    (filter (fn [purchase]
                              (= (get purchase "category") category)) 
                      purchases))]
    [:ol
     (map (fn [purchase]
            [:li (str " "
                   (get purchase "customer_id") " " 
                   ( get purchase "date") " " 
                   ( get purchase "category") " ")])
       purchases)])) 

(webcode/defroutes routes
  (webcode/GET "/:category{.*}" [category]
    (htmlout/html [:html
                   [:body
                    (purchases-html category)]])))
                    
    

(defonce server (atom nil))

(defn -main []
  (when @server
    (.stop @server))
  (reset! server (servweb/run-jetty routes {:port 3000 :join? false})))
  
