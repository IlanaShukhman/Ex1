package Ex1;


public class ComplexFunction implements complex_function {
	//private Binary_Tree CF;
	private Operation OP;
	private function left,right;

	public double f(double x) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * Constructors
	 */
	public ComplexFunction() {
		//CF=new Binary_Tree();
		OP=OP.None;
		left=null;
		right=null;
	}//ComplexFunction

	public ComplexFunction(Operation op,function l,function r) {
		set_OP(op);
		set_left(l);
		set_right(r);
	}//ComplexFunction

	public ComplexFunction(String op, String left, String right) {
		set_OP(op);
		set_left(left);
		set_right(right);
	}

	public ComplexFunction(String s) {
		s=s.replace(" ", "");
		
		Operation op=OP.None;
		function left=new Polynom(),right=new Polynom();

		if(Polynom.isPolynom(s)) {
			op=OP.None;
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
		Operation op=OP.None;
		function left=new Polynom(),right=new Polynom();

		if(Polynom.isPolynom(s)) {
			op=OP.None;
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
			return OP.Plus;
		else if(oper.equals("min"))
			return OP.Min;
		else if(oper.equals("max"))
			return OP.Max;
		else if(oper.equals("comp"))
			return OP.Comp;
		else if(oper.equals("mul"))
			return OP.Times;
		else if(oper.equals("div"))
			return OP.Divid;
		else
			return OP.Error;
	}
	
	
	public void set_OP(String oper)
	{
		oper=oper.toLowerCase();
		if(oper.equals("plus"))
			OP=OP.Plus;
		else if(oper.equals("min"))
			OP=OP.Min;
		else if(oper.equals("max"))
			OP=OP.Max;
		else if(oper.equals("comp"))
			OP=OP.Comp;
		else if(oper.equals("mul"))
			OP=OP.Times;
		else if(oper.equals("div"))
			OP=OP.Divid;
		else
			OP=OP.Error;
	}//operationAnalyzing

	public void set_right(String str) {
		function func=new Polynom(str);
		right=func;
	}
	public void set_left(String str) {
		function func=new Polynom(str);
		left=func;
	}
	public void set_OP(Operation op) {
		this.OP=op;
	}
	public void set_right(function right) {
		this.right=right;
	}
	public void set_left(function left) {
		this.left=left;
	}
	public void set_right() {
		this.right=null;
	}
	
	@Override
	public function copy() {
		function f1=new ComplexFunction(this.toString());	
		return f1;
	}

	@Override
	public void plus(function f1) {
		OP=OP.Plus;
		left=this;
		right=f1;

	}

	@Override
	public void mul(function f1) {
		OP=OP.Times;
		left=this;
		right=f1;

	}

	@Override
	public void div(function f1) {
		OP=OP.Divid;
		left=this;
		right=f1;


	}

	@Override
	public void max(function f1) {
		OP=OP.Max;
		left=this;
		right=f1;


	}

	@Override
	public void min(function f1) {
		OP=OP.Min;
		left=this;
		right=f1;


	}

	@Override
	public void comp(function f1) {
		OP=OP.Comp;
		left=this;
		right=f1;


	}
	
	public String toString() {
		String str="";
		str=OP +" ( " + left +" , "+right +" )";
		return str;
	}

	@Override
	public function left() {
		return left;
	}

	@Override
	public function right() {
		return right;
	}

	@Override
	public Operation getOp() {
		return OP;
	}//getOP

}
