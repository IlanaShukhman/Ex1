package Ex1Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Functions_GUI;
import Ex1.Operation;
import Ex1.function;

class Functions_GUITest {
	
	@Test
	void testFunction_Gui() {
		Functions_GUI f= new Functions_GUI();
		ArrayList<function> Expected=new ArrayList<>();
		assertEquals(f.get_func_collection(),Expected,"Err");
	}

	@Test
	void testFunctions_GUIArrayListOffunction() { 
		function f1 = new ComplexFunction("plus(x^2,x)");
		function f2 = new ComplexFunction("mul(3x^5, 5)");
		ArrayList<function> arr = new ArrayList<>();
		arr.add(f1);
		arr.add(f2);
		Functions_GUI f=new Functions_GUI(arr);
		assertEquals(f.get_func_collection(),arr);		
	}

	@Test
	void testSize() {
		function f1 = new ComplexFunction("plus(x^2,x)");
		function f2 = new ComplexFunction("mul(3x^5, 5)");
		ArrayList<function> arr = new ArrayList<>();
		arr.add(f1);
		arr.add(f2);
		Functions_GUI f=new Functions_GUI(arr);
		assertEquals(f.size(),2);
		Functions_GUI g=new Functions_GUI();
		assertEquals(f.size(),0);
	}

	//NOT WORKING!
	@Test
	void testIsEmpty() {
		Functions_GUI f=new Functions_GUI();
		assertTrue(f.isEmpty(), "Err: TestIsEmpty failed to return true when f is empty.");
		f.add(new ComplexFunction("plus(x^2,x"));
		assertFalse(f.isEmpty(), "ERR: TestIsEmpty failed to return false when f is not empty.");
	}

	@Test
	void testContains() {
		Object obj=new ComplexFunction("plus(x^2,x)");
		function f1 = new ComplexFunction("plus(x^2,x)");
		function f2 = new ComplexFunction("mul(3x^5, 5)");
		ArrayList<function> arr = new ArrayList<>();
		arr.add(f1);
		arr.add(f2);
		Functions_GUI f=new Functions_GUI(arr);
		assertTrue(f.contains(obj),"Err: TestContains failed to return true when f contains the object.");
	}

	@Test
	void testIterator() {
		fail("Not yet implemented");
	}

	//@Test
	void testToArray() {
		fail("Not yet implemented");
	}

//	@Test
	void testToArrayTArray() {
		fail("Not yet implemented");
	}

//	@Test
	void testAdd() {
		fail("Not yet implemented");
	}

//	@Test
	void testRemove() {
		fail("Not yet implemented");
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

//	@Test
	void testClear() {
		fail("Not yet implemented");
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
