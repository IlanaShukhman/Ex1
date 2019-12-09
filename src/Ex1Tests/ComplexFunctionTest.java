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
				ACTUAL=new ComplexFunction(o,left,right);
				double x=4;
				double actual=ACTUAL.f(x);
				double expected=left.f(x);
				assertEquals(actual,expected,EPS, "ERR: testF failing to calculate "+o.name()+" operation. We expected to get: "+EXPECTED.toString()+"="+expected+" But we get: "+ACTUAL.toString()+"="+actual);
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
		assertEquals(EXPECTED.toString(), ACTUAL.toString(), "ERR: failing to initFromString. We expected to get: "+EXPECTED.toString()+" But we got: "+ACTUAL.toString());

	}

	@Test

	final void testCopy() {
		op=Operation.Plus;		
		EXPECTED=new ComplexFunction(op,left,right);
		ACTUAL=EXPECTED.copy();
		assertEquals(EXPECTED.toString(), ACTUAL.toString(), "ERR: failing to copy. We expected to get: "+EXPECTED.toString()+" But we got: "+ACTUAL.toString());
	}

	@Test
	final void testPlus() {
		op=Operation.Plus;
		ACTUAL=new ComplexFunction(op,left,right);
		EXPECTED=new ComplexFunction("plus(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
		assertEquals(EXPECTED.toString(), ACTUAL.toString(), "ERR: failing to do Plus operation. We expected to get: "+EXPECTED.toString()+" But we got: "+ACTUAL.toString());
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
		assertEquals(EXPECTED.toString(), ACTUAL.toString(), "ERR: failing to do Divide operation. We expected to get: "+EXPECTED.toString()+" But we got: "+ACTUAL.toString());
	}

	@Test
	final void testMax() {
		op=Operation.Max;
		ACTUAL=new ComplexFunction(op,left,right);
		EXPECTED=new ComplexFunction("max(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
		assertEquals(EXPECTED.toString(), ACTUAL.toString(), "ERR: failing to do Max operation. We expected to get: "+EXPECTED.toString()+" But we got: "+ACTUAL.toString());
	}

	@Test
	final void testMin() {
		op=Operation.Min;
		ACTUAL=new ComplexFunction(op,left,right);
		EXPECTED=new ComplexFunction("min(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
		assertEquals(EXPECTED.toString(), ACTUAL.toString(), "ERR: failing to do Min operation. We expected to get: "+EXPECTED.toString()+" But we got: "+ACTUAL.toString());
	}

	@Test
	final void testComp() {
		op=Operation.Comp;
		ACTUAL=new ComplexFunction(op,left,right);
		EXPECTED=new ComplexFunction("comp(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
		assertEquals(EXPECTED.toString(), ACTUAL.toString(), "ERR: failing to do Comp operation. We expected to get: "+EXPECTED.toString()+" But we got: "+ACTUAL.toString());
	}

	@Test
	final void testLeft() {
		ComplexFunction actual=new ComplexFunction(op,left,right);
		function l=actual.left();
		assertEquals(left.toString(), l.toString(),"ERR: failing to get left function. "+"We expected to get: "+left.toString()+" But we got: "+actual.left().toString());
	}

	@Test
	final void testRight() {
		ComplexFunction actual=new ComplexFunction(op,left,right);
		function r=actual.right();
		assertEquals(right.toString(), r.toString(),"ERR: failing to get right function. "+"We expected to get: "+right.toString()+" But we got: "+actual.right().toString());
	}

	@Test
	final void testGetOp() {
		op=op.None;
		ComplexFunction actual=new ComplexFunction(op,left,right);
		Operation o=actual.getOp();
		assertEquals(o.toString(), op.toString(),"ERR: failing to get operation function. We expected to get: "+op.toString()+" But we got: "+o.toString());

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

		ACTUAL=new ComplexFunction("div(x^2,x)");
		EXPECTED=new ComplexFunction("div(x^2,x)");
		assertEquals(EXPECTED,ACTUAL,"ERR: failing to compare complexFunction to another ComplexFunction");


		ACTUAL=new ComplexFunction("min(3x^2,x^5+3x^3+2x-8)");
		EXPECTED=new ComplexFunction(op,f,g);
		assertEquals(EXPECTED,ACTUAL,"ERR: failing to compare complexFunction to another ComplexFunction");

				EXPECTED=new ComplexFunction(op,g,f);
				assertEquals(EXPECTED,ACTUAL,"ERR: failing to compare complexFunction to another ComplexFunction");

		//Should we do that if f and g are differant but the checkByInterval returns true, we then we return true?
		//Like in this example, we can make it true:
		EXPECTED=new ComplexFunction("min(3x^2,plus(x^5+3x^3,2x-8))");
		assertEquals(EXPECTED,ACTUAL,"ERR: failing to compare complexFunction to another ComplexFunction");
		//Tell me which, so I can write it down in issue.

		EXPECTED=new ComplexFunction("min(x^2+2x^2,x^5+3x^3+2x-8)");
		assertEquals(EXPECTED,ACTUAL,"ERR: failing to compare complexFunction to another ComplexFunction");
		
//Should we solve it? (Null pointer Exception). Or try and catch?		
		ACTUAL=new ComplexFunction(op.None,null,null);
		EXPECTED=new ComplexFunction();
		assertEquals(EXPECTED,ACTUAL,"ERR: failing to compare empty complexFunction to another ComplexFunction");		
		Polynom a=new Polynom("x^5+3x^3+2x-8");
		Polynom b=new Polynom("3x^2");
		a.add(b);
		function h=a;
		ACTUAL=new ComplexFunction(h);
		EXPECTED=new ComplexFunction("3x^2+x^5+3x^3+2x-8");
		


		ACTUAL=new ComplexFunction("min(3x^2,x^5+3x^3+2x-8)");
		EXPECTED=new ComplexFunction("max(3x^2,x^5+3x^3+2x-8)");
		assertNotEquals(EXPECTED,ACTUAL,"ERR: failing to return false when ComplexFunctions are not the same");
		ACTUAL=new ComplexFunction("3x^2");
		assertEquals(ACTUAL,f,"ERR: failing to return true when monom and complexFunction are the same");




	}//testEquals

}
