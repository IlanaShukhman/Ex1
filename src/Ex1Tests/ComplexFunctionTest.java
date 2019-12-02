package Ex1Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.function;

class ComplexFunctionTest {

	@Test
	final void testF() {
		fail("Not yet implemented"); // TODO
	}

	@Test	
	final void testComplexFunction() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testComplexFunctionOperationFunctionFunction() {
		fail("Not yet implemented"); // TODO
	}

	@Test	
	final void testLeftString() {
		System.out.println("**** TestLeftString****");
		String str="plus(max(max(x^2,x),3),mul(x^5,4))";
		ComplexFunction f= new ComplexFunction();
		f=f.findLeftFunction(str);
		System.out.println(f);
		System.out.println("Should be Max(Max(1.0x^2 , 1.0x) , None(3.0 , null))");
	}
	
	
	
	@Test
	final void testSimpleCF() {
		System.out.println("**** TestSimpleCF****");
		String s1="max(x^2,x)";
		boolean e=ComplexFunction.simpleCF(s1);
		assertTrue(e);
		String s2="mul(4,plus(x,6))";
		assertFalse(ComplexFunction.simpleCF(s2));
	}

	@Test
	final void testInitFromString() {
		System.out.println("**** TestInitFromString****");
		String s1="max(x^2,x)";
		ComplexFunction f1=new ComplexFunction(s1);
		System.out.println(f1);
		String str="plus(max(max(x^2,x),3),mul(x^5,4))";
		ComplexFunction f2=new ComplexFunction(str);
		System.out.println(f2);
	}

	@Test
	final void testOperationAnalyzing() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testCopy() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testPlus() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testMul() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testDiv() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testMax() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testMin() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testComp() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testLeft() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testRight() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testGetOp() {
		fail("Not yet implemented"); // TODO
	}

}
