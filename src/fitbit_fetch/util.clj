(ns fitbit-fetch.util)


(defn rand-str [len]
  ;; create a random string
  (apply str (take len (repeatedly #(char (+ (rand 26) 65))))))
