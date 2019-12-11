package Ex1Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Ex1.ComplexFunction;
import Ex1.Functions_GUI;
import Ex1.Monom;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.Range;
import Ex1.function;


class Functions_GUITest {
	
	private function f1=new ComplexFunction("plus(x^2,x)");
	private function f2=new ComplexFunction("mul(3x^5, 5)");
	private function f3=new ComplexFunction("min(div(x^3,x-1),x)");
	private function f4=new ComplexFunction("max(mul(x^2,x),plus(x+2,2))");
	private ArrayList<function> arr=new ArrayList<>();
	{
		arr.add(f1);
		arr.add(f2);
	}
	private ArrayList<function> arr2=new ArrayList<>();
	{
		arr2.add(f3);
		arr2.add(f4);
	}
	boolean e;
	
	@Test
	void testFunction_Gui() {
		Functions_GUI f= new Functions_GUI();
		ArrayList<function> Expected=new ArrayList<>();
		assertEquals(f.get_func_collection(),Expected,"Err");
	}

	@Test
	void testFunctions_GUIArrayListOffunction() { 
		Functions_GUI f=new Functions_GUI(arr);
		assertEquals(f.get_func_collection(),arr);		
	}

	@Test
	void testSize() {
		Functions_GUI f=new Functions_GUI(arr);
		assertEquals(2, f.size(), "Err: Expacted 2, but got:"+f.size());
		Functions_GUI g=new Functions_GUI();
		assertEquals(0, g.size(), "Err: Expected 0, but got:"+g.size());
	}

	@Test
	void testIsEmpty() {
		Functions_GUI f=new Functions_GUI();
		assertTrue(f.isEmpty(), "Err: TestIsEmpty failed to return true when f is empty.");
		f.add(new ComplexFunction("plus(x^2,x)"));
		assertFalse(f.isEmpty(), "ERR: TestIsEmpty failed to return false when f is not empty.");
	}

	@Test
	void testContains() {
		Object obj=f1;
		Functions_GUI f=new Functions_GUI(arr);
		assertTrue(f.contains(obj),"Err: TestContains failed to return true when f contains the object.");
	}

	@Test
	void testToArray() {
		Functions_GUI f=new Functions_GUI();
		e=f.add(f1);
		e=f.add(f2);
		Object[] Actual = f.toArray();
		Object[] Expected=new function[f.size()];
		Expected[0]=f1;
		Expected[1]=f2;
		e=Arrays.equals(Actual, Expected);
		assertTrue(e);
	}
//I don't know understand how to use T[]
	@Test
	void testToArrayTArray() {
		Functions_GUI f=new Functions_GUI();
		f.add(f1);
		f.add(f2);
		
	}

	@Test
	void testAdd() {
		Functions_GUI f=new Functions_GUI(arr);
		Functions_GUI g=new Functions_GUI();
		e=g.add(f1);
		e=g.add(f2);
		e=f.equals(g);
		assertTrue(e, "ERR: testAdd failed to return true when f and g are the same. Got: "+f +" , "+g);
		//assertEquals(f,g,"Err: TestAdd failed, expacted: "+f+", Got: "+g);
	}

	@Test
	void testRemove() {
		Functions_GUI f=new Functions_GUI();
		Functions_GUI g=new Functions_GUI();
		e=f.add(f1);
		e=f.add(f2);
		e=f.remove(f1);
		e=g.add(f2);
		assertTrue(f.equals(g));
		e=g.remove(f1);
		assertFalse(e, "ERR: testRemove didn't return false when tried to remove an object that doesn't exist in it");
		e=f.remove(f2);
		e=g.remove(f2);
		e=f.equals(g);
		assertTrue(e);
		//assertEquals(f, g, "ERR: testRemove failed to return true when f and g are the same. Got: "+f +" , "+g);
	}

	@Test
	void testContainsAll() {
		ArrayList<function> c=new ArrayList<function>();
		Functions_GUI f=new Functions_GUI(arr);
		e=c.add(f1);
		e=c.add(f2);
		assertTrue(f.containsAll(c));
		
	}

	@Test
	void testAddAll() {
		Collection<function> c=new ArrayList<function>();
		Functions_GUI f=new Functions_GUI(arr);
		e=c.add(f3);
		e=c.add(f4);
		e=f.addAll(c);
		Functions_GUI g=new Functions_GUI(arr);
		e=g.add(f3);
		e=g.add(f4);
		e=f.containsAll(g);
		assertTrue(e);
		
	}

