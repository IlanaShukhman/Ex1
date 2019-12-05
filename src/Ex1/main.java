package Ex1;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub'
		
		function g= new Polynom("3x^2+4");
		Operation op=Operation.Plus;
		function f=new Monom("3x^2");
		ComplexFunction c =new ComplexFunction(f.toString());
		System.out.println(c.equals(f));
		
		
		

	}

}
