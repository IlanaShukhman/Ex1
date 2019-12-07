package Ex1;


import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;





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

	public function get(int index) {
		return func_collection.get(index);
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
	//Deserialization
	@Override
	public void initFromFile(String file) throws IOException {
        String split = ",";
        String line="";
        try 
        {
        	BufferedReader br = new BufferedReader(new FileReader(file));
        	 while ((line = br.readLine()) != null) 
             {
        		 function f=new ComplexFunction(line);
        		 f.initFromString(line);
        		 add(f);
             }//while

        }//try
        catch (IOException e) 
        {
            e.printStackTrace();
            System.out.println("could not read file");
        }//catch

	}//initFromFile

	@Override
	public void saveToFile(String file) throws IOException {
		try {
			File f=new File(file);
			PrintWriter print=new PrintWriter(f);
			StringBuffer into=new StringBuffer();
			Iterator<function> it=iterator();
			int count=1;
			while(it.hasNext())
			{
				function g=it.next();
				into.append(g.toString()+"\n");
				count++;
			}//while
			print.write(into.toString());
			print.close();
		} catch (Exception e) {
			System.out.println("ERR: failing to save this list.");
		}
	}//SaveToFile


	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		Color[] color= {Color.blue,Color.cyan,Color.darkGray,Color.gray,Color.green,Color.magenta,Color.orange
				,Color.pink};
		
		int colorNum=0;


		Range Width=new Range(-width/2,width/2);
		Range Height=new Range(0-height/2,height/2);

		// number of line segments to plot
		int n=resolution;
		double range=rx.get_max()-rx.get_min();
		double stepX=range/n;

		double maxY = ry.get_max(), minY = ry.get_min();
		double maxX = rx.get_max(), minX = rx.get_min();


		// rescale the coordinate system
		StdDraw.setXscale(Width.get_min(), Width.get_max());
		StdDraw.setYscale(Height.get_min(), Height.get_max());

		//////// vertical lines
		StdDraw.setPenColor(Color.LIGHT_GRAY);
		for (int i = (int) Width.get_min(); i <= Width.get_max(); i=i+n/4){
			StdDraw.line(i, Height.get_min(), i, Height.get_max());
		}
		//////// horizontal  lines
		for (double i = Height.get_min(); i <= Height.get_max(); i=i+n/4) {
			StdDraw.line(Width.get_min(), i, Width.get_max(), i);
		}

		///////Draw x axis and y axis
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.line(Width.get_min(), 0, Width.get_max(), 0);
		StdDraw.line(0, Height.get_min(), 0, Height.get_max());
		StdDraw.setFont(new Font("TimesRoman", Font.PLAIN, 15));

		Iterator<function> itr= iterator();
		while(itr.hasNext()) {

			function f= itr.next();
			double[] x = new double[n+1];
			double[] y = new double[n+1];
			double[] w = new double[n+1];
			double[] h = new double[n+1];

			for (int i = 0; i <= n; i++) {
				x[i] = minX+i*stepX;
				try {
					y[i] = f.f(x[i]);
				}
				catch(Exception e) {

				}
				w[i] = (x[i]*Width.get_max())/rx.get_max();
				h[i] = (y[i]*Width.get_max())/ry.get_max();

			}


//
//			//////// x axis
//			for (int i = 0; i <= n; i=i+n) {
//				StdDraw.text(w[i]-0.07, -0.07, Integer.toString((int) (x[i]/2)));
//			}
//			//////// y axis
//			for (double i = Height.get_min(); i <= Height.get_max(); i=i+n) {
//				StdDraw.text(w[(n/2)]-0.07, i+0.07, Double.toString(i));
//			}

			StdDraw.setPenColor(color[colorNum]);
			// plot the approximation to the function
			for (int i = 0; i < n; i++) {
				StdDraw.line(w[i], h[i], w[i+1], h[i+1]);
			}
			colorNum++;
			if(colorNum==8)
				colorNum=0;

		}
	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub

	}
	public <T> ArrayList<T> getObjectList(String jsonString,Class<T> cls){
		ArrayList<T> list = new ArrayList<T>();
	    try {
	        Gson gson = new Gson();
	        JsonArray arry = new JsonParser().parse(jsonString).getAsJsonArray();
	        for (JsonElement jsonElement : arry) {
	            list.add(gson.fromJson(jsonElement, cls));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}
}