	//I don't know what is the problem
	@Test
	void testRemoveAll() {
		Collection<function> c=new ArrayList<function>();
		Functions_GUI f=new Functions_GUI(arr);
		e=c.add(f2);
		e=c.add(f1);
		e=f.add(f3);
		e=f.add(f4);
		e=f.removeAll(c);
		Functions_GUI g=new Functions_GUI(arr2);
		e=f.containsAll(g);
		assertTrue(e);
	}

	@Test
	void testRetainAll() {
		Collection<function> c=new ArrayList<function>();//empty
		Functions_GUI f=new Functions_GUI();
		Functions_GUI g=new Functions_GUI();
		f.add(f1);
		f.add(f2);
		g.add(f2);
		g.add(f3);
		e=c.add(f2);//Only f3
		e=f.retainAll(g.get_func_collection());//f is f3
		//System.out.println(f.toString());
		assertEquals(f.get_func_collection(),c);
		
	}

	@Test
	void testClear() {
		Functions_GUI f=new Functions_GUI();
		function f1=new ComplexFunction("plus(x^2,x)");
		function f2=new ComplexFunction("mul(3x^5,5)");
		f.add(f1);
		f.add(f2);
		f.clear();
		Functions_GUI g=new Functions_GUI();
		assertEquals(f, g, "ERR: trying to Clear collection. We expected to: "+f.toString()+" but we got: "+g.toString());
	}

	@Test
	void testInitFromFile() throws IOException {
		Functions_GUI f=new Functions_GUI(arr);
		Functions_GUI g=new Functions_GUI();
		String file="functions.txt";
		f.saveToFile(file);
		g.initFromFile(file);
		assertEquals(f, g, "ERR: trying to saveToFile. We expected to: "+f.toString()+" but we got: "+g.toString());
		g.initFromFile("wrongformat.txt");
	}

	@Test
	void testSaveToFile() throws IOException {
		Functions_GUI f=new Functions_GUI(arr);
		Functions_GUI g=new Functions_GUI();
		String file="functions.txt";
		f.saveToFile(file);
		g.initFromFile(file);
		assertEquals(f, g, "ERR: trying to saveToFile. We expected to: "+f.toString()+" but we got: "+g.toString());
		
	}

	@Test
	void testDrawFunctionsIntIntRangeRangeInt() {
		Functions_GUI data = FunctionsFactory();
		int w=1000, h=600, res=200;
		Range rx = new Range(-10,10);
		Range ry = new Range(-5,15);
		data.drawFunctions(w,h,rx,ry,res);
	}

	//@Test
	void testDrawFunctionsString() {
		String file="GUI_params.json";
		Functions_GUI g = new Functions_GUI(arr);
		g.add(f3);
		g.add(f4);
		g.drawFunctions(file);
		
		
	}//testDrawFunctionsString
	
	public static Functions_GUI FunctionsFactory() {
		Functions_GUI ans = new Functions_GUI();
		String s1 = "3.1 +2.4x^2 -x^4";
		String s2 = "5 +2x -3.3x +0.1x^5";
		String[] s3 = {"x +3","x -2", "x -4"};
		function p1 = new Polynom(s1);
		function p2 = new Polynom(s2);
		function p3 = new Polynom(s3[0]);
		ComplexFunction cf3 = new ComplexFunction(p3.toString());
		for(int i=1;i<s3.length;i++) {
			cf3.mul(new Polynom(s3[i]));
		}//for
		
		ComplexFunction cf = new ComplexFunction(Operation.Plus, p1,p2);
		ComplexFunction cf4 = new ComplexFunction(Operation.Divid, new Polynom("x +1"),cf3);
		cf4.plus(new Monom("2"));
		ans.add(cf.copy());
		ans.add(cf4.copy());
		cf.div(p1);
		ans.add(cf.copy());
		String s = cf.toString();
		function cf5 = cf4.initFromString(s1);
		function cf6 = cf4.initFromString(s2);
		ans.add(cf5.copy());
		ans.add(cf6.copy());
		Iterator<function> iter = ans.iterator();
		function f = iter.next();
		ComplexFunction max = new ComplexFunction(f);
		ComplexFunction min = new ComplexFunction(f);
		while(iter.hasNext()) {
			f = iter.next();
			max.max(f);
			min.min(f);
		}
		ans.add(max);
		ans.add(min);		
		return ans;
	}//FunctionsFactory
}
