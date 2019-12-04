package Ex1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Functions_GUI implements functions {
	private ArrayList<function> func_collection;


	public Functions_GUI() {
		super();
		this.func_collection = new ArrayList<function>();
	}//Functions_GUI
	public Functions_GUI(ArrayList<function> func_collection) {
		super();
		this.func_collection = func_collection;
	}//Functions_GUI
    /**
     * Returns the number of elements in this collection.
     */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
    /**
     * Returns true if this collection contains no elements.
     */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
    /**
     * Returns true if this collection contains the specified element. 
     * More formally, 
     * returns true if and only if this collection contains at least one element e such that: 
     * (o==null ? e==null : o.equals(e)).
     */
	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
    /**
     * Returns an iterator over the elements in this collection.
     */
	@Override
	public Iterator<function> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
    /**
     * Returns an array containing all of the elements in this collection.
     */
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}
    /**
     * Returns an array containing all of the elements in this collection; 
     * the runtime type of the returned array is that of the specified array.
     */
	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(function e) {
		// TODO Auto-generated method stub
		return false;
	}
    
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Returns true if this collection contains all of the elements in the specified collection.
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
    /**
     * Adds all of the elements in the specified collection to this collection (optional operation).
     */
	@Override
	public boolean addAll(Collection<? extends function> c) {
		// TODO Auto-generated method stub
		return false;
	}
    /**
     * Removes all of this collection's elements that are also contained in the specified collection (optional operation).
     */
	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
    /**
     * Retains only the elements in this collection that are contained in the specified collection (optional operation).
     */
	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
    /**
     * Removes all of the elements from this collection (optional operation).
     */
	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initFromFile(String file) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveToFile(String file) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub

	}

}
