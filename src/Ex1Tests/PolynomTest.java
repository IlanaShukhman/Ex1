package Ex1Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Ex1.MyRandom;
import Ex1.Monom;
import Ex1.Polynom;
import Ex1.function;

public class PolynomTest {
private  final Double EPS=0.000001;
private Monom ZERO=new Monom();
private Monom ONE=new Monom(1,0);
private Monom MINUS=new Monom(-1,0);
private Polynom EXPECTED;
private Polynom ACTUAL;
private Double expected;
private Double actual;
private MyRandom rand= new MyRandom(12345);
private final Integer min=-99;
private final Integer max=99;
	@Test
	public void testPolynom() {
	    ACTUAL=new Polynom();
	    assertEquals("ERR: failing to create empty monom", true, ACTUAL.isZero());
	}//testPolynom

	@Test
	public void testPolynomString()
	{
		String s="4.0x^3+5.0x+2.0";
		ACTUAL=new Polynom(s);
		assertEquals("ERR: failing to make polynom from string. We expect to: "+s+" we got: "+ACTUAL.toString(), s, ACTUAL.toString());	
		boolean flage=true;
		try {
			ACTUAL=new Polynom("");
		} catch (Exception e) {
			flage=false;
		}//catch
		assertEquals("ERR: you can't init empty string to monom constructor", false, flage);	
	}//test
	
/**
 * Checking manual calculation of f(x).
 * y(x)=3x^3+2x^2+x when x=0 y(x)=0.
 * 
 */
	@Test
	public void testF() {
		for (int i = 0; i < 100; i++) {
			double x=rand.randomInt(min,max);
			Polynom p=new Polynom("3.2x^3+6x^2-7x+7");
			actual=p.f(x);
			expected=3.2*Math.pow(x, 3)+6*Math.pow(x, 2)-7*x+7;
			assertEquals("ERR: function calculator faild. we expect: "+expected+" we got: "+p.f(x), expected, p.f(x),this.EPS); 
		}//for
	}//testF
	
