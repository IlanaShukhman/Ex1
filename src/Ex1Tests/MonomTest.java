package Ex1Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.MyRandom;
import Ex1.Monom;

class MonomTest {
	private final Double EPS=0.000001;
	private final Integer STEPS=100;
	private final long SEED=1234; 
	private final Integer MIN=-9999;
	private final Integer MAX=9999;
	private final Monom ZERO=new Monom();
	private final Monom ONE=new Monom(1,0);
	private final Monom MINUS=new Monom(-1,0);
	private final MyRandom rand=new MyRandom(SEED);
	private Monom ACTUAL;
	private Monom EXPECTED;
	/**
	 * Checking:
	 * If creation empty monom get zero monom
	 */
	@Test
	void testMonom() {
		ACTUAL=new Monom();
		EXPECTED=new Monom(ZERO);
		assertEquals(EXPECTED, ACTUAL, "ERR: failing to create zero monom by empty constructor.");
	}//testMonom
/**
 * Checking:
 * Builder constructor with (double,int) input.
 * 1) Creating zero monom: monom(0,num)=0
 * 2) Creating one monom: monom(1,0)=1
 * 3) Creating regular monom: monom(2,5)=2x^5
 */
	@Test
	void testMonomDoubleInt() {
		EXPECTED=new Monom();
		Integer pow=rand.randomInt(0, MAX);
		ACTUAL=new Monom(0,pow);
		assertEquals(EXPECTED, ACTUAL, "ERR: failing to create ZERO monom by Value constructor.");
		EXPECTED =new Monom(ONE);
		ACTUAL=new Monom(1,0);
		assertEquals(EXPECTED, ACTUAL, "ERR: failing to create ONE monom by Value constructor.");
		EXPECTED=new Monom("2x^5");
		Double coe=rand.random(MIN, MAX);
		ACTUAL=new Monom(2,5);
		assertEquals(EXPECTED, ACTUAL, "ERR: failing to create regular monom by Value constructor.");
	}
    /**
     * Checking:
     * Builder constructor with input monom.
     * 1) Creating zero monom: monom(ZERO)=0
     * 2) Creating one monom: monom(ONE)=1
     * 3) Creating regular monom: monom(m)=m
     */
	@Test
	void testMonomMonom() {
		EXPECTED=new Monom();
		ACTUAL=new Monom(ZERO);
		assertEquals(EXPECTED, ACTUAL, "ERR: failing to create ZERO monom by Monom constructor.");
		EXPECTED =new Monom("1");
		ACTUAL=new Monom(ONE);
		assertEquals(EXPECTED, ACTUAL, "ERR: failing to create ONE monom by Monom constructor.");
		EXPECTED=new Monom("2x^5");
		ACTUAL=new Monom(EXPECTED);
		assertEquals(EXPECTED, ACTUAL, "ERR: failing to create regular monom by Monom constructor.");
	}
    /**
     * Checking manually if two Monom are equals
     */
	@Test
	void testEqualsObject() {
		EXPECTED=new Monom("3x^2");
		ACTUAL=new Monom(3,2);
		assertEquals(EXPECTED, ACTUAL, "ERR: failing to equal two monoms.");
	}//testEqualsObject

