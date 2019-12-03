package Ex1;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub'
		Operation o=Operation.Times;
		ComplexFunction left=new ComplexFunction("min(div(x^3,x),x)");
		ComplexFunction right=new ComplexFunction("max(mul(x^2,x),plus(x+2,2))");
		ComplexFunction ACTUAL=new ComplexFunction(o,left,right);
		double x=4;
		double actual=ACTUAL.f(x);
		ComplexFunction EXPECTED=new ComplexFunction("mul(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
		double expected=EXPECTED.f(x);
		System.out.println(actual+" expected:"+expected);
		System.out.println(ACTUAL.toString());

	}

}
