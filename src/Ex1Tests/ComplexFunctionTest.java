package Ex1Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.function;

class ComplexFunctionTest {
	private function EXPECTED;
	private function ACTUAL;
	private Operation op;
	private final double EPS=0.00001;
	
	//@Test
	final void testF() {
		function left=new Polynom("x^3-x^2+x-1");
		function right=new Monom("10x");
		for (Operation o : Operation.values()) {
			op=o;
		switch (op) {
		case Comp:
		{
			ACTUAL=new ComplexFunction(op,left,right);
			double x=0;
			double expected=-1;
			double actual=ACTUAL.f(x);
			assertEquals(expected, actual,EPS, "ERR: testF failing to Comp. "+ACTUAL.toString());
		}//Comp
		case Divid:
		{
			ACTUAL=new ComplexFunction(op,left,right);
			double x=2;
			double expected=5.0/20.0;
			double actual=ACTUAL.f(x);
			assertEquals(expected, actual,EPS, "ERR: testF failing to Divide. "+ACTUAL.toString());
		}//Divide
		case Error:
		case Max:
		case Min:
		case None:
		case Plus:
		case Times:

		}//switch
		}//for
	}//testF()
	

//	@Test	
	final void testComplexFunction() {
		fail("Not yet implemented"); // TODO
	}

//	@Test
	final void testComplexFunctionOperationFunctionFunction() {
		fail("Not yet implemented"); // TODO
	}

	@Test	
	final void testLeftString() {
		//System.out.println("**** TestLeftString****");
		String str="Plus(Max(Max(x^2,x),3),Times(x^5,4))";
		ComplexFunction actual= new ComplexFunction();
		actual=actual.findLeftFunction(str);
		assertEquals(str, actual.toString(), "ERR: failing to testLeftString."+actual.toString());
		//System.out.println(f);
		//System.out.println("Should be Max(Max(1.0x^2 , 1.0x) , None(3.0 , null))");
	}
	
	
	
	@Test
	final void testSimpleCF() {
		//System.out.println("**** TestSimpleCF****");
		String s1="max(x^2,x)";
		boolean e=ComplexFunction.simpleCF(s1);
		assertTrue(e);
		String s2="mul(4,plus(x,6))";
		assertFalse(ComplexFunction.simpleCF(s2));
	}

	@Test
	final void testInitFromString() {
		//System.out.println("**** TestInitFromString****");
		String s1="max(x^2,x)";
		ComplexFunction f1=new ComplexFunction(s1);
		//System.out.println(f1);
		assertEquals(s1, f1.toString(), "ERR: failing to initFromString");
		String expected="Plus(Max(Max(x^2,x),3),Mul(x^5,4))";
		ACTUAL=new ComplexFunction(expected);
		//System.out.println(f2);
		assertEquals(expected, ACTUAL.toString(), "ERR: failing to initFromString");
	}

//	@Test

	final void testCopy() {
		fail("Not yet implemented"); // TODO
	}

//	@Test
	final void testPlus() {
		fail("Not yet implemented"); // TODO
	}

//	@Test
	final void testMul() {
		fail("Not yet implemented"); // TODO
	}

//	@Test
	final void testDiv() {
		fail("Not yet implemented"); // TODO
	}

//	@Test
	final void testMax() {
		fail("Not yet implemented"); // TODO
	}

//	@Test
	final void testMin() {
		fail("Not yet implemented"); // TODO
	}

//	@Test
	final void testComp() {
		fail("Not yet implemented"); // TODO
	}

//	@Test
	final void testLeft() {
		fail("Not yet implemented"); // TODO
	}

//	@Test
	final void testRight() {
		fail("Not yet implemented"); // TODO
	}

//	@Test
	final void testGetOp() {
		fail("Not yet implemented"); // TODO
	}

}
