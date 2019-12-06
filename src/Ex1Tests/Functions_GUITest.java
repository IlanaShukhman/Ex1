package Ex1Tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import Ex1.ComplexFunction;
import Ex1.Functions_GUI;
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
//	@Test
	void testToArrayTArray() {
		
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
		Collection<function> c=new ArrayList<function>();
		Functions_GUI f=new Functions_GUI(arr);
		e=c.add(f1);
		e=c.add(f2);
		e=f.containsAll(c);
		assertTrue(e);
		Functions_GUI g=new Functions_GUI(arr);
		e=f.containsAll(g);
		assertTrue(e);
		
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
		Collection<function> c=new ArrayList<function>();
		Functions_GUI f=new Functions_GUI(arr);
		Functions_GUI g=new Functions_GUI(arr2);
		e=c.add(f1);
		e=f.retainAll(c);
		e=f.containsAll(c);
		assertTrue(e);
		
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

//	@Test
	void testInitFromFile() {
		fail("Not yet implemented");
	}

//	@Test
	void testSaveToFile() {
		fail("Not yet implemented");
	}

//	@Test
	void testDrawFunctionsIntIntRangeRangeInt() {
		fail("Not yet implemented");
	}

//	@Test
	void testDrawFunctionsString() {
		fail("Not yet implemented");
	}

}