	/**
	 * Checking:
	 * 1.Adding two polynoms:  (3x^2+4)+(5x^3+2x^2+4x+6)= 5x^3+5x^2+4x+10
	 * 2.Adding non zero polynom with zero:	(5x^3+5x^2+4x+10)+0=5x^3+5x^2+4x+10
	 * 3.Adding zero polynom with non zero polynom:	0+(5x^3+5x^2+4x+10)=5x^3+5x^2+4x+10
	 * 4.Adding two different polynoms power:(5x^3+5x^2+4x+10)+(x^7+x^6)=x^7+x^6+5x^3+5x^2+4x+10
	 */
	@Test
	public void testAddPolynom_able() {
		ACTUAL=new Polynom("3x^2+4");
		Polynom pZero=new Polynom();
		Polynom p=new Polynom("5x^3+2x^2+4x+6");
		ACTUAL.add(p);
		EXPECTED=new Polynom("5x^3+5x^2+4x+10");
		assertEquals("ERR: failing to add two polynoms. We expected to get: "+EXPECTED.toString()+" we got: "+ACTUAL.toString(), EXPECTED.toString(), ACTUAL.toString());
		ACTUAL.add(pZero);
		assertEquals("ERR: failing to add non zero polynom with zero. We expected to get: "+EXPECTED.toString()+" we got: "+ACTUAL.toString(),EXPECTED.toString(), ACTUAL.toString());
		pZero.add(ACTUAL);
		assertEquals("ERR: failing to add zero polynom with non zero polynom. We expected to get: "+EXPECTED.toString()+" we got: "+ACTUAL.toString(),EXPECTED.toString(), ACTUAL.toString());
		p=new Polynom("x^7+x^6");
		ACTUAL.add(p);
		EXPECTED=new Polynom("x^7+x^6+5x^3+5x^2+4x+10");
		assertEquals("ERR: failing to add two different polynoms power. We expected to get: "+EXPECTED.toString()+" we got: "+ACTUAL.toString(),EXPECTED.toString(), ACTUAL.toString());
	}//testAddPolynom_able

	
	/**
	 * Checking three cases:
	 * 1.Adding polynom with new monom power: (3x^2+8x+7)+4x^4=4x^4+3x^2+8x+7     
	 * 2.Adding polynom with zero monom: (4x^4+3x^2+8x+7)+0=4x^4+3x^2+8x+7
	 * 3.Adding polynom with existing monom power: (4x^4+3x^2+8x+7)+(2x^4)=6x^4+3x^2+8x+7
	 * 4.Adding polynom with exactly some negative monom:(6x^4+3x^2+8x+7)-(8x)=6x^4+3x^2+7
	 */
	@Test
	public void testAddMonom() {
		ACTUAL=new Polynom("3x^2+8x+7");
		Monom dif=new Monom("4x^4");
		ACTUAL.add(dif);
		EXPECTED=new Polynom("4x^4+3x^2+8x+7");
		assertEquals("ERR: fail to adding polynom with new monom power. We expect to: "+EXPECTED.toString()+" we got: "+ACTUAL.toString(), true, ACTUAL.equals(EXPECTED));
		ACTUAL.add(ZERO);
		assertEquals("ERR: fail to adding polynom with zero monom. We expect to: "+EXPECTED.toString()+" we got: "+ACTUAL.toString(), true, ACTUAL.equals(EXPECTED));
		EXPECTED=new Polynom("6x^4+3x^2+8x+7");
		Monom same=new Monom("2x^4");
		ACTUAL.add(same);
		assertEquals("ERR: fail to adding polynom with existing monom power. We expect to: "+EXPECTED.toString()+" we got: "+ACTUAL.toString(), true, ACTUAL.equals(EXPECTED));
		EXPECTED=new Polynom("6x^4+3x^2+7");
		Monom exist=new Monom("-8x");
		ACTUAL.add(exist);
		assertEquals("ERR: fail to adding polynom with exactly some negative monom. We expect to: "+EXPECTED.toString()+" we got: "+ACTUAL.toString(), true, ACTUAL.equals(EXPECTED));
	}//testAddMonom
/**
 * Checking cases: 
 * 1.Same polynoms substraction: (3x^3+2x^2+x+1)-(3x^3+2x^2+x+1)=0
 * 2.Different Polynoms: (3x^3+2x^2+x+1)-(2x^2+2x)=3x^3-x+1
 * 3.Polynom with zero: (x^2+4x+7)-0=(x^2+4x+7)
 * 4.zero with Polynom:0-(x^2+4x+7)=-x^2-4x-7
 */
	@Test
	public void testSubstractPolynom_able() {
	   ACTUAL=new Polynom("3x^3+2x^2+x+1");
	   Polynom copy=(Polynom) ACTUAL.copy();
	   Polynom zeroP=new Polynom();
	   copy.substract(ACTUAL);
	   assertEquals("ERR: failing to substract exactly the same polynoms. \t We expect to get: "+zeroP.toString()+" we got: "+ACTUAL.toString(), zeroP.toString(), copy.toString());
	   copy=new Polynom("2x^2+2x");
	   EXPECTED=new Polynom("3x^3-x+1");
	   ACTUAL.substract(copy);
	   assertEquals("ERR: failing to substract two different polynoms. \t We expect to get: "+EXPECTED.toString()+" we got: "+ACTUAL.toString(), EXPECTED.toString(), ACTUAL.toString());
	   EXPECTED=(Polynom) ACTUAL.copy();
	   ACTUAL.substract(zeroP);
	   assertEquals("ERR: failing to substract polynom by zero. \t We expect to get: "+EXPECTED.toString()+" we got: "+ACTUAL.toString(), EXPECTED.toString(), ACTUAL.toString());
	   EXPECTED.multiply(MINUS);
	   zeroP.substract(ACTUAL);
	   assertEquals("ERR: failing to substract zero by polynom. \t We expect to get: "+EXPECTED.toString()+" we got: "+zeroP.toString(), EXPECTED.toString(), zeroP.toString());
	}//testSubstractPolynom_able

	
	/**
	 * Checking:
	 * 1.Polynom - zero monom = polynom
	 * 2.Polynom p1 - new power monom = new polynom
	 */
	@Test
	public void testSubstractMonom() {
		Polynom pZero=new Polynom();
		ACTUAL=new Polynom("3x^3-2x^2+x-1");
		Monom m=new Monom("+x");
		EXPECTED=new Polynom("3x^3-2x^2-1");
		ACTUAL.substract(m);
		assertEquals("ERR: failing to substract polynom with another monom. We expected to: "+EXPECTED.toString()+" We got: "+ACTUAL.toString(), EXPECTED.toString(), ACTUAL.toString());
		ACTUAL.substract(ZERO);
		assertEquals("ERR: failing to substract polynom with zero monom", EXPECTED.toString(), ACTUAL.toString());
	}//testSubstractMonom
/**
 * Checking manually: (x-2)(x+2)=x^2-4 
 */
	@Test
	public void testMultiplyPolynom_able() {
		ACTUAL=new Polynom("x-2");
		Polynom actual2=new Polynom("x+2");
		Polynom expected= new Polynom("x^2-4");
		ACTUAL.multiply(actual2);
		assertEquals("ERR: polynom multiple is wrong. We expect to: "+expected+" We got: "+ACTUAL, true, expected.equals(ACTUAL));
	}//testMultiplyPolynom_able

