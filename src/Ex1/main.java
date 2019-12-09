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
		function f=new Polynom("3x^2+4");
		ComplexFunction g=new ComplexFunction(Operation.Comp,f,null);
		System.out.println(g.f(4));
		
	}

}
