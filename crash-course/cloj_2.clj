;; Functions
;; a basic function call
(or + -) ;; returns the addition function
;; or
((or + -) 1 2 3 4)
((and (= 1 1) +) 1 2 3 4)
((first [+ 0]) 1 2 3 4)
;; defining a function
(defn name-concat
  "Return a string welcoming the person who adds their name."
  [name]
  (str "Hello Mr. " name ". We have been expecting you." "." "."))
(name-concat "__ice__")
;; function declaration with 2 parameters
(defn two-paramd
  [first last]
  (str "First name: " first ", Last name: " last))
(two-paramd "Foo" "Bar") ;; 2-arity function
;; multi-arity Functions || operator overloading
(defn multi-arity
  ;; 3 params and a body
  ([x y z]
    (str "Hello: " x " " y " " z))
  ;; 2 params and a body
  ([x y]
    (str "Hello only " x " & " y))
  ;; 1 param and a body
  ([x]
    (str "Hello " x)))
(multi-arity "avocado" "bohnen" "apfel")
(multi-arity "blumenkohl" "eis")
(multi-arity "bananen")

;; using default with multi-arity
(defn x-chop
  ([name chop-type]
    (str "I " chop-type " chop " name "! Take That!!!"))
  ([name]
    ;; this handles the default argument.
    (x-chop name "judo")))
(x-chop "Foo")

;; variablr-arity functions - includes a rest param to include the
;; rest of the arguements in a list.
(defn warning-message
  [vorname]
  (str "You better stay out " vorname " !!!"))

(defn warning
  [& vornames]
  (map warning-message vornames))
(warning "Lewis" "Charles" "Max" "Sebastian")

;; mixing of params and rest params
(defn doer
  [names & things]
    (str "yo! " names "!" " do you like these? " 
      (clojure.string/join ", " things)))

(doer "baloo" "skiing" "football" "skates")

;; destructuring - binds names to values within a collection
(defn my-first
  [[first-thing]]
  first-thing)
(my-first ["bake" "bike" "bake again"])

;; alternatively
(defn chooser
  [[first-choice second-choice & other-choices]]
  (println (str "First choice is: " first-choice))
  (println (str "Second choice is: " second-choice))
  (println (str "Here are others if you listed them: "
  (clojure.string/join ", " other-choices))))
(chooser ["football", "guitar playing", "formula one", "reading"])

;; destructuring a map can also be done
(defn destination-latlong
    [{lat :lat lng :lng}]
  (println (str "Destination Lat: " lat))
  (println (str "Destination Lng: " lng)))
(destination-latlong {:lat -12.399 :lng 145.324})

;; or a shorter way for the same would be
(defn origin-latlong
    [{:keys [lat lng]}]
  (println (str "Origin Lat:" lat))
  (println (str "Origin Lng: " lng)))
(origin-latlong {:lat -12.210 :lng 146.230})

;; function body - clojure returns the last evaluated form
(defn illustrate-fn-body
  []
  "123"
  -0.12
  "foo")
(illustrate-fn-body) ;; should return "foo"

;; another example
(defn illustration
 [x]
 (if (> x 5)
  "that's a biggie!"
  "puny number..."))
(illustration 13)

;; note: clojure treats all functions as equals.

;; Anonymous functions
;; looks like:
;; (fn [param-list]
;;   function-body)
(map (fn [name] (str "Hello " name "!"))
      ["foo" "bar" "treats" "zommer"])

((fn [x] (* x x)) 7) ;; anon function that squares

;; can associate anon function with a name
(def square-maker (fn [x] (* x x)))
(square-maker 3)
;; a more compact way for anon function
#(* % 3)
(#(* % 3) 5) 
(#(* % %) 7) ;; anon square maker function 
(map #(str "yo! " %)
    ["foo" "bar" "joo"])
;; or past rest params as such:
(#(identity %&) 1 "yikes!" :foo)

;; functions can return functions. These returned functions
;; are called closures.
(defn inc-maker
  [inc-by]
  #(+ % inc-by))

(def inc3 (inc-maker 3))
(inc3 -3)

;; Let - binds names to values
(let [x 3] x)
(def pet-list
  ["patty" "chloe" "whisky" "peppa"])
(let [pets (take 3 pet-list)]
  pets)
;; or let's you introduce a new scope
(def x 0) ;; binds x to 0
(let [x -3] x) ;; binds x to -3 now
;; similar example
(def x -9)
(let [x (inc x)] x)
;; let also allows to evaluate an expression only once
;; and reuse the result.
;; it is also a good way to introduce a local names for
;; values to simplify the code.

;; into function
(into [] (set [:a :a :b :c :d :aa :bc :dd :cd]))

;; Loop - an alternate way to do recursion
(loop [iteration 0]
  (println (str "iteration: " iteration))
  (if (> iteration 4)
    (println "ciao!")
    (recur (inc iteration))))

;; Regular Expressions
(re-find #"-lll-" "-lll-asdasd-ll-asdsad") ;; will return the original string
(re-find #"-lll-" "-ll0asd-ll-") ;; will return nil

;; Reduce
(reduce * [1 2 3 4])
;; also takes an initial value. operates on the initial value with the first value of the sequence
(reduce * 7 [1 2 3 4])