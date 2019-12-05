package Ex1Tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import Ex1.ComplexFunction;
import Ex1.Functions_GUI;
import Ex1.function;

class Functions_GUITest {
	
	private function f1=new ComplexFunction("plus(x^2,x)");
	private function f2=new ComplexFunction("mul(3x^5, 5)");
	private ArrayList<function> arr=new ArrayList<>();
	{
		arr.add(f1);
		arr.add(f2);
	}
	
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
		f.add(f1);
		f.add(f2);
		Object[] Actual = f.toArray();
		Object[] Expected=new function[f.size()];
		Expected[0]=f1;
		Expected[1]=f2;
		boolean e=Arrays.equals(Actual, Expected);
		assertTrue(e);
	}

//	@Test
	void testToArrayTArray() {
		
	}

	//@Test
	void testAdd() {
		ArrayList<function> arr = new ArrayList<>();
		arr.add(f1);
		arr.add(f2);
		Functions_GUI f=new Functions_GUI(arr);
		
		Functions_GUI g=new Functions_GUI();
		g.add(f1);
		g.add(f2);
		assertEquals(f,g,"Err: TestAdd failed, expacted: "+f+", Got: "+g);
	}

	@Test
	void testRemove() {
		Functions_GUI f=new Functions_GUI();
		f.add(f1);
		f.add(f2);
	}

//	@Test
	void testContainsAll() {
		fail("Not yet implemented");
	}

//	@Test
	void testAddAll() {
		fail("Not yet implemented");
	}

//	@Test
	void testRemoveAll() {
		fail("Not yet implemented");
	}

//	@Test
	void testRetainAll() {
		fail("Not yet implemented");
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
		assertEquals(f,g);
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
