package Ex1Tests;

//https://introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html


import Ex1.StdDraw;

public class TestStdDraw {
	
	public static void testStdDraw1() {
		StdDraw.setPenRadius(0.05);
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.point(0.5, 0.5);
		StdDraw.setPenColor(StdDraw.MAGENTA);
		StdDraw.line(0.2, 0.2, 0.8, 0.2);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.circle(100, 100, 0.5);
	}
	public static void testStdDraw2() {
		StdDraw.square(0.2, 0.8, 0.1);
		StdDraw.filledSquare(0.8, 0.8, 0.2);
		StdDraw.circle(0.8, 0.2, 0.2);

		StdDraw.setPenColor(StdDraw.BOOK_RED);
		StdDraw.setPenRadius(0.02);
		StdDraw.arc(0.8, 0.2, 0.1, 200, 45);

		// draw a blue diamond
		StdDraw.setPenRadius();
		StdDraw.setPenColor(StdDraw.BOOK_BLUE);
		double[] x = { 0.1, 0.2, 0.3, 0.2 };
		double[] y = { 0.2, 0.3, 0.2, 0.1 };
		StdDraw.filledPolygon(x, y);

		// text
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(0.2, 0.5, "black text");
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(0.8, 0.8, "white text");
	}
	
	public static void main(String[] args) {
		testStdDraw1();
		testStdDraw2();
	}

}
