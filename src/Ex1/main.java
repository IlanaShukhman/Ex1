package Ex1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;



public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<function> col = new ArrayList<function>();
		
		 function left=new ComplexFunction("min(div(x^3,x),x)");
		 function right=new ComplexFunction("max(mul(x^2,x),plus(x+2,2))");
		 col.add(left);
		Functions_GUI f=new Functions_GUI(col);
		f.saveToFile("dd.json");
		f.print();
		f.clear();
		f.print();
		Functions_GUI g=new Functions_GUI();
		g.initFromFile("dd.json");
		
	}

}
