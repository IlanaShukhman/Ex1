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
	

//	@Test	
	final void testComplexFunction() {
		fail("Not yet implemented"); // TODO
	}

//	@Test
	final void testComplexFunctionOperationFunctionFunction() {
		fail("Not yet implemented"); // TODO
	}

	//@Test	
	final void testLeftString() {
		
	}
	
	
	
	//@Test
	final void testSimpleCF() {
		
	}

	@Test
	final void testInitFromString() {
		left=new ComplexFunction("min(div(x^3,x),x)");
		right=new ComplexFunction("max(mul(x^2,x),plus(x+2,2))");
		ACTUAL=new ComplexFunction(op.Times,left,right);
		EXPECTED=new ComplexFunction("mul(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
		assertEquals(EXPECTED.toString(), ACTUAL.toString(), "ERR: failing to initFromString. We expected to get: "+EXPECTED.toString()+" But we got: "+ACTUAL.toString());
		
	}

	//@Test

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

}
