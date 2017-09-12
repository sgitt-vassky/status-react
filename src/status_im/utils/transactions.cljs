(ns status-im.utils.transactions
  (:require [status-im.utils.utils :as utils]
            [status-im.utils.types :as types]
            [status-im.utils.money :as money]))

(defn get-transaction-url [network account]
  (let [network (case network
                  "testnet" "ropsten"
                  "mainnet" "api")]
    (str "https://" network ".etherscan.io/api?module=account&action=txlist&address=0x"
         account "&startblock=0&endblock=99999999&sort=desc&apikey=YourApiKeyToken?q=json")))

(defn format-transaction [account {:keys [value timeStamp blockNumber hash from to gas gasPrice gasUsed nonce]}]
  (let [inbound?    (= (str "0x" account) to)]
    {:value (money/wei->ether value)
     ;; timestamp is in seconds, we convert it in ms
     :timestamp (str timeStamp "000")
     :symbol "ETH"
     :type (if inbound? :inbound :outbound)
     :block blockNumber
     :hash  hash
     :from from
     :to to
     :gas-limit gas
     :gas-price gasPrice
     :gas-used gasUsed
     :cost (money/fee-value gasUsed gasPrice)
     :nonce nonce
     :data "not implemented"}))

(defn format-transactions-response [response account]
  (->> response
       types/json->clj
       :result
       (reduce (fn [transactions {:keys [hash] :as transaction}]
                 (assoc transactions hash (format-transaction account transaction)))
               {})))

(defn get-transactions [network account on-success on-error]
  (let [network "mainnet"
        account "075664b56347c7148a775802e72acc133afc8bae"]
    (utils/http-get (get-transaction-url network account)
                    #(on-success (format-transactions-response % account))
                    on-error)))
