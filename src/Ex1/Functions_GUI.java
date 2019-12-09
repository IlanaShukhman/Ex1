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
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;







public class Functions_GUI implements functions {
	private ArrayList<function> func_collection;

	public Functions_GUI() {
		this.func_collection = new ArrayList<function>();
	}//Functions_GUI

	public Functions_GUI(ArrayList<function> func_collection) {
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
			Iterator<function> it=iterator();
			while(it.hasNext())
			{
				function f=it.next();
				if(f.equals(o))
					return true;
			}//while
			return false;
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
	}//add


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
		if(c.size()>size())
			return false;
		else
		{
			Object [] a=c.toArray();
			for (int i = 0; i < a.length; i++) {
				if(a[i] instanceof function)
				{
					if(!contains(a[i]))
						return false;
				}//if
				else
					throw new RuntimeException("ERR: this is not function so you cant compare");
			}//for
			return true;
		}//else
	}//containsAll
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
		Iterator<function> it=iterator();
		int counter=0;
		ArrayList<function> del=new ArrayList<function>();
		while(it.hasNext())
		{
			function f=it.next();
			if(!c.contains(f))
			{
				del.add(f);
			}//if

		}//while
		removeAll(del);
		return true;
	}//retainAll
	/**
	 * Removes all of the elements from this collection (optional operation).
	 */
	public void clear() {
		this.func_collection.clear();
	}
	/**
	 * We can't yet compare two ComplexFunctions.
	 */
	public boolean equals(Object f) {
		if(f instanceof Functions_GUI)//If they are the same object
		{
			if(this.size()==0 && (((Functions_GUI) f).size()==0))//
				return true;
			else if(this.size()==((Functions_GUI) f).size())//Now we can compare them each other
			{
				Iterator<function> it=iterator();
				while(it.hasNext())
				{
					function g=it.next();
					if(!((Functions_GUI) f).contains(g))//Its good for polynoms and monoms Dif for ComplexFunction
						return false;
				}//while
				return true;
			}//else
			return false;
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
			//int count=1;
			while(it.hasNext())
			{
				function g=it.next();
				into.append(g.toString()+"\n");			}//while
			print.write(into.toString());
			print.close();
		} catch (Exception e) {
			System.out.println("ERR: failing to save this list.");
		}
	}//SaveToFile



	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		Color[] color= {Color.blue,Color.cyan,Color.darkGray,Color.gray,Color.green,Color.magenta,Color.orange
				,Color.pink};

		StdDraw.setCanvasSize(width,height);

		int colorNum=0;
		double maxY = ry.get_max(), minY = ry.get_min();
		double maxX = rx.get_max(), minX = rx.get_min();


		// calculate all the parameters needed to draw the function:
		int n=resolution;
		double rangeX=rx.get_max()-rx.get_min();
		double rangeY=ry.get_max()-ry.get_min();

		Range Width=new Range(width*minX/rangeX,width*maxX/rangeX);
		Range Height=new Range(height*minY/rangeY,height*maxY/rangeY);


		StdDraw.setXscale(Width.get_min(), Width.get_max());
		StdDraw.setYscale(Height.get_min(), Height.get_max());


		/*The stepX is calculated according to the resolution.
		 *It the resolution is higher, the step is smaller, so there will be more steps in rangeX.
		 */
		double stepX=rangeX/n;

		/*
		 * stepW and stepH are how many pixels are there in x=1 and y=1
		 */
		double stepW=width/rangeX;
		double stepH=height/rangeY;

		////////vertical lines
		StdDraw.setPenColor(Color.LIGHT_GRAY);
		drawLines(Width,Height,stepW,stepH);
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		addNumbers(Width,Height,stepW,stepH);

		///////Draw x axis and y axis
		StdDraw.line(Width.get_min(), 0, Width.get_max(), 0);
		StdDraw.line(0, Height.get_min(), 0, Height.get_max());

		Iterator<function> itr= iterator();

		//go through all the functions in Functions_GUI:
		while(itr.hasNext()) {

			function f= itr.next();

			double[] x = new double[n+1];
			double[] y = new double[n+1];


			//calculate (x,y) according to the resolution
			for (int i = 0; i <= n; i++) {
				x[i] = minX+i*stepX;

				//in case of dividing by 0
				try {
					y[i] = f.f(x[i]);
				}
				catch(Exception e) {

				}
				//(x,y) place on the screen:
				x[i] = (x[i]*Width.get_max())/rx.get_max();
				y[i] = (y[i]*Height.get_max())/ry.get_max();

			}

			// rescale the coordinate system
			StdDraw.setXscale(Width.get_min(), Width.get_max());
			StdDraw.setYscale(Height.get_min(), Height.get_max());


			StdDraw.setPenColor(color[colorNum]);

			// plot the approximation to the function
			for (int i = 0; i < n; i++) {
				StdDraw.line(x[i], y[i], x[i+1], y[i+1]);
			}

			//change color for next turn 
			colorNum++;
			if(colorNum==8)
				colorNum=0;

		}
	}//drawFunction
	/**
	 * This function draw horizontal and vertical lines according to the received parameters:
	 * @param Width the width range in pixels
	 * @param Height the height range in pixels
	 * @param stepW how much pixels is x=1
	 * @param stepH how much pixels is y=1
	 */

	private void drawLines(Range Width, Range Height, double stepW, double stepH) {

		/////// vertical lines
		for (double i = 0; i <= Width.get_max(); i=i+stepW){
			StdDraw.line(i, Height.get_min(), i, Height.get_max());

		}
		for (double i = 0; i >= Width.get_min(); i=i-stepW){
			StdDraw.line(i, Height.get_min(), i, Height.get_max());

		}

		//////// horizontal  lines
		for (double i = 0; i <= Height.get_max(); i=i+stepH) {
			StdDraw.line(Width.get_min(), i, Width.get_max(), i);

		}

		for (double i = 0; i >= Height.get_min(); i=i-stepH) {
			StdDraw.line(Width.get_min(), i, Width.get_max(), i);

		}
	}//drawLines

	/**
	 * This method adds the numbers to the x axis and y axis
	 * @param Width the width range in pixels
	 * @param Height the height range in pixels
	 * @param stepW how much pixels is x=1
	 * @param stepH how much pixels is y=1
	 */

	private void addNumbers(Range Width, Range Height, double stepW, double stepH) {

		int j=0;
		for (double i = 0; i <= Width.get_max(); i=i+stepW){
			StdDraw.text(i-0.07, -0.07, Integer.toString(j));
			j++;
		}
		j=0;
		for (double i = 0; i >= Width.get_min(); i=i-stepW){			
			StdDraw.text(i-0.07, -0.07, Integer.toString(j));
			j--;
		}
		j=0;
		//////// horizontal  lines
		for (double i = 0; i <= Height.get_max(); i=i+stepH) {		
			StdDraw.text(0, i-0.07, Double.toString(j));
			j++;
		}
		j=0;
		for (double i = 0; i >= Height.get_min(); i=i-stepH) {
			StdDraw.text(0, i-0.07, Double.toString(j));
			j--;
		}
	}//addNumbers





	@Override
	public void drawFunctions(String json_file) {
		if(ifJson(json_file))
			throw new RuntimeException("ERR: trying to parsing json file but the given file isnt json");
		Gson gson = new Gson();
		try 
		{
			FileReader reader = new FileReader(json_file);
			JsonObject param = gson.fromJson(reader,JsonObject.class); 
			int width=Integer.valueOf(String.valueOf(param.get("Width")));
			int height=Integer.valueOf(String.valueOf(param.get("Height")));
			int resolution=Integer.valueOf(String.valueOf(param.get("Resolution")));
			Range rx=new Range();
			Range ry=new Range();
			rx.setValues(String.valueOf(param.get("Range_X")));
			ry.setValues(String.valueOf(param.get("Range_Y")));
			drawFunctions(width, height, rx, ry, resolution);

		}//try
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}//catch

	}//drawFunctions
	/**
	 * 
	 * @param name - path file
	 * @return - if the path file have .json in the suffix.
	 */
	private boolean ifJson(String name)
	{
		int index=name.indexOf('.');
		String type=name.substring(index);
		if(name.equals("json"))
			return true;
		return false;	
	}//ifJson
}
