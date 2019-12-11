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
	ComplexFunction g=new ComplexFunction("Plus ( 3.0x^2+5.0 , 0 )");
	g.plus(g);
	System.out.println(g.toString());
	ComplexFunction f=new ComplexFunction("Plus(Plus ( 3.0x^2+5.0 , 0 ),Plus ( 3.0x^2+5.0 , 0 ))");
	String s="dsds";
	System.out.println();
		
		
	}

}