	@Test
	/**
	 * By https://en.wikipedia.org/wiki/Even_and_odd_functions#Even_functions
	 * Function is Even function iff f(-x)=f(x)
	 * Function is Odd function iff f(-x)=-1*(f(x))
	 * Here we testing some polynomial functions such as:
	 * Root: Odd+Even - we expect that the roots are symetric in both side of axis x
	 * Area: Let [-a,a] to be our interval when a is real number.
	 * We expect that in even function we will got that: Area[-a,0]=Area[0,a] in contrast to Odd function when |Area[-a,0]|=|Area[0,a]|.
	 * So we use wisely polynomial function to satisfying correctness 
	 */
	public void testMathQualities() {
		oddFunctionQualities();
		evenFunctionQualities();
	}
	public void oddFunctionQualities()
	{
		Polynom p=new Polynom("x^3-x");//Oddfunction
		double rightArea=p.area(0, 4, this.EPS);
		double leftArea=p.area(-4, 0, this.EPS);
		double rootX1=p.root(-4, 0, this.EPS);
		double rootX2=p.root(0, 4, this.EPS);
		assertEquals("Left area: "+leftArea+"in odd function shouldn't be equal to: "+rightArea+" by our definition for area",false, rightArea==leftArea);
		assertEquals("In odd function we have symetric roots", Math.abs(rootX1), Math.abs(rootX2),this.EPS);
		assertEquals("ERR: this is indeed odd function: "+p.toString(),true, p.ifOddFunc());
	}//oddFunctionQualities
    public void evenFunctionQualities()
    {
	Polynom p=new Polynom("x^4-x^2");
	double rightArea=p.area(0, 4, this.EPS);
	double leftArea=p.area(-4, 0, this.EPS);
	double rootX1=p.root(-4, 0, this.EPS);
	double rootX2=p.root(0, 4, this.EPS);
	assertEquals("Left area: "+leftArea+"in Even function should equal to: "+rightArea,leftArea, rightArea,this.EPS);
	assertEquals("In even function we have symetric roots", Math.abs(rootX1), Math.abs(rootX2),this.EPS);
	assertEquals("ERR: this is indeed even function: "+p.toString(),true, p.ifEvenFunc());
    }//evenFunctionQualities
	@Test
	public void testToString() {
		String s="2.0x^4+7x^2+3.0x^2+8+2";
		ACTUAL=new Polynom(s);
		EXPECTED = new Polynom("2x^4+10x^2+10");
		assertEquals("ERR: toString failing", EXPECTED.toString(), ACTUAL.toString());
		
	}//testToString
    /**
     * Checking manual derivation: (8x^4+4x^2+2.5x^2)'=32x^3+8x+5x
     */
	@Test
	public void testDerivative() {
		Polynom actual=new Polynom("8x^4+4x^2+2.5x^2");
		Polynom p=(Polynom)actual.derivative();
		Polynom expected = new Polynom("32x^3+8x+5x");
		assertEquals("ERR: after derivation "+p.toString()+" should be equal to: "+expected.toString(), true, expected.toString().equals(p.toString()));

		}//testDerivative
	

