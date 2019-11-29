package Ex1;


public class ComplexFunction implements complex_function {
    private Binary_Tree CF;//Represent comlex function by the tree
    private Operation OP;//Represent specific operators
    private function left,right;
	
	public double f(double x) {
		// TODO Auto-generated method stub
		return 0;
	}
    /**
     * Constructors
     */
	public ComplexFunction() {
		CF=new Binary_Tree();
        OP=OP.None;
        left=null;
        right=null;
	}//ComplexFunction
	
	public ComplexFunction(Operation op,function l,function r) {
		CF=new Binary_Tree();
        OP=OP.None;
	}//ComplexFunction
	
	@Override
	public function initFromString(String s) {
		CF=new Binary_Tree();
		function f=new ComplexFunction();
	    operationAnalyzing(s);
	    
	    
	}
    /**
     * 
     * @param s - input string format 
     * By the giving input decide which operation we use
     */
	public void operationAnalyzing(String s)
	{
		 int index=s.indexOf('(');
		    String oper=s.substring(0,index);
		    if(oper.equals(OP.Plus))
		    	OP=OP.Plus;
		    else if(oper.equals(OP.Min))
		    	OP=OP.Min;
		    else if(oper.equals(OP.Max))
		    	OP=OP.Max;
		    else if(oper.equals(OP.Comp))
		    	OP=OP.Comp;
		    else if(oper.equals(OP.Times))
		    	OP=OP.Times;
		    else if(oper.equals(OP.Divid))
		    	OP=OP.Divid;
		    else
		    	OP=OP.Error;
	}//operationAnalyzing
	@Override
	public function copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void plus(function f1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mul(function f1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void div(function f1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void max(function f1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void min(function f1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void comp(function f1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public function left() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public function right() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Operation getOp() {
		return OP;
	}//getOP

}
