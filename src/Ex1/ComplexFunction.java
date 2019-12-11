package Ex1;



public class ComplexFunction implements complex_function {

	private function Left,Right;//Represent comlex function by the tree
	private Operation Root;//Represent specific operators


	/**
	 * Constructors:
	 */

	public ComplexFunction() {
		Left=null;
		Right=null;
		Root=Root.None;        
	}//ComplexFunction


	public ComplexFunction(Operation op,function l,function r)
	{
		//if we got op(f,null), and op isn't none, it is not a legal input.
		if(op!=Operation.None)
		{
			if(op==Operation.Error)
				throw new RuntimeException("ERR: Got error as an operation, not a legal Operation. ");
			else {
				this.Left=l;
				this.Right=r;
				this.Root=op;
			}//else
		}//if
		else if(r!=null)
			throw new RuntimeException("ERR: Got error as an operation, if the right function isn't null we cant do nothing eith none ");
		else//if this operation is none and right is null
		{
			this.Left=l;
			this.Right=r;
			this.Root=op;
		}//else


	}//ComplexFunction

	public ComplexFunction(String o,function l,function r)
	{
		Operation op=whichOP(o);
		if(op!=Operation.None)
		{
			if(op==Operation.Error)
				throw new RuntimeException("ERR: Got error as an operation, not a legal Operation. ");
			else {
				this.Left=l;
				this.Right=r;
				this.Root=op;
			}//else
		}//if
		else if(r!=null)
			throw new RuntimeException("ERR: Got error as an operation, if the right function isn't null we cant do nothing eith none ");
		else
		{
			this.Left=l;
			this.Right=r;
			this.Root=op;
		}//else

	}//ComplexFunction
	
	public ComplexFunction(ComplexFunction cf) {
		this(cf.Root,cf.Left,cf.Right);
	}//ComplexFunction

	public ComplexFunction(function f) {
		initFromString(f.toString());
	}//ComplexFunction

	public ComplexFunction(String s) {
		s=s.replace(" ", "");

		Operation op=Root.None;
		function left=new Polynom(),right=new Polynom();

		if(s.equals("null")) {
			set_OP(Operation.None);
			set_left(null);
			set_right(null);
		}

		else if(Polynom.isPolynom(s)) {
			op=Root.None;
			left=new Polynom(s);
			right=null;	
		}//if
		else {
			if(!checkLegalInput(s))
				throw new RuntimeException("The input we got is not a legal input!");
			int index=s.indexOf("(");
			String oper=s.substring(0,index);
			op=whichOP(oper);
			if(op==op.Error) {
				throw new RuntimeException("ERR: The operation was not legal");
			}
			left=findLeftFunction(s);
			right=findRightFunction(s);
		}//else

		//if we got op(f,null), and op isn't none, it is not a legal input.
		if(right==null && !op.equals(op.None))
			throw new RuntimeException("ERR: The operation was not none when right was null");

		set_OP(op);
		set_left(left);
		set_right(right);
	}//ComplexFunction

