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
					ACTUAL=new ComplexFunction(o.Comp,left,right);
					double x=4;
					double actual=ACTUAL.f(x);
					EXPECTED=new ComplexFunction("comp(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
					double expected=EXPECTED.f(x);
					assertEquals(expected, actual,EPS, "ERR: testF failing to Comp. "+ACTUAL.toString());
				}//Comp
				case Divid:
				{
					ACTUAL=new ComplexFunction(o.Divid,left,right);
					double x=4;
					double actual=ACTUAL.f(x);
					EXPECTED=new ComplexFunction("div(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
					double expected=EXPECTED.f(x);
					assertEquals(expected, actual,EPS, "ERR: testF failing to Divide. "+ACTUAL.toString());
				}//Divide
				case Error:
				{
					
				}//Error
				case Max:
				{
					ACTUAL=new ComplexFunction(o.Max,left,right);
					double x=4;
					double actual=ACTUAL.f(x);
					EXPECTED=new ComplexFunction("max(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
					double expected=EXPECTED.f(x);
					assertEquals(expected, actual,EPS, "ERR: testF failing to Max. "+ACTUAL.toString());
				}//Max
				case Min:
				{
					ACTUAL=new ComplexFunction(o.Min,left,right);
					double x=4;
					double actual=ACTUAL.f(x);
					EXPECTED=new ComplexFunction("min(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
					double expected=EXPECTED.f(x);
					assertEquals(expected, actual,EPS, "ERR: testF failing to Min. "+ACTUAL.toString());
				}//Min
				case None:
				{
					
				}//None
				case Plus:
				{
					ACTUAL=new ComplexFunction(o.Plus,left,right);
					double x=4;
					double actual=ACTUAL.f(x);
					EXPECTED=new ComplexFunction("plus(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
					double expected=EXPECTED.f(x);
					assertEquals(expected, actual,EPS, "ERR: testF failing to Plus. "+ACTUAL.toString());
				}//Plus
				case Times:
				{
					left=new ComplexFunction("min(div(x^3,x),x)");
					right=new ComplexFunction("max(mul(x^2,x),plus(x+2,2))");
					ACTUAL=new ComplexFunction(o.Times,left,right);
					double x=4;
					double actual=ACTUAL.f(x);
					EXPECTED=new ComplexFunction("mul(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
					double expected=EXPECTED.f(x);
					assertEquals(actual,expected,EPS, "ERR: testF failing to Times. "+ACTUAL.toString());	
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
		assertEquals(EXPECTED.toString(), ACTUAL.toString(), "ERR: failing to initFromString");
		
	}

	//@Test

	final void testCopy() {
		
	}

	@Test
	final void testPlus() {
		op=Operation.Plus;
		ACTUAL=new ComplexFunction(op,left,right);
		EXPECTED=new ComplexFunction("plus(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
		assertEquals(EXPECTED.toString(), ACTUAL.toString(), "ERR: failing to do Plus operation");
	}

	@Test
	final void testMul() {
		op=Operation.Times;
		ACTUAL=new ComplexFunction(op,left,right);
		EXPECTED=new ComplexFunction("mul(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
		assertEquals(EXPECTED.toString(), ACTUAL.toString(), "ERR: failing to do Times operation");
	}

	@Test
	final void testDiv() {
		op=Operation.Divid;
		ACTUAL=new ComplexFunction(op,left,right);
		EXPECTED=new ComplexFunction("div(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
		assertEquals(EXPECTED.toString(), ACTUAL.toString(), "ERR: failing to do Divid operation");
	}

	@Test
	final void testMax() {
		op=Operation.Max;
		ACTUAL=new ComplexFunction(op,left,right);
		EXPECTED=new ComplexFunction("max(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
		assertEquals(EXPECTED.toString(), ACTUAL.toString(), "ERR: failing to do Max operation");
	}

	@Test
	final void testMin() {
		op=Operation.Min;
		ACTUAL=new ComplexFunction(op,left,right);
		EXPECTED=new ComplexFunction("min(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
		assertEquals(EXPECTED.toString(), ACTUAL.toString(), "ERR: failing to do Min operation");
	}

	@Test
	final void testComp() {
		op=Operation.Comp;
		ACTUAL=new ComplexFunction(op,left,right);
		EXPECTED=new ComplexFunction("comp(min(div(x^3,x),x),max(mul(x^2,x),plus(x+2,2)))");
		assertEquals(EXPECTED.toString(), ACTUAL.toString(), "ERR: failing to do Comp operation");
	}

	//@Test
	final void testLeft() {
		fail("Not yet implemented"); // TODO
	}

	//@Test
	final void testRight() {
		fail("Not yet implemented"); // TODO
	}

	//@Test
	final void testGetOp() {
		fail("Not yet implemented"); // TODO
	}

}
