package Ex1;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub'
Operation op= Operation.Divid;
function f1=new Polynom("3x^2+4");
function f2=new Monom("3x^2");
Operation op2= Operation.Max;
function f3=new Polynom("5x^4+4x^2");
function f4=new Monom("3x");


ComplexFunction cf=new ComplexFunction(op, f1, f2);
ComplexFunction cf2=new ComplexFunction(op2, f3, f4);
cf2.min(cf);
System.out.println(cf2.toString());

	}

}
