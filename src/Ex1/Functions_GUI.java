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
	 * Getters.
	 * @return func_collenction 
	 */
	public ArrayList<function> get_func_collection(){
		return this.func_collection;
	}
    /**
     * Returns the number of elements in this collection.
     */
	public int size() {
		return this.func_collection.size();
	}
	
    /**
     * Returns true if this collection contains no elements.
     */
	public boolean isEmpty() {
		if(this.func_collection.isEmpty()) {
			return true;
		}
		return false;
	}
	
    /**
     * Returns true if this collection contains the specified element. 
     * More formally, 
     * returns true if and only if this collection contains at least one element e such that: 
     * (o==null ? e==null : o.equals(e)).
     */
	public boolean contains(Object o) {
		if(this.func_collection.contains(o))
			return true;
		return false;
	}
    /**
     * Returns an iterator over the elements in this collection.
     */
	public Iterator<function> iterator() {
		Iterator<function> itr=func_collection.iterator();
		return itr;
	}
	
    /**
     * Returns an array containing all of the elements in this collection.
     */
	public Object[] toArray() {
		Object[] obj = new Object[this.func_collection.size()];
		for (int i = 0; i < obj.length; i++) {
			obj[i]=this.func_collection.get(i);
		}
		return obj;
	}
	
    /**
     * Returns an array containing all of the elements in this collection; 
     * the runtime type of the returned array is that of the specified array.
     */
	public <T> T[] toArray(T[] a) {
		T[] t = (T[]) new Object[this.func_collection.size()];
		for (int i = 0; i < t.length; i++) {
			t[i]=(T) this.func_collection.get(i);
		}
		return t;
	}

	public boolean add(function e) {
		this.func_collection.add(e);
		return true;
	}
    
	
	public boolean remove(Object o) {
		this.func_collection.remove(o);
		return true;
	}

	/**
	 * Returns true if this collection contains all of the elements in the specified collection.
	 */
	public boolean containsAll(Collection<?> c) {
		if(this.func_collection.containsAll(c)) {
			return true;
		}
		return false;
	}
    /**
     * Adds all of the elements in the specified collection to this collection (optional operation).
     */
	@Override
	public boolean addAll(Collection<? extends function> c) {
		this.func_collection.addAll(c);
		return true;
	}
    /**
     * Removes all of this collection's elements that are also contained in the specified collection (optional operation).
     */
	@Override
	public boolean removeAll(Collection<?> c) {
		this.func_collection.removeAll(c);
		return true;
	}
    /**
     * Retains only the elements in this collection that are contained in the specified collection (optional operation).
     */
	@Override
	public boolean retainAll(Collection<?> c) {
		this.func_collection.retainAll(c);
		return true;
	}
    /**
     * Removes all of the elements from this collection (optional operation).
     */
	public void clear() {
		this.func_collection.clear();
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
