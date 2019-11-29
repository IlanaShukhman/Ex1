package Ex1;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub'
Operation op = null;
String s="Plus(3x+2,4)";
int i=s.indexOf('(');
System.out.println(i);
String s2=s.substring(0,i);
System.out.println(s2);

op=op.Plus;
System.out.println(op.toString());
	}

}