	public boolean checkLegalInput(String str) {
		int count1=0;
		int count2=0;
		int count3=0;
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i)=='(')
				count1++;
			else if(str.charAt(i)==')')
				count2++;
			else if(str.charAt(i)==',')
				count3++;
		}
		if(count1!=count2 || count2!=count3 || count3!=count1 )
			return false;
		if(str.contains("(,") || str.contains(",)"))
			return false;
		
		
		return true;
	}
	/**
	 * This method gets a string s in the form of: op(f(x),g(x)), where f and g are complexFunction,
	 * returns f(x)
	 * @param s string in the form of op(f(x),g(x))
	 * @return f(x)
	 */
	private ComplexFunction findLeftFunction(String s) {
		ComplexFunction Left=new ComplexFunction();

		int index=s.indexOf('(');
		s=s.substring(index+1,s.length()-1);
		int counter = 0;
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)=='(')
				counter++;
			else if(s.charAt(i)==')')
				counter--;
			if(counter==0 && s.charAt(i+1)==',') {
				s=s.substring(0,i+1);
				Left=new ComplexFunction(s);
				break;
			}
		}
		return Left;
	}

	/**
	 * This method gets a string s in the form of: op(f(x),g(x)), where f and g are complexFunction,
	 * returns g(x)
	 * @param s string in the form of op(f(x),g(x))
	 * @return g(x)
	 */
	private ComplexFunction findRightFunction(String s) {
		ComplexFunction Right=new ComplexFunction();

		int index=s.indexOf('(');
		s=s.substring(index+1,s.length()-1);
		int counter = 0;
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)=='(')
				counter++;
			else if(s.charAt(i)==')')
				counter--;
			if(counter==0 && s.charAt(i+1)==',') {
				s=s.substring(i+2);
				Right=new ComplexFunction(s);
				break;
			}
		}
		return Right;
	}


	/**
	 * This method returns a function from a given string.
	 * @param s
	 * @return function f
	 */
	public function initFromString(String s) {
		function f=new ComplexFunction(s);
		return f;
	}//initFromString

	/**
	 * This method receives a string oper, and returns the operator the string is equals to.
	 * @param oper = a string in the form of one of the operators.
	 * @return the appropriate operator.
	 */
	private Operation whichOP(String oper) {
		oper=oper.toLowerCase();
		if(oper.equals("plus"))
			return Root.Plus;
		else if(oper.equals("min"))
			return Root.Min;
		else if(oper.equals("max"))
			return Root.Max;
		else if(oper.equals("comp"))
			return Root.Comp;
		else if(oper.equals("mul") || oper.equals("times"))
			return Root.Times;
		else if(oper.equals("div") || oper.equals("divid"))
			return Root.Divid;
		else if(oper.equals("none"))
			return Root.None;
		else
			return Root.Error;
	}//WhichOP

	/**
	 * Setters.
	 */
	private void set_OP(Operation op) {
		this.Root=op;
	}
	private void set_right(function right) {
		this.Right=right;
	}
	private void set_left(function left) {
		this.Left=left;
	}


	/**
	 * This method returns a deep copy of this function
	 */
	public function copy() {
		function f= new ComplexFunction();
		f=f.initFromString(toString());
		return f;
	}//copy

	/**
	 * This method calculates the value of this function f in x.
	 * @param x
	 * @return f(x)
	 */
	public double f(double x) {
		if(this.Right!=null) {
			switch (Root) {
			case Comp:
			{
				return this.Left.f(this.Right.f(x));
			}//Comp
			case Divid:
			{
				if (this.Right.f(x)==0) {
					throw new RuntimeException("Not possible to divide by 0!");
				}
				return this.Left.f(x)/this.Right.f(x);
			}//Divid
			case Max:
			{
				return Math.max(this.Left.f(x), this.Right.f(x));
			}//Max
			case Plus:
			{
				return (this.Left.f(x)+ this.Right.f(x));
			}//Plus
			case Min:
			{
				return Math.min(this.Left.f(x), this.Right.f(x));
			}//Min
			case None:
			{
				return this.Left.f(x);
			}//None
			case Times:
			{
				return this.Left.f(x)* this.Right.f(x);
			}//Times
			case Error:
			{
				throw new RuntimeException("ERR: wrong operation"); 
			}//Error
			}//switch
		}//if
		return this.Left.f(x);
	}//f


	/**
	 * This method sets the root of this function as the operator plus, this function becomes 
	 * the left function and a given function becomes the right function. 
	 * @param f1 is the given function, to become the right function of this.
	 */
	public void plus(function f1) {
		function f=new ComplexFunction(f1.toString());
		Operation op=Operation.Plus;
		function left=new ComplexFunction(this.Root, this.Left, this.Right);
		this.Root=op;
		this.Left=left;
		if(this==f1)
		{
			this.Right=f;
		}//if
		else
		{
			this.Right=f1;
		}//else
	}//plus




	/**
	 * This method sets the root of this function as the operator times, this function becomes 
	 * the left function and a given function becomes the right function. 
	 * @param f1 is the given function, to become the right function of this.
	 */
	public void mul(function f1) {
		function f=new ComplexFunction(f1.toString());
		Operation op=Operation.Times;
		function left=new ComplexFunction(this.Root, this.Left, this.Right);
		this.Root=op;
		this.Left=left;
		if(this==f1)
		{
			this.Right=f;
		}//if
		else
		{
			this.Right=f1;
		}//else
	}//mul




	/**
	 * This method sets the root of this function as the operator divid, this function becomes 
	 * the left function and a given function becomes the right function. 
	 * @param f1 is the given function, to become the right function of this.
	 */
	public void div(function f1) {
		function f=new ComplexFunction(f1.toString());
		Operation op=Operation.Divid;
		function left=new ComplexFunction(this.Root, this.Left, this.Right);
		this.Root=op;
		this.Left=left;
		if(this==f1)
		{
			this.Right=f;
		}//if
		else
		{
			this.Right=f1;
		}//else
	}//div



	/**
	 * This method sets the root of this function as the operator max, this function becomes 
	 * the left function and a given function becomes the right function. 
	 * @param f1 is the given function, to become the right function of this.
	 */
	public void max(function f1) {
		function f=new ComplexFunction(f1.copy());
		Operation op=Operation.Max;
		function left=new ComplexFunction(this.Root, this.Left, this.Right);
		this.Root=op;
		this.Left=left;
		if(this==f1)
		{
			this.Right=f;
		}//if
		else
		{
			this.Right=f1;
		}//else
	}//max



	/**
	 * This method sets the root of this function as the operator min, this function becomes 
	 * the left function and a given function becomes the right function. 
	 * @param f1 is the given function, to become the right function of this.
	 */
	public void min(function f1) {
		function f=new ComplexFunction(f1.toString());
		Operation op=Operation.Min;
		function left=new ComplexFunction(this.Root, this.Left, this.Right);
		this.Root=op;
		this.Left=left;
		if(this==f1)
		{
			this.Right=f;
		}//if
		else
		{
			this.Right=f1;
		}//else
	}//min



	/**
	 * This method sets the root of this function as the operator comp, this function becomes 
	 * the left function and a given function becomes the right function. 
	 * @param f1 is the given function, to become the right function of this.
	 */
	public void comp(function f1) {
		function f=new ComplexFunction(f1.toString());
		Operation op=Operation.Comp;
		function left=new ComplexFunction(this.Root, this.Left, this.Right);
		this.Root=op;
		this.Left=left;
		if(this==f1)
		{
			this.Right=f;
		}//if
		else
		{
			this.Right=f1;
		}//else
	}//comp


	/**
	 * @return the right function of this ComplexFunction
	 */
	public function left() {
		return this.Left;
	}//Left

	/**
	 * @return the right function of this ComplexFunction
	 */
	public function right() {
		return this.Right;
	}//Right


	/**
	 * @return the operator of this complexFunction
	 */
	public Operation getOp() {
		return Root;
	}//getOP

	/**
	 * f1 and f2 will be called equals if for every x, f1(x)=f2(x).
	 * @param obj is a given object. 
	 * @return true if obj is a ComplexFunction and equals to this. Return false otherwise.
	 */
	public boolean equals(Object obj) {
		if(obj instanceof function)
		{
			if((obj instanceof Polynom || obj instanceof Monom))
			{
				if(!isEmpty() && !checkByInterval((function) obj))
					return false;
				if(Right==null)
				{
					if(Left instanceof Polynom || Left instanceof Monom)
					{
						return Left.equals(obj);
					}//if
					else if(Left instanceof ComplexFunction)//Left is ComplexFunction and Obj is Polynom or Monom
					{
						if(((ComplexFunction) Left).getOp()==Operation.None)
							return Left.equals(obj);	
					}//else
				}//if
				else {
					if(!isEmpty() && checkByInterval((function) obj))
						return true;
					System.out.println("ERR: can't compare between complex function to Polynoms or Monoms");
				}//else
				return true;
			}//if
			else if(obj instanceof ComplexFunction)
			{

				if(((ComplexFunction) obj).isEmpty() && isEmpty())
					return true;
				if(obj.toString().equals(toString()))
					return true;
				if(ComotativeCase((ComplexFunction) obj))
					return true;
				if(!isEmpty() && !((ComplexFunction) obj).isEmpty())
					return checkByInterval((function) obj);
			}//else if
			else
				throw new RuntimeException("ERR: trying to mix meat and milk those objects are different");
			return false;
		}//if
		return false;
	}//equals


	/**
	 * This method helps to decide if two complexFunctions are equals or not.
	 * @param obj is a given function.
	 * @return true if for every x checked, this.f(x) equals obj.f(x).
	 */
	private boolean checkByInterval(function obj)
	{
		Integer min=80;
		Integer max=100;
		for (int i = min; i < max; i++) {
			if(f(i)!=obj.f(i))
				return false;
		}
		return true;
	}//checkByInterval

	/**
	 * Returns this complexFunction as a string.
	 */
	public String toString() {
		String str="";
		str=Root +" ( " + Left +" , "+ Right +" )";
		str=str.replace("None ( " , "");
		str=str.replace(", null )", "");
		return str;
	}
	/**
	 * This method helps to decide if two complexFunctions are equals or not.
	 * @param r is a given ComplexFunction in the form of op(f,g).
	 * @return true if this is op(g,f), and op is either plus, max, min, mul or none.
	 */
	private boolean ComotativeCase(ComplexFunction r)
	{
		if(getOp()==r.getOp() && (r.getOp()==Operation.Plus || r.getOp()==Operation.Times || r.getOp()==Operation.Max || r.getOp()==Operation.Min || r.getOp()==Operation.None))
		{
			if((left().equals(r.Left) && right().equals(r.Right)) || (left().equals(r.Right) && left().equals(r.Right)))
				return true;
			return false;
		}//if
		else
			return false;
	}//ComotativeCase

	/**
	 * @return true if this is an empty ComplexFunction
	 */
	private boolean isEmpty()
	{
		if(getOp()==Operation.None && this.Left==null && this.Right==null)
			return true;
		return false;
	}//idEmpty

}
