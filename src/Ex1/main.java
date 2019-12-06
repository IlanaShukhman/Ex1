package Ex1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;





public class main {

	public static void main(String[] args) {

		
		 ComplexFunction left=new ComplexFunction("min(div(x^3,x),x)");
		 ComplexFunction right=new ComplexFunction("max(mul(x^2,x),plus(x+2,2))");
		 Operation op=Operation.Plus;
		 ComplexFunction f=new ComplexFunction(op,left,right);
		 
	
			//Make JSON!!
			Gson gson = new Gson();
			String json = gson.toJson(f);
			System.out.println(json);

			//Write JSON to file
			try 
			{
				PrintWriter pw = new PrintWriter(new File("bookstore.json"));
				pw.write(json);
				pw.close();
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
				return;
			}
			
 
		
		
		

	}

}
