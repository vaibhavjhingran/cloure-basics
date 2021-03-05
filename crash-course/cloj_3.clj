;; Core Function - Clojure
;; if first, rest, cons work on a data structure, it is safe to say
;; the ds implements a seq abstraction. List, vectors, maps, set.
(defn titleize
  [topic]
  (str topic " for the Brave and True"))

(map titleize ["Beagles" "Mousse"])
(map titleize '("Hamsters" "Jerseys"))
(map titleize #{"Joghurt" "Tacos"})
(map #(titleize (second %)) {:uh-huh "Bathing"})

;; Sequences or seq
(seq [ 1 2 3 ])
(seq '( 1 2 3 ))
(seq #{ 1 2 3 })
(seq { :name "foo" :occupation "bar" })

;; into - to convert seq lists back to a map
(into {} (seq { :name "foo" :occupation "bar" }))

;; Sequence Functions:
;; map
;; basic usage:
(map inc '(1 2 3))
;; map - with multiple collections
(map str ["a" "b" "c"] ["A" "B" "C"])

(def games [10 10 10 9 11])
(def goals [3 5 8 4 4])
(def assists [1 2 6 2 3])
(def names ["martial" "rashford" "fernandes" "cavani" "greenwood"])

(defn football-data
  [names games goals assists]
  {:name names
   :games games
   :goals goals
   :assists assists})

(map football-data names games goals assists)

;; can also pass it a collection of Functions
(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))
(defn stats
  [numbers]
  (map #(% numbers) [sum count avg]))
(stats [2 3 0])

;; map is also used to retrieve the value associated with a keyword from a collection of map ds.
(def player_team
  [{:name "fernandes" :team "man utd"}
   {:name "haaland" :team "dortmund"}
   {:name "mbappe" :team "psg"}])
(map :team player_team)

;; reduce
;; transform a maps value
(reduce (fn [new-map [k v]]
          (assoc new-map k (inc v)))
          {}
          {:max 30 :min 10})
;; you can also filter out keys from a map based on value
(reduce (fn [new-map [key val]]
          (if (> val 4)
            (assoc new-map key val)
            new-map))
            {}
            {:foo 4.1
             :bar 3.9})

;; take, drop, take-while & drop-while
(take 2 [1 2 3 4 5 6 7 8 9])
(take 1 [1 2 3 4 5 6 7 8 9])
(take 0 [1 2 3 4 5 6 7 8 9])

(drop 2 [1 2 3 4 5 6 7 8 9])
(drop 1 [1 2 3 4 5 6 7 8 9])
(drop 0 [1 2 3 4 5 6 7 8 9])

(def food-journal
  [{:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 1 :day 2 :human 5.1 :critter 2.0}
   {:month 2 :day 1 :human 4.9 :critter 2.1}
   {:month 2 :day 2 :human 5.0 :critter 2.5}
   {:month 3 :day 1 :human 4.2 :critter 3.3}
   {:month 3 :day 2 :human 4.0 :critter 3.8}
   {:month 4 :day 1 :human 3.7 :critter 3.9}
   {:month 4 :day 2 :human 3.7 :critter 3.6}])

(take-while #(< (:month %) 3) food-journal)
(drop-while #(< (:month %) 3) food-journal)
;; or use them together
(take-while #(< (:month %) 4)
              (drop-while #(< (:month %) 2) food-journal))

;; filter - return all elements of a seq that test true to the predicate Function
(filter #(< (:human %) 4) food-journal)
;; filter can work instead of take-while and drop-while but is less efficient. This is because data is sorted, filter can end up processing all the data. Take-while will only examine data we need.

;; some - helps in validating if some data does test true in a seq, returning the first truthy value.
(some #(> (:critter %) 3) food-journal) ; this will return true
(some #(and (> (:critter %) 3) %) food-journal)

;; sort & sort-by
(sort [1 2 3 0 5])
(sort-by count ["aaa" "bb" "ee" "cccc" "d"])

;; concat
(concat [1 2] [3 4 5])

;; Lazy Seqs 
;; generally maps returns a lazy seq. It's a seq whose
;; members aren't computed until you try to access them. Makes them efficient, allowing to create infinite seqs.
;; Computing members is called realizing the seq.

(def utd-players-db 
  { 0 {:name "rashford" :matches 10 :goals 5 :assits 3 :fit? true}
    1 {:name "martial" :matches 10 :goals 2 :assits 1 :fit? false}
    2 {:name "cavani" :matches 7 :goals 4 :assits 1 :fit? true}
    3 {:name "greenwood" :matches 7 :goals 2 :assits 2 :fit? true}})

(defn player-details
  [special-number]
  ;;(Thread/sleep 3000)
  (get utd-players-db special-number))

(defn utd-player?
  [record]
  (and (:fit? record)
       (not (:matches record))
       record))

(defn identify-player
  [special-numbers]
  (first (filter utd-player?
                 (map player-details special-numbers))))

;; to time function runs 
(time (player-details 0))
