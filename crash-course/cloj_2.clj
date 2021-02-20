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
  [name]
    (str "You better stay out " name " !!!"))

(defn receivers
  [& names]
    (map warning-message receivers))
(receivers "Lewis" "Charles" "Max" "Sebastian")


