package Ex1;



public class ComplexFunction implements complex_function {

	private function Left,Right;//Represent comlex function by the tree
	private Operation Root;//Represent specific operators

	/**
	 * Constructors
	 */

	public ComplexFunction() {

		Left=null;
		Right=null;
		Root=Root.None;        


	}//ComplexFunction


	public ComplexFunction(Operation op,function l,function r)
	{
		this.Left=l;
		this.Right=r;
		this.Root=op;
	}//ComplexFunction

	public ComplexFunction(String op, String left, String right) {
		set_OP(op);
		set_left(left);
		set_right(right);
	}

	public ComplexFunction(String s) {
		s=s.replace(" ", "");

		Operation op=Root.None;
		function left=new Polynom(),right=new Polynom();

		if(Polynom.isPolynom(s)) {
			op=Root.None;
			left=new Polynom(s);
			right=null;	
		}
		else if(simpleCF(s)){
			op=whichOP(s.substring(0,s.indexOf('(')));
			left = new Polynom(s.substring(s.indexOf('(')+1,s.indexOf(',')));
			right =new Polynom(s.substring(s.indexOf(',')+1,s.indexOf(')')));
		}
		else {
			int index=s.indexOf("(");
			String oper=s.substring(0,index);
			op=whichOP(oper);
			left=findLeftFunction(s);
			right=findRightFunction(s);
		}

		set_OP(op);
		set_left(left);
		set_right(right);
	}

	public static boolean simpleCF(String str) {
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
		if(count1!=1 || count2!=1 || count3!=1 )
			return false;
		return true;
	}

	public static boolean complicateCF(String str) {
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
		if(count1==1 || count2==1 || count3==1 )
			return false;
		else if(count1==0)
			return false;
		return true;
	}
	/**
	 * Given a string with the form op(left,right), returns the left function.
	 * 
	 * @param s
	 * @return
	 */
	public ComplexFunction findLeftFunction(String s) {
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
			}
		}
		return Left;
	}

	public ComplexFunction findRightFunction(String s) {
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
			}
		}
		return Right;
	}


	@Override
	public function initFromString(String s) {
		s=s.replace(" ", "");
		function func;
		Operation op=Root.None;
		function left=new Polynom(),right=new Polynom();

		if(Polynom.isPolynom(s)) {
			op=Root.None;
			left=new Polynom(s);
			right=null;	
		}
		else if(simpleCF(s)){
			op=whichOP(s.substring(0,s.indexOf('(')));
			left = new Polynom(s.substring(s.indexOf('(')+1,s.indexOf(',')));
			right =new Polynom(s.substring(s.indexOf(',')+1,s.indexOf(')')));
		}
		else {
			int index=s.indexOf("(");
			String oper=s.substring(0,index);
			op=whichOP(oper);
			left=findLeftFunction(s);
			right=findRightFunction(s);


		}
		func=new ComplexFunction(op,left,right);
		System.out.println(func);
		return func;
	}
	/**
	 * Setters. Can get: String, operation and function, empty.
	 */
	public Operation whichOP(String oper) {
		oper=oper.toLowerCase();
		if(oper.equals("plus"))
			return Root.Plus;
		else if(oper.equals("min"))
			return Root.Min;
		else if(oper.equals("max"))
			return Root.Max;
		else if(oper.equals("comp"))
			return Root.Comp;
		else if(oper.equals("mul"))
			return Root.Times;
		else if(oper.equals("div"))
			return Root.Divid;
		else
			return Root.Error;
	}


	public void set_OP(String oper)
	{
		oper=oper.toLowerCase();
		if(oper.equals("plus"))
			Root=Root.Plus;
		else if(oper.equals("min"))
			Root=Root.Min;
		else if(oper.equals("max"))
			Root=Root.Max;
		else if(oper.equals("comp"))
			Root=Root.Comp;
		else if(oper.equals("mul"))
			Root=Root.Times;
		else if(oper.equals("div"))
			Root=Root.Divid;
		else
			Root=Root.Error;
	}//operationAnalyzing

	public void set_right(String str) {
		function func=new Polynom(str);
		Right=func;
	}
	public void set_left(String str) {
		function func=new Polynom(str);
		Left=func;
	}
	public void set_OP(Operation op) {
		this.Root=op;
	}
	public void set_right(function right) {
		this.Right=right;
	}
	public void set_left(function left) {
		this.Left=left;
	}
	public void set_right() {
		this.Right=null;
	}


	@Override
	public function copy() {
		function f= new ComplexFunction();
		f.initFromString(toString());
		return f;
	}//copy

	public double f(double x) {
		if(this.Right!=null) {
			switch (Root) {
			case Comp:
			{
				return this.Left.f(this.Right.f(x));	
			}//Comp
			case Divid:
			{
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


	@Override
	public void plus(function f1) {

		Operation op=Operation.Plus;
		function left=new ComplexFunction(this.Root, this.Left, this.Right);
		this.Root=op;
		this.Left=left;
		this.Right=f1;
	}//plus




	@Override
	public void mul(function f1) {

		Operation op=Operation.Times;
		function left=new ComplexFunction(this.Root, this.Left, this.Right);
		this.Root=op;
		this.Left=left;
		this.Right=f1;

	}//mul




	@Override
	public void div(function f1) {

		Operation op=Operation.Divid;
		function left=new ComplexFunction(this.Root, this.Left, this.Right);
		this.Root=op;
		this.Left=left;
		this.Right=f1;
	}//div


	@Override
	public void max(function f1) {

		Operation op=Operation.Max;
		function left=new ComplexFunction(this.Root, this.Left, this.Right);
		this.Root=op;
		this.Left=left;
		this.Right=f1;
	}//max



	@Override
	public void min(function f1) {

		Operation op=Operation.Min;
		function left=new ComplexFunction(this.Root, this.Left, this.Right);
		this.Root=op;
		this.Left=left;
		this.Right=f1;
	}//min



	@Override
	public void comp(function f1) {

		Operation op=Operation.Comp;
		function left=new ComplexFunction(this.Root, this.Left, this.Right);
		this.Root=op;
		this.Left=left;
		this.Right=f1;
	}//comp


	@Override
	public function left() {
		return this.Left;
	}//Left




	@Override
	public function right() {
		return this.Right;
	}//Right


	@Override
	public Operation getOp() {
		return Root;
	}//getOP

	@Override
	public boolean equals(function obj) {
		// TODO Auto-generated method stub
		return false;
	}//equals


	public String toString() {
		String str="";
		str=Root +" ( " + Left +" , "+Right +" )";
		return str;
	}



}
