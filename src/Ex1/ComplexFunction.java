package Ex1;

import com.sun.javadoc.ThrowsTag;



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
	
	
	@Override
	public function initFromString(String s) {
		function f=new ComplexFunction();    
	    return f;
	}//initFromString
  
	@Override
	public function copy() {
	    return this;
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
	
	public String toString()
	{
		return this.Root.name()+"("+this.Left.toString()+","+this.Right.toString()+")";
	}//toString

	
	

}