	/**
	 * Checking:
	 * 1.Multiplying polynom with monom:(2x^5+3x^2-0.5)*(2x^3)=4x^8+6x^5-x^3
	 * 2.Multiplying polynom by one: (4x^8+6x^5-x^3)*1=4x^8+6x^5-x^3
	 * 3.Multiplying polynom by minus: (4x^8+6x^5-x^3)*-1=-4x^8-6x^5+x^3
	 * 4.Multiplying polynom by zero:	(-4x^8-6x^5+x^3)*0=0
	 */
	@Test
	public void testMultiplyByMonom() {
	    EXPECTED=new Polynom("4x^8+6x^5-x^3");
	    ACTUAL=new Polynom("2x^5+3x^2-0.5");
	    Monom m=new Monom("2x^3");
	    ACTUAL.multiply(m);
	    assertEquals("ERR: failing to Multiplying polynom with monom: We expected to get: "+EXPECTED.toString()+" we got: "+ACTUAL.toString(), EXPECTED.toString(), ACTUAL.toString());
	    ACTUAL.multiply(ONE);
	    assertEquals("ERR: failing to Multiplying polynom by one: We expected to get: "+EXPECTED.toString()+" we got: "+ACTUAL.toString(), EXPECTED.toString(), ACTUAL.toString());
	    ACTUAL.multiply(MINUS);
	    EXPECTED=new Polynom("-4x^8-6x^5+x^3");
	    assertEquals("ERR: failing to Multiplying polynom by minus: We expected to get: "+EXPECTED.toString()+" we got: "+ACTUAL.toString(), EXPECTED.toString(), ACTUAL.toString());
	    Polynom pZero=new Polynom();
	    ACTUAL.multiply(ZERO);
	    assertEquals("ERR: failing to Multiplying polynom by zero: We expected to get: "+EXPECTED.toString()+" we got: "+ACTUAL.toString(), ZERO.toString(), ACTUAL.toString());
	}//testMultiplyMonom

/**
 * Checking manually if copy function work
 */
	@Test
	public void testCopy() {
		EXPECTED = new Polynom("3x^3+8x");
		ACTUAL=(Polynom)EXPECTED.copy();
		assertEquals("We expected to get: "+EXPECTED.toString()+" We got: "+ACTUAL, true, ACTUAL.equals(EXPECTED));	
	}//testCopy
    /**
     * Checking manually correction of dividing polynom into monoms strings.
     */
	@Test
	public void testPartStrPoly() {
		String expect="8.0x^3+5.0x^2+2.0x+10";
		Polynom polynom=new Polynom(expect);
		ArrayList<String> s=polynom.partStrPoly(expect);
		StringBuilder act=new StringBuilder();
		for (int i = 0; i < s.size(); i++) {
			act.append(s.get(i));
		}//for
		assertEquals("ERR: failing to divide polynoms into monoms. we expect to: "+expect+" but we get: "+act, expect.toString(), act.toString());
	}//testPartStrPoly
/**
 * Check manually if empty constructor making zero polynom
 */
		
	public void testIsZero() {
		Polynom z=new Polynom();
		Polynom z2=new Polynom("0+0");
		Polynom mistake=new Polynom("3x");
		assertEquals("ERR: failing to check if polynom is zero", z.toString(), ZERO.toString());
		assertEquals("ERR: failing to check if polynom is zero", z2.toString(), ZERO.toString());
	}//testIsZero
/**
 * Check manual case for polynom equals
 */
	@Test
	public void testEqualsObject() {
		ACTUAL=new Polynom("3x^2+2x^2+2x+3x+4+1");
		EXPECTED=new Polynom("5x^2+5x+5");
		assertEquals("ERR: the expected polynom is: "+EXPECTED.toString()+" We got: "+ACTUAL.toString(), true, ACTUAL.toString().equals(EXPECTED.toString()));
	}//Equals
	@Test
	public void initFromStringTest() {
		String s="1.4x^4+8.5x^2-9.3";
        Polynom p=new Polynom();
        function f=p.initFromString(s);
		assertEquals("ERR: failing to create polynom initFromString", s, f.toString());
	}//initFromString 	
	
}