	@Test
	void testGetComp() {
		
	}//testGetComp
    /**
     * Checking manually if equal monoms return the same power
     */
	@Test
	void testGet_power() {
		Double coe=rand.random(MIN, MAX);
		Integer pow=rand.randomInt(0, MAX);
		EXPECTED=new Monom(coe,pow);
		ACTUAL=new Monom(EXPECTED);
		assertEquals(EXPECTED.get_power(),ACTUAL.get_power(), "ERR: failing to equal two monoms.");
	}//testGet_power
    /**
     * Derivative manual check:
     * 1) (num)'=0
     * 2) (7x^3)'=21x^2
     */
	@Test
	void testDerivative() {
		EXPECTED=new Monom(ZERO);
		Double coe=rand.random(MIN, MAX);
		Monom m=new Monom(coe,0);
		ACTUAL=new Monom(m.derivative());
		assertEquals(EXPECTED,ACTUAL, "ERR: number monom derivation failing. We expect to get: "+EXPECTED.toString()+" but we got: "+ACTUAL.toString());
		EXPECTED=new Monom("21x^2");
		m=new Monom("7x^3");
		ACTUAL=m.derivative();
		assertEquals(EXPECTED,ACTUAL, "ERR: regular monom derivation failing. We expect to get: "+EXPECTED.toString()+" but we got: "+ACTUAL.toString());
	}//testDerivative
    /**
     * Calculator function testing.
     * 1) For all input x: number monom = number monom Example: 3=3 or 8=8
     * 2) Trying regular input: Assum that x=2 and f(x)=4x^5 Then f(2)=4*2^5=128
     */
	@Test
	void testF() {
		double x=rand.random(MIN, MAX);
		Double coe=rand.random(MIN, MAX);
		ACTUAL=new Monom(coe,0);		
		assertEquals(ACTUAL.get_coefficient(),ACTUAL.f(x), "ERR: regular monom derivation failing. We expect to get: "+ACTUAL.get_coefficient()+" but we got: "+ACTUAL.f(x));	
		Integer pow=rand.randomInt(0, MAX);
		ACTUAL=new Monom(coe,pow);
		Double actual=ACTUAL.f(x);
		Double expected=coe*Math.pow(x, pow);
		assertEquals(expected, actual, "ERR: failing to calculate f(x). We expected to get: "+expected+" but we got: "+actual);
	}//testF
    /**
     * Checking if monom is zero by:
     * monom constructors such as: by giving monom ,string ,empty.
     */
	@Test
	void testIsZero() {
		ACTUAL=new Monom();
		assertEquals(true, ACTUAL.isZero(), "ERR: failing to recognize if monom is zero - empty constructor case");
		ACTUAL=new Monom(ZERO);
		assertEquals(true, ACTUAL.isZero(), "ERR: failing to recognize if monom is zero - Monom constructor case");
		ACTUAL=new Monom("0");
		assertEquals(true, ACTUAL.isZero(), "ERR: failing to recognize if monom is zero - String constructor case");
	}//testIsZero
    /**
     * Comparing two different constructor.
     */
	@Test
	void testMonomString() {
		ACTUAL=new Monom("4x^6");
		EXPECTED=new Monom(4,6);
		assertEquals(EXPECTED, ACTUAL, "ERR: failing to create monom by string. We expected to get: "+EXPECTED+" but we got: "+ACTUAL);
	}//testMonomString
    /**
     * Checking monom adding by the following cases:
     * 1) monom + 0 = monom : 3x^6+0=3x^6
     * 2) 0 + monom = monom
     * 3) a1x^b + a2x^b = (a1+a2)x^b
     * 4) a1x^b1 + a2x^b2= ERR when b1 != b2
     */
	@Test
	void testAdd() {
		Double coe=rand.random(MIN, MAX);
		Integer pow=rand.randomInt(0, MAX);
		ACTUAL=new Monom(coe,pow);
		EXPECTED=new Monom(ACTUAL);
		ACTUAL.add(ZERO);
		assertEquals(EXPECTED, ACTUAL, "ERR: failing to adding monom by zero");
		ACTUAL=new Monom();
		ACTUAL.add(EXPECTED);
		assertEquals(EXPECTED, ACTUAL, "ERR: failing to adding zero monom by non zero monom");
		Double coe2=rand.random(MIN, MAX);
		Monom m=new Monom(coe,pow);
		ACTUAL=new Monom(coe2,pow);
		ACTUAL.add(m);
		EXPECTED=new Monom(coe+coe2,pow);
		assertEquals(EXPECTED, ACTUAL, "ERR: failing to adding two monoms with the same power");
		boolean flage=true;
		try {
			ACTUAL=new Monom(coe,pow);
			m=new Monom(coe2, pow+2);
			ACTUAL.add(m);	
		} catch (Exception e) {
			flage=false;
		}//catch
		assertEquals(false, flage, "ERR: by monoms rules you can't add two different monoms with different power otherwise is polynom");
	}//testAdd
/**
 * Checking multiplying monoms by the following cases:
 * 1) Multiplying monom by zero
 * 2) Multiplying zero monom by non zero monom
 * 3) Multiplying monom by one
 * 4) Multiplying one monom by monom
 * 5) Multiplying monom by monom
 */
	@Test
	void testMultipy() {
		Double coe=rand.random(MIN, MAX);
		Integer pow=rand.randomInt(0, MAX);
		ACTUAL=new Monom(coe,pow);
		ACTUAL.multipy(ZERO);
		EXPECTED=new Monom();
		assertEquals(EXPECTED, ACTUAL, "ERR: failing to Multiplying monom by zero");
		Monom m=new Monom(coe, pow);
		ACTUAL.multipy(m);
		assertEquals(EXPECTED, ACTUAL, "ERR: failing to Multiplying zero monom by non zero monom");
		ACTUAL=new Monom(coe, pow);
		EXPECTED=new Monom(ACTUAL);
		ACTUAL.multipy(ONE);
		assertEquals(EXPECTED, ACTUAL, "ERR: failing to Multiplying monom by one");
		ACTUAL=new Monom(ONE);
		EXPECTED=new Monom(coe, pow);
		ACTUAL.multipy(EXPECTED);
		assertEquals(EXPECTED, ACTUAL, "ERR: failing to Multiplying one monom by monom");
		Double coe2=rand.random(MIN, MAX);
		Integer pow2=rand.randomInt(0, MAX);
		ACTUAL=new Monom(coe, pow);
		m=new Monom(coe2, pow2);
		ACTUAL.multipy(m);
		EXPECTED=new Monom(coe*coe2, pow+pow2);
		assertEquals(EXPECTED, ACTUAL, "ERR: failing to Multiplying monom by monom");
	}//testMultipy
    /**
     * Comparing toString by two different constructors
     * We expect to get the same output
     */
	@Test
	void testToString() {
		EXPECTED=new Monom("4x^5");
		ACTUAL=new Monom(4,5);
		String expected=EXPECTED.toString();
		String actual=ACTUAL.toString();
		assertEquals(true, actual.equals(expected),"ERR: failing toString from Value constructor");
		EXPECTED=new Monom("3x^2");
		ACTUAL=new Monom(EXPECTED);
		expected=EXPECTED.toString();
		actual=ACTUAL.toString();
		assertEquals(true, actual.equals(expected),"ERR: failing toString from Monom constructor");	
	}//testToString
    /**
     * Checking bad monoms exception and good monoms exception
     */
	@Test
	void testIfMonom() {
		String[] good = {"+2","2","-2","-x","+x","-3.2X^2","-0","-X^2","+0","0x","x^0","0x^1","1x","-1x"};	
		for (int i = 0; i < good.length; i++) {
			boolean flage=true;
			try {
				Monom m=new Monom(good[i]);
			}//try 
			catch (Exception e) {
			   flage=false;
			}//catch
			assertEquals(true, flage,good[i]+" suppose to be true monom");
		}//for
		String[] bad = {"","2*4","2x^-1","x^2+x","x+3x","5x-2x","3x/4","x-","x+","xx","--x","++x","3x*3x","y"};
		for (int i = 0; i < bad.length; i++) 
		{
			boolean flage=false;
			try {
				Monom m=new Monom(bad[i]);
			} catch (Exception e) {
			   flage=true;
			}//catch
			assertEquals(true, flage,"Wrong "+bad[i]+" Event though it isn't monom you accept him");
		}//for
	}//testIfMonom
	  /**
     * Checking manually if equal monoms return the same coefficient
     */
	@Test
	void testGet_coefficient() {
		Double coe=rand.random(MIN, MAX);
		Integer pow=rand.randomInt(0, MAX);
		EXPECTED=new Monom(coe,pow);
		ACTUAL=new Monom(EXPECTED);
		assertEquals(EXPECTED.get_coefficient(),ACTUAL.get_coefficient(), "ERR: failing to equal two monoms.");
	}

}
