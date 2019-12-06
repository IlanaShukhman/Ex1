package Ex1;

import java.awt.Color;
import java.awt.Font;
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
		this.func_collection =new ArrayList<>(func_collection);
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
		if(o instanceof function) {
			if(this.func_collection.contains(o))
				return true;
		}//contains
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
		Object[] obj = new function[this.func_collection.size()];
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
		T[] t = (T[]) new function[this.func_collection.size()];
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
		if(!this.contains(o))
			return false;
		this.func_collection.remove(o);
		return true;
	}//remove

	/**
	 * Returns true if this collection contains all of the elements in the specified collection.
	 */
	public boolean containsAll(Collection<?> c) {
		if(c instanceof function) {
			if(this.func_collection.containsAll(c)) {
				return true;
			}//if
		}//if
		return false;
	}
	/**
	 * Adds all of the elements in the specified collection to this collection (optional operation).
	 */
	@Override
	public boolean addAll(Collection<? extends function> c) {
		return this.func_collection.addAll(c);
	}
	/**
	 * Removes all of this collection's elements that are also contained in the specified collection (optional operation).
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		if(!this.containsAll(c))
			return false;
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

	public boolean equals(Object f) {
		if(f instanceof Functions_GUI)
		{
			if(this.size()==0 && (((Functions_GUI) f).size()==0))
				return true;

			else if(this.func_collection.containsAll((Collection<?>) f) && this.size()==((Functions_GUI) f).size()) {
				return true;
			}//else if

		}//if
		return false;
	}//equals

	public String toString() {
		String str="";
		for (int i = 0; i < this.func_collection.size(); i++) {
			str+=this.func_collection.get(i).toString()+", ";
		}
		if(str.length()>2)
			str=str.substring(0,str.length()-2);
		return str;
	}

	public void print() {
		for (int i = 0; i < this.size(); i++) {
			System.out.println(i+") "+   this.func_collection.get(i));
		}
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
		// number of line segments to plot
		int n=resolution;
		int range=(int) (rx.get_max()-rx.get_min());
		double maxY = ry.get_max(), minY = ry.get_min();
		int j=0;
		Iterator itr= this.iterator();
		while(itr.hasNext()) {
			
			function f= this.func_collection.get(j);
			
			double[] x = new double[(int) (n+1)];
			double[] y = new double[(int) (n+1)];

			for (int i = 0; i <= n; i++) {
				x[i] = range/n;
				y[i] = f.f(x[i]);
			}		
			// rescale the coordinate system
			StdDraw.setXscale(0, Math.PI);
			StdDraw.setYscale(minY, maxY);

			//////// vertical lines
			StdDraw.setPenColor(Color.LIGHT_GRAY);
			for (int i = 0; i <= n; i=i+10) {
				StdDraw.line(x[i], minY, x[i], maxY);
			}
			//////// horizontal  lines
			for (double i = minY; i <= maxY; i=i+0.5) {
				StdDraw.line(0, i, Math.PI, i);
			}
			//////// x axis		
			StdDraw.setPenColor(Color.BLACK);
			StdDraw.setPenRadius(0.005);
			StdDraw.line(0, y[(int) (n/2)], Math.PI, y[(n/2)]);
			StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 15));
			for (int i = 0; i <= n; i=i+10) {
				StdDraw.text(x[i]-0.07, -0.07, Integer.toString((i-n/2)));
			}
			//////// y axis	
			StdDraw.line(x[(n/2)], minY, x[(n/2)], maxY);
			for (double i = minY; i <= maxY; i=i+0.5) {
				StdDraw.text(x[(n/2)]-0.07, i+0.07, Double.toString(i));
			}

			// plot the approximation to the function
			for (int i = 0; i < n; i++) {
				StdDraw.line(x[i], y[i], x[i+1], y[i+1]);
			}
			StdDraw.setPenColor(Color.RED);
			StdDraw.setPenRadius(0.01);
			StdDraw.point(x[(n/2)], 1);

		}
	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub

	}

}
