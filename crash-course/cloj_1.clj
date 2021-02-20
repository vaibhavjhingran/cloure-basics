;; Clojure Crash Course

;; basic syntax
1
"foo"
["a" "vector" "of" "foos"]

;; operations
(+ 2 3 4 5)
(str "this " "is " "con" "cat")

;; control flow
;; if syntax
(if true
  "this is true!"
  "totally false!")

(if false
  "this is true!"
  "totally false!")

(if false
  "this is true!")
;; omitting the else branch spits nil

;; to have multiple statements within an if:
(if true
  (do (println "this is true!")
      "continuing on the truthy part...")
  (do (println "this is false!")
      "continuing on the path of being false..."))

;; when 
;; similar to if and do but without else branch
(when true
  (println "Success!!!")
  "foo...")

;; nil, true, false, Truthiness, Falsiness, Equality & Boolean
(nil? 1)
(nil? nil)

(if 1
  "this is truthy."
  "this should not show.")

(if nil
  "this should not show."
  "quite rightly, this will show for the nil case.")

;; = equality operator
(= 1 1)
(= nil false)
(= nil true)
(= 1 2)

;; booleans
;; or - returns either the first truthy value or the last value.
;; and - returns the first falsey value or, if no values are falsey,
;; the last truthy value.

(or false nil :sample :large_vale_case)
(or (= 0 1) (= "yes" "no"))
(or nil)

(and :free_wifi :hot_coffee)
(and :feeling_so_cold nil false)

;; naming values with def
(def failed-names
  ["Larry Potter" "Incredible Bulk" "Bora the explorer"])
failed-names

;; binding instead of assigning in Clojure
(defn error-message
  [severity]
  (str "Oh noes! It's a disastah! "
    (if (= severity :mild)
      "not so brutal..."
      "brutal! savage! rekt!")))
(error-message :crazy!)
;; this function shows that instead of assigning values
;; we bind operators together.

;; Data Structures
;; numbers
1
3/2
1.6
;; strings
"Lord Foldermouth"
"\"He who must not be shamed\""
;; maps - similar to hashes
{}
{:first-name "Charlie"
:last-name "Mortanganger"}
{"string-key" +}
;; nested maps
{:name {:first "Pig" :last "Son" :middle "of a"}}
;; this also works
(hash-map :a 1 :b "23")
(get {:a 12 :b 13} :b) ;; fetching values
(get {:a 12 :b 13} :default "nopety nopes") ;; will return nil if no default specified.
(get-in {:name {:first "Pig" :last "Son" :middle "of a"}} [:name :last ])
;; or get value by treating map like a function
({:a 12 :b 13 :c "foo"} :c)

;; keywords
;; alternatively fetching values similar to get for maps with keyword
(:a {:a 12 :b 14})
(:d {:a 12 :b "c"} "default since the k,v pair doesn't exist")

;; vectors - similar to arrays (0-indexed collection)
[1 3 5]
(get [1 0 9] 2)
(vector ["creedence" "clearwater" "revival"])
(get ["=" {:name "pete sigley"} 23] 1)
;; to add elements to a vector use conj
(conj [1 2 3 4] 5)

;; lists - similar to vectors, linear collection of values.
'(1 2 3 4)
'("a" {:name "ice" :age "13"} 13)
(nth '(:a :b :c) 0)
(nth '(0 1 2 3 4 5) 2)
;; new elements are added to the front of the list
(conj '(1 2 3 4 5) 0)

;; sets - clojure has two kinds of sets: hashed and sorted.
#{20 "ocet" {:a "boo"}} ;; hashed set example
(hash-set 1 0 0 1 22 2 0)
;; adding to a set example
(conj #{:a :b :c} :b)
(set [1 0.2 0.4 1 10 9 10 13 1.2])
(contains? #{12, 13, 14, 15, -3} -3)
(:a #{:a :b :c :d}) ;; another example for checking set membership.
(get #{:a 0 :b} 0)
(get #{:a 0 :b} nil) ;; returns nil
(get #{:a nil} nil) ;; returns nil ; this is why we will prefer using contains?

