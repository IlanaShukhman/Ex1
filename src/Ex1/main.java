package Ex1;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.google.gson.Gson;



public class main {

	public static void main(String[] args) throws IOException {
//		// TODO Auto-generated method stub
//		Functions_GUI f=new Functions_GUI();
//
//		 function left=new ComplexFunction("min(div(x^3,x),x)");
//		 function right=new ComplexFunction("max(mul(x^2,x),plus(x+2,2))");
//		 f.add(left);
//		 f.add(right);
//		 f.saveToFile("function.txt");
//		 String l=left.toString();
//		 function g=new ComplexFunction();
//		 Functions_GUI g=new Functions_GUI();
//		 f.initFromFile("function.txt");
//		
//		// number of line segments to plot
//		int n = 100;
//		double maxY = 2.0, minY = -2.0;
//
//		// the function y = sin(4x), sampled at n+1 points
//		// between x = 0 and x = pi
//		double[] x = new double[n+1];
//		double[] y = new double[n+1];
//		for (int i = 0; i <= n; i++) {
//			x[i] = Math.PI * i / n;
//			y[i] = Math.sin(4*x[i]);
//		}		
//		// rescale the coordinate system
//		StdDraw.setXscale(0, Math.PI);
//		StdDraw.setYscale(minY, maxY);
//		
//		//////// vertical lines
//		StdDraw.setPenColor(Color.LIGHT_GRAY);
//		for (int i = 0; i <= n; i=i+10) {
//			StdDraw.line(x[i], minY, x[i], maxY);
//		}
//		//////// horizontal  lines
//		for (double i = minY; i <= maxY; i=i+0.5) {
//			StdDraw.line(0, i, Math.PI, i);
//		}
//		//////// x axis		
//		StdDraw.setPenColor(Color.BLACK);
//		StdDraw.setPenRadius(0.005);
//		StdDraw.line(0, y[n/2], Math.PI, y[n/2]);
//		StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 15));
//		for (int i = 0; i <= n; i=i+10) {
//			StdDraw.text(x[i]-0.07, -0.07, Integer.toString(i-n/2));
//		}
//		//////// y axis	
//		StdDraw.line(x[n/2], minY, x[n/2], maxY);
//		for (double i = minY; i <= maxY; i=i+0.5) {
//			StdDraw.text(x[n/2]-0.07, i+0.07, Double.toString(i));
//		}
//
//		// plot the approximation to the function
//		for (int i = 0; i < n; i++) {
//			StdDraw.line(x[i], y[i], x[i+1], y[i+1]);
//		}
//
//	
//	
//
//
		Functions_GUI f1=new Functions_GUI();
		function f2=new ComplexFunction("plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0)");
		function f3=new ComplexFunction("div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)");
		function f4=new ComplexFunction("plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)");
		function f5=new ComplexFunction(" -1.0x^4 +2.4x^2 +3.1");
		function f6=new ComplexFunction("+0.1x^5 -1.2999999999999998x +5.0");
		function f7=new ComplexFunction("max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)");
		f1.add(f2);
		f1.add(f3);
		f1.add(f4);
		f1.add(f5);
		f1.add(f6);
		f1.add(f7);
		function f8=new ComplexFunction("x");
		f1.add(f8);
		int width=1000;
		int height=1000;
		Range rx=new Range(-5,15);
		Range ry=new Range(-15,15);
		int resolution=100;
		f1.drawFunctions(width, height, rx, ry, resolution);
		

	}

}
