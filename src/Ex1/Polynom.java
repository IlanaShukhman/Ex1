package Ex1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Michael Perry
 *
 */
public class Polynom implements Polynom_able{
	private ArrayList<Monom> poly;
	private ArrayList<Integer> pow;

	//Constructors:
	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {
		initializing();
	}

	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x"};
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) {
		if(s.isEmpty())
			throw new RuntimeException("ERR: your string can't be empty"); 
		initializing();
		s=s.toLowerCase();
		ArrayList<String> monoms=partStrPoly(s);
		Iterator<String> it=monoms.iterator();
		while(it.hasNext())
		{
			String z=it.next();
			//System.out.println("The partition monom: "+z);
			Monom m=new Monom(z);
			add(m);
		}//while
	}//Polynom

	/**
	 * Easy calculation of the whole function
	 */
	public double f(double x) {
		double sum=0;
		Iterator<Monom> it=iteretor();
		while(it.hasNext())
		{
			Monom m=it.next();
			sum+=m.f(x);
		}//while
		return sum;
	}//f(x)

	/**
	 * Adding our polynom by another polynom
	 */
	public void add(Polynom_able p1) {
		Iterator<Monom> it=p1.iteretor();
		while(it.hasNext())
		{
			Monom m=new Monom(it.next());
			add(m);
		}//while
	}//add

	/**
	 * Adding by place 
	 * If monom is zero: do not do nothing
	 */
	public void add(Monom m1) {

		if(!m1.isZero()) {
			if(isZero()) {
				this.pow.remove(0);
				this.poly.remove(0);}
			int power=m1.get_power();
			if(this.pow.contains(power))//If there is such a monom with the same power
			{
				int index=pow.indexOf(power);				
				this.poly.get(index).add(m1);//Find the index of the existing monom
				if(this.poly.get(index).isZero() && this.poly.size()>=1)//Adding two monoms with opposite operation
				{
					this.pow.remove(index);
					this.poly.remove(index);			    		
					this.poly.sort(m1._Comp);
					this.pow.sort(null);
					if(this.poly.isEmpty()) setZero();
				}//if 			     
			}//if
			else //Adding new monom with new power 
			{
				this.pow.add(power);
				this.pow.sort(null);
				this.poly.add(m1);
				this.poly.sort(m1._Comp);
			}//else
		}//if
	}//add		
	/**
	 * Let p1,p2 polynomial structures.
	 * to substract p1 from p2 we will multiplying p2 with -1 and then add them together
	 */
	public void substract(Polynom_able p1) {

		if(equals(p1)) //If we substract the same polynoms 
			setZero();
		else {
			Iterator<Monom> it=p1.iteretor();			
			while(it.hasNext() )
			{				
				Monom m=new Monom(it.next());				
				substract(m);
			}//while			
		}//else
	}//substract

	/**
	 * This function implement basic arithmetic operation
	 * Substract polynom with single monom
	 */
	public void substract(Monom m) {	
		Monom minus=new Monom("-1");
		minus.multipy(m);			
		add(minus);
	}//substruct
	/**
	 * This function implement basic arithmetic operator.
	 * Product our class polynom and another polynom p1 by three steps:
	 * Step 1: create polynom arraylist to save the multiple monoms of our class polynom with inserted p1
	 * step 2: initialize poly to zero for step 3
	 * Step 3: sum poly with the polynoms in arraylist for combining the solution
	 */
	public void multiply(Polynom_able p1) {
		ArrayList<Polynom> p=new ArrayList<Polynom>();//Saving the new polynoms
		Iterator<Monom> it=iteretor();		
		while(it.hasNext())
		{
			Polynom sum=new Polynom(p1.toString());
			sum.multiply(it.next());
			p.add(sum);
		}//while		
		setZero();//Initializing the original polynom
		Iterator<Polynom> itP=p.iterator();
		while(itP.hasNext())
			add(itP.next());//Adding all polynoms to one
	}//multipy



	/**
	 * Compute a value x' (x0<=x'<=x1) for with |f(x')| < eps
	 * assuming (f(x0)*f(x1)<=0, else should throws runtimeException 
	 * computes f(x') such that:
	 * 	(i) x0<=x'<=x1 && 
	 * 	(ii) |f(x')|<eps
	 */

	public double root(double x0, double x1, double eps) {
		//System.out.println("f("+x0+")= "+f(x0)+" f("+x1+")= "+f(x1));
		if(x0>x1)
			throw new RuntimeException("ERR: NOT legit interval: "+x0+">"+x1); 

		if(f(x0)*f(x1)>0)
			throw new RuntimeException("ERR: Not following kushi rules because "+f(x0)+" * "+f(x1)+">0"); 
		else if(Math.abs(f(x0))<=eps) return x0;		
		else if (Math.abs(f(x1))<=eps) return x1;
		else if(f(x0)<0)
		{
			double c=((x0+x1)/2.0);
			if(f(c)<0)   return root(c, x1, eps);
			else  return root(x0, c, eps);
		}//else
		else // f(x0)>0
		{
			double c=((x0+x1)/2.0);
			if(f(c)<0)   return root(x0, c, eps);
			else  return root(c, x1, eps);
		}//else
	}//root


	public String toString()
	{
		String str="";
		if(this.poly.isEmpty())
			return "";
		str+=this.poly.get(this.poly.size()-1);
		for (int i = this.poly.size()-2; i >=0; i--) {
			if(this.poly.get(i).get_coefficient()>=0)
				str+="+"+this.poly.get(i);
			else
				str+=this.poly.get(i);
		}//for
		return str;
	}//toString
	/**
	 * Return new derivative polynom 
	 */
	public Polynom_able derivative() {
		Polynom_able p=new Polynom();
		Iterator<Monom> it=iteretor();
		while(it.hasNext()) {
			Monom before=it.next();
			Monom after=before.derivative();
			if(!after.isZero())
				p.add(after);
			else
				System.out.println(after.isZero());
		}//while
		return p;
	}//public

	/**
	 * Compute a Riman's integral from x0 to x1 in eps steps. 
	 */
	//limit
	public double area(double x0, double x1, double eps) {
		double sumArea=0;
		for (double x = x0+eps; x <=x1; x+=eps) 
		{
			if(isPositive(f(x)) && isPositive(f(x-eps)))
				sumArea+=(f(x)*eps); 
		}//for
		return sumArea;
	}//area


	/**
	 * This function implement basic arithmetic operation.
	 * There is three worst cases:
	 * Case 1:The monom is zero then all the polynom is clear
	 * Case 2:The monom power is zero so they still in the same place
	 * Case 3:The monom power is more than zero so the place is changed
	 */
	public void multiply(Monom m1) {
		if(m1.get_coefficient()==0)
			setZero();					
		else {
			for (int i = 0; i<this.poly.size(); i++) {
				if(m1.get_power()>=1)
				{  
					this.poly.get(i).multipy(m1);
					this.pow.set(i, this.poly.get(i).get_power());
				}//if
				else if(m1.get_power()==0) 
					this.poly.get(i).multipy(m1);
				else
					throw new RuntimeException("ERR: this is not polynom"); 	
			}//for		
		}//else			
	}//multiply
	/**
	 * Initiate the iterator for our polynom
	 */
	public Iterator<Monom> iteretor() {
		Iterator<Monom> it=poly.iterator();
		return it;
	}//iterator
	/**
	 * Using to string method to copy lightly
	 */
	public Polynom_able copy() {
		Polynom_able p=new Polynom(toString());
		return p;
	}//copy
	/**
	 * The purpose of this function is to simplify copy constructor from string to monoms
	 * Let a to be real number and b non zero natural zero.
	 * Init all the sub sets of Polynom from a simple polynom structure such as:
	 * {"a","ax","ax^b"}
	 */
	public ArrayList<String> partStrPoly(String poly)
	{
		poly.toLowerCase();
		ArrayList<String> s=new ArrayList<String>();
		String monomialFormat = "([+-]?[\\d\\.]*[a-zA-Z]?\\^?\\d*)", 
				monomialPartsFormat = "([+-]?[\\d\\.]*)([a-zA-Z]?)\\^?(\\d*)";

		Pattern p1 = Pattern.compile(monomialFormat);
		Matcher m1 = p1.matcher(poly);

		while (!m1.hitEnd()) {
			m1.find();
			Pattern p2 = Pattern.compile(monomialPartsFormat);		        
			s.add(m1.group());
			//System.out.println(m1.group());
		}//while
		return s;
	}//partStrPoly

	/**
	 * This function simplify common operation for developer understanding.
	 * Initializing Data Structure to zero.
	 */
	public void setZero()
	{

		this.poly.clear();
		this.pow.clear();
		Monom m=new Monom(0,0);
		this.poly.add(m);
		this.pow.add(0);
	}//setZeroPolynom
	/**
	 * This function allow the programmer getting status of the major use Data Structure
	 */
	public void status()
	{
		System.out.println("pow: "+this.pow.toString());
		System.out.println("poly: "+this.poly.toString());
	}//status

	//Booleans Functions:
	public boolean ifEvenFunc()
	{
		if(this.pow.size()==1 && this.pow.get(0)<2)
			return false;
		Iterator<Integer> it=this.pow.iterator();
		while(it.hasNext())
		{
			if(it.next()%2!=0)
				return false;
		}//while
		return true;
	}//ifEvenFunc
	public boolean ifOddFunc()
	{
		if(this.pow.size()==1 && this.pow.get(0)<1)
			return false;
		Iterator<Integer> it=this.pow.iterator();
		while(it.hasNext())
		{
			if(it.next()%2==0)
				return false;
		}//while
		return true;
	}//ifEvenFunc
	/**
	 * This function allow us to know if the calculation of the f(x) is positive 
	 */
	public boolean isPositive(double fx)
	{
		return fx>0?true:false;
	}//isPositive

	/**
	 * Check if all the coefficients are equal to zero
	 */
	public boolean isZero() {
		Iterator<Monom> it=iteretor();
		while(it.hasNext()) {
			Monom m=it.next();
			if(m.get_coefficient()==0)
				return true;
		}//while
		return false;
	}//iszero
	/**
	 * Use String method to check if the same
	 */
	@Override
	public boolean equals(function p1) {
		if(p1 instanceof Polynom) {
			Iterator<Monom> act=iteretor();
			Iterator<Monom> exp=((Polynom) p1).iteretor();
			while (exp.hasNext() && act.hasNext()) {
				Monom mExp = (Monom) exp.next();
				Monom mAct=(Monom) act.next();
				if(!mExp.equals(mAct))
					return false;
			}//while
			return true;
		}//if
		return false;
	}//equals

	/**
	 * Initializing our arraylists: pow and poly to zero.
	 */
	public void initializing()
	{
		this.poly=new ArrayList<Monom>();
		this.pow=new ArrayList<Integer>();
		Monom m=new Monom("0");
		this.poly.add(m);
		this.pow.add(0);
	}//initializing

	@Override
	public function initFromString(String s) {
		function f= new Polynom(s);
		return f;
	}
}//polynom
