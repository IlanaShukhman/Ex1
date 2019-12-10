# Ex1

The main purpose of this project is to make complicated functions. There are classes, interfaces and Enums.

## Intefaces: 

1) complex_function. This interface represents a complex function of type y=g(f1(x), f2(x)), where both f1, f2 are functions (or complex functions), 
   y and x are real numbers and g is an operation: plus, mul, div, max, min, comp (f1(f2(x))).   
2) cont_function: The interface represents a continuance function.
3) function: This interface represents a simple function of type y=f(x), where both y and x are real numbers.
4) functions: This interface represents a collection of mathematical functions which can be presented on a GUI window and can be saved
   (and load) to file. Extands Collection. 
5) Polynom_able: This interface represents a general Polynom: f(x) = a_1X^b_1 + a_2*X^b_2 ... a_n*Xb_n,
   where: a_1, a_2 ... a_n are real numbers and b_1<b_2..<b_n are none negative integers (naturals).
   For formal definitions see: https://en.wikipedia.org/wiki/Polynomial

## Enum:
1) Operation: This enum presents the set of operations applicable on function to compose a complex function from two functions.
   E.g.,   Plus: plus(f1(x), f2(x)),  Times: mul(f1(x), f2(x)), Divid: div(f1(x), f2(x)), Max: max(f1(x), f2(x)), Min: min(f1(x), f2(x)),
   Comp: comp(f1(x), f2(x)) == f1(f2(x))
   None representing no Operation and Error representing an unknown (aka unsupported) Operation.

## Classes:
1) ComplexFunction: implements the interface complex_function. The class variables are function left and function right, and an Operation op.
It supports the following methods:

  1.1) Constructors. Can receive : nothing, which returns a ComplexFunction that left and right are null and op is none; 
                                   operation op, function left and function right;
                                   ComplexFunction cf;
                                   function f;
                                   String s. 
                                   
  1.2) ComplexFunction findLeftFunction: This method gets a string s in the form of: op(f(x),g(x)), where f and g are complexFunction, returns f(x)
  
  1.3) ComplexFunction findRightFunction: This method gets a string s in the form of: op(f(x),g(x)), where f and g are complexFunction, returns g(x)
  
  1.4) function initFromString: This method returns a function from a given string.
  
  1.5) Getters and setters.
  
  1.6) function Copy: This method returns a deep copy of this function.
  
  1.7) double f(x): This method calculates the value of this function f in x.
  
  1.8) void plus, mul, div, max, min, comp: These methods sets the root of this function as the operator we chose, this function becomes 
	     the left function and a given function becomes the right function. 
       
  1.9) boolean equals: Returns true if a given function is equal to this ComplexFunction. Equals: f1 and f2 will be called equals if for every x, f1(x)=f2(x).
       Returns false otherwise.
       
  1.10) String toString.
  
2) Functions_GUI: implements functions. The class variable is ArrayList<function> func_collection. It supports the following methods:
 
  2.1) Constructors. Can receive: nothing, which returns an empty ArrayList.
                                  ArrayList<function> func_collection.
                                  
  2.2) Getters.
  
  2.3) All the methods in the interface Collection: size, isEmpty, contains, iterator, toArray, add, remove, containsAll, addAll, removeAll, retainAll, clear.
  
  2.4) boolean equals: Returns true if a given object is a Functions_GUI, is the same size as this Functions_GUI,
       and this contains all the functions in the given Functions_GUI.
       
  2.5) String toString
  
  2.6) initFromFile: Init a new collection of functions from a file.
  
  2.7) saveToFile: Save a collection of function to a file.
  
  2.8) drawFunctions(int width, int height, Range rx, Range ry, int resolution): Draws all the functions in the collection in a GUI window using the
	     given parameters for the GUI windo and the range & resolution.
       
  2.9) drawFunction(String json file): Draws all the functions in the collection in a GUI window using the given JSON file.

3) Monom: implements function. This class represents a simple "Monom" of shape ax^b, where a is a real number and b is an integer 
   (summed a none negative), 
   see: https://en.wikipedia.org/wiki/Monomial 
   The class support simple operations as: construction, value at x, derivative, add and multiply. 

4) Polynom: implements Polynom_able. This class represents a Polynom with add, multiply functionality, it also should support the following:

   4.1) Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
   
   4.2) Finding a numerical value between two values (currently support root only f(x)=0).
   
   4.3) Derivative

In this project, we also have Ex1Test, which contains the tests we did for these four classes.
  
