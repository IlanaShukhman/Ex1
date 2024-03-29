package Ex1Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Functions_GUI;
import Ex1.Monom;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.function;

class ComplexFunctionTest {
	private function EXPECTED;
	private function ACTUAL;
	private Operation op;
	private final double EPS=0.00001;
	private ComplexFunction left=new ComplexFunction("min(div(x^3,x),x)");
	private ComplexFunction right=new ComplexFunction("max(mul(x^2,x),plus(x+2,2))");


	@Test
	final void testF() {
		for (Operation o : Operation.values()) {
			switch (o) {
			case Comp:
			{
				ACTUAL=new ComplexFunction(o,left,right);
				double x=4;
				double actual=ACTUAL.f(x);
				EXPECTED=new ComplexFunction("comp(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
				double expected=EXPECTED.f(x);
				assertEquals(actual,expected,EPS, "ERR: testF failing to calculate "+o.name()+" operation. We expected to get: "+EXPECTED.toString()+"="+expected+" But we get: "+ACTUAL.toString()+"="+actual);	
				break;
			}//Comp
			case Divid:
			{
				ACTUAL=new ComplexFunction(o,left,right);
				double x=4;
				double actual=ACTUAL.f(x);
				EXPECTED=new ComplexFunction("div(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
				double expected=EXPECTED.f(x);
				assertEquals(actual,expected,EPS, "ERR: testF failing to calculate "+o.name()+"  operation. We expected to get: "+EXPECTED.toString()+"="+expected+" But we get: "+ACTUAL.toString()+"="+actual);
				break;
			}//Divide
			case Error:
			{
				boolean flag=true;
				try {
					ACTUAL=new ComplexFunction(o,left,right);
					double x=4;
					double actual=ACTUAL.f(x);
					EXPECTED=new ComplexFunction("div(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
					double expected=EXPECTED.f(x);
					assertEquals(actual,expected,EPS, "ERR: testF failing to calculate "+o.name()+"  operation. We expected to get: "+EXPECTED.toString()+"="+expected+" But we get: "+ACTUAL.toString()+"="+actual);
					} 
					catch (Exception e)
					{
						flag=false;
					}
				assertEquals(false, flag,"ERR: Operation is Eror and you trying to calculate something.");
				break;
			}//Error
			case Max:
			{
				ACTUAL=new ComplexFunction(o,left,right);
				double x=4;
				double actual=ACTUAL.f(x);
				EXPECTED=new ComplexFunction("max(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
				double expected=EXPECTED.f(x);
				assertEquals(actual,expected,EPS, "ERR: testF failing to calculate "+o.name()+" operation. We expected to get: "+EXPECTED.toString()+"="+expected+" But we get: "+ACTUAL.toString()+"="+actual);
				break;
			}//Max
			case Min:
			{
				ACTUAL=new ComplexFunction(o,left,right);
				double x=4;
				double actual=ACTUAL.f(x);
				EXPECTED=new ComplexFunction("min(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
				double expected=EXPECTED.f(x);
				assertEquals(actual,expected,EPS, "ERR: testF failing to calculate "+o.name()+" operation. We expected to get: "+EXPECTED.toString()+"="+expected+" But we get: "+ACTUAL.toString()+"="+actual);
				break;
			}//Min
			case None:
			{
				ACTUAL=new ComplexFunction(o,left,null);
				double x=4;
				double actual=ACTUAL.f(x);
				double expected=left.f(x);
				assertEquals(actual,expected,EPS, "ERR: testF failing to calculate "+o.name()+" operation. We expected to get: "+EXPECTED.toString()+"="+expected+" But we get: "+ACTUAL.toString()+"="+actual);
				ACTUAL=new ComplexFunction("none(none(none(x,null),null),null)");
				assertEquals(ACTUAL.f(x),4);
				break;
			}//None
			case Plus:
			{
				ACTUAL=new ComplexFunction(o,left,right);
				double x=4;
				double actual=ACTUAL.f(x);
				EXPECTED=new ComplexFunction("plus(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
				double expected=EXPECTED.f(x);
				assertEquals(actual,expected,EPS, "ERR: testF failing to calculate "+o.name()+" operation. We expected to get: "+EXPECTED.toString()+"="+expected+" But we get: "+ACTUAL.toString()+"="+actual);
				break;
			}//Plus
			case Times:
			{
				left=new ComplexFunction("min(div(x^3,x),x)");
				right=new ComplexFunction("max(mul(x^2,x),plus(x+2,2))");
				ACTUAL=new ComplexFunction(o,left,right);
				double x=4;
				double actual=ACTUAL.f(x);
				EXPECTED=new ComplexFunction("mul(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
				double expected=EXPECTED.f(x);
				assertEquals(actual,expected,EPS, "ERR: testF failing to calculate "+o.name()+" operation. We expected to get: "+EXPECTED.toString()+"="+expected+" But we get: "+ACTUAL.toString()+"="+actual);
				break;
			}//TImes
			}//switch
		}//for
	}//testF()


	@Test
	final void testInitFromString() {
		left=new ComplexFunction("min(div(x^3,x),x)");
		right=new ComplexFunction("max(mul(x^2,x),plus(x+2,2))");
		ACTUAL=new ComplexFunction(op.Times,left,right);
		EXPECTED=new ComplexFunction(ACTUAL.toString());
		assertEquals(EXPECTED, ACTUAL, "ERR: failing to initFromString. We expected to get: "+EXPECTED.toString()+" But we got: "+ACTUAL.toString());

		ACTUAL=new ComplexFunction(op.None,new Polynom("x^2"),null);
		EXPECTED=new Polynom("x^2");
		assertEquals(EXPECTED, ACTUAL, "ERR: failing to initFromString. We expected to get: "+EXPECTED.toString()+" But we got: "+ACTUAL.toString());
		boolean flag=true;
		String ans="Please throw error";
		try {
			ACTUAL=new ComplexFunction(ans);
		} catch (Exception e) {
			flag=false;
		}
		assertFalse(flag,"ERR:ComplexFunctiokn wrong format. Trying to construct: "+ans);
		
		flag=true;
		ans="+(x^3,2x)";
		try {
			ACTUAL=new ComplexFunction(ans);
		} catch (Exception e) {
			flag=false;
		}
		assertFalse(flag,"ERR:ComplexFunctiokn wrong format. Trying to construct: "+ans);
		
		flag=true;
		ans="plus(mulx,x),x)";
		try {
			ACTUAL=new ComplexFunction(ans);
		} catch (Exception e) {
			flag=false;
		}
		assertFalse(flag,"ERR:ComplexFunctiokn wrong format. Trying to construct: "+ans);
	}//testInitFromString

	@Test

	final void testCopy() {
		op=Operation.Plus;	
		ACTUAL=new ComplexFunction();
		EXPECTED=new ComplexFunction(op,left,right);
		ACTUAL=EXPECTED.copy();
		assertEquals(EXPECTED, ACTUAL, "ERR: failing to copy. We expected to get: "+EXPECTED.toString()+" But we got: "+ACTUAL.toString());
	}

	@Test
	final void testPlus() {
		op=Operation.Plus;
		ACTUAL=new ComplexFunction(op,left,right);
		EXPECTED=new ComplexFunction("plus(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
		assertEquals(EXPECTED, ACTUAL, "ERR: failing to do Plus operation. We expected to get: "+EXPECTED.toString()+" But we got: "+ACTUAL.toString());
		
		ACTUAL=new ComplexFunction("max(mul(x^2,x),plus(x+2,2))");
		((ComplexFunction) ACTUAL).plus(ACTUAL);
		EXPECTED=new ComplexFunction("plus(max(mul(x^2,x),plus(x+2,2)),max(mul(x^2,x),plus(x+2,2)))");
		//System.out.println(EXPECTED.toString());
		//System.out.println(ACTUAL.toString());
		assertEquals(EXPECTED, ACTUAL);
	}

	@Test
	final void testMul() {
		op=Operation.Times;
		ACTUAL=new ComplexFunction(op,left,right);
		EXPECTED=new ComplexFunction("mul(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
		assertEquals(EXPECTED.toString(), ACTUAL.toString(), "ERR: failing to do Times operation. We expected to get: "+EXPECTED.toString()+" But we got: "+ACTUAL.toString());
	}

	@Test
	final void testDiv() {
		op=Operation.Divid;
		ACTUAL=new ComplexFunction(op,left,right);
		EXPECTED=new ComplexFunction("div(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
		assertEquals(EXPECTED, ACTUAL, "ERR: failing to do Divide operation. We expected to get: "+EXPECTED.toString()+" But we got: "+ACTUAL.toString());
	}

	@Test
	final void testMax() {
		op=Operation.Max;
		ACTUAL=new ComplexFunction(op,left,right);
		EXPECTED=new ComplexFunction("max(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
		assertEquals(EXPECTED, ACTUAL, "ERR: failing to do Max operation. We expected to get: "+EXPECTED.toString()+" But we got: "+ACTUAL.toString());
	}

	@Test
	final void testMin() {
		op=Operation.Min;
		ACTUAL=new ComplexFunction(op,left,right);
		EXPECTED=new ComplexFunction("min(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
		assertEquals(EXPECTED, ACTUAL, "ERR: failing to do Min operation. We expected to get: "+EXPECTED.toString()+" But we got: "+ACTUAL.toString());
	}

	@Test
	final void testComp() {
		op=Operation.Comp;
		ACTUAL=new ComplexFunction(op,left,right);
		EXPECTED=new ComplexFunction("comp(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
		assertEquals(EXPECTED, ACTUAL, "ERR: failing to do Comp operation. We expected to get: "+EXPECTED.toString()+" But we got: "+ACTUAL.toString());
	}

	@Test
	final void testLeft() {
		op=op.Plus;
		ComplexFunction actual=new ComplexFunction(op,left,right);
		function l=actual.left();
		assertEquals(left, l,"ERR: failing to get left function. "+"We expected to get: "+left.toString()+" But we got: "+actual.left().toString());
		actual=new ComplexFunction();
		assertEquals(null, actual.left(),"ERR: failed  to return null");
	}

	@Test
	final void testRight() {
		op=op.Plus;
		ComplexFunction actual=new ComplexFunction(op,left,right);
		function r=actual.right();
		assertEquals(right, r,"ERR: failing to get right function. "+"We expected to get: "+right.toString()+" But we got: "+actual.right().toString());
	}

	@Test
	final void testGetOp() {
		op=op.None;
		ComplexFunction actual=new ComplexFunction(op,left,null);
		Operation o=actual.getOp();
		assertEquals(o, op,"ERR: failing to get operation function. We expected to get: "+op.toString()+" But we got: "+o.toString());
		
	}

	@Test
	final void testEquals()
	{
		function f=new Monom("3x^2");
		function g=new Polynom("x^5+3x^3+2x-8");
		op=Operation.Min;
		ACTUAL=new ComplexFunction(g.toString());
		assertEquals(g, ACTUAL,"ERR: failing to compare ComplexFunction to Polynom");
		ACTUAL=new ComplexFunction(f.toString());
		assertEquals(f, ACTUAL,"ERR: failing to compare ComplexFunction to Monom");

		ACTUAL=new ComplexFunction(right);
		assertEquals(right, ACTUAL,"ERR: failing to compare ComplexFunction to Monom");
		
		ACTUAL=new ComplexFunction("div(x^2,x-90)");
		EXPECTED=new ComplexFunction("div(x^2,x-90)");
		assertEquals(EXPECTED,ACTUAL,"ERR: failing to compare complexFunction to another ComplexFunction");


		ACTUAL=new ComplexFunction("min(3x^2,x^5+3x^3+2x-8)");
		EXPECTED=new ComplexFunction(op,f,g);
		assertEquals(EXPECTED,ACTUAL,"ERR: failing to compare complexFunction to another ComplexFunction");

		EXPECTED=new ComplexFunction(op,g,f);
		assertEquals(EXPECTED,ACTUAL,"ERR: failing to check commutative case in ComplexFunction equals");


		EXPECTED=new ComplexFunction("min(3x^2,plus(x^5+3x^3,2x-8))");
		assertEquals(EXPECTED,ACTUAL,"ERR: failing to compare between two complexFunctions that are the same but written differently");


		EXPECTED=new ComplexFunction("min(x^2+2x^2,x^5+3x^3+2x-8)");
		assertEquals(EXPECTED,ACTUAL,"ERR: failing to compare complexFunction to another ComplexFunction");


		ACTUAL=new ComplexFunction(op.None,null,null);
		EXPECTED=new ComplexFunction();
		assertEquals(EXPECTED,ACTUAL,"ERR: failing to compare empty complexFunction to another ComplexFunction");		

		
		ACTUAL=new ComplexFunction("min(3x^2,x^5+3x^3+2x-8)");
		EXPECTED=new ComplexFunction("max(3x^2,x^5+3x^3+2x-8)");
		assertNotEquals(EXPECTED,ACTUAL,"ERR: failing to return false when ComplexFunctions are not the same");
		ACTUAL=new ComplexFunction("3x^2");
		assertEquals(ACTUAL,f,"ERR: failing to return true when monom and complexFunction are the same");
	}//testEquals

}
