package Ex1;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;



public class main {

	public static void main(String[] args) throws IOException {
		Collection<function> c=new ArrayList<function>();//empty
		Functions_GUI f=new Functions_GUI();
		Functions_GUI g=new Functions_GUI();
		function f1=new ComplexFunction("plus(x^2,x)");
		function f2=new ComplexFunction("mul(3x^5, 5)");
		function f3=new ComplexFunction("min(div(x^3,x-1),x)");
		function f4=new ComplexFunction("max(mul(x^2,x),plus(x+2,2))");
		f.add(f1);
		f.add(f2);
		g.add(f2);
		g.add(f4);
		
		//System.out.println(f.toString());
		//System.out.println(g.toString());
		System.out.println();
		f.retainAll(g.get_func_collection());
		System.out.println(f.toString());
	}

}
