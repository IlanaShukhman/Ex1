package Ex1;

public class Graph_Para {
	private int width;
	private int height;
	private Range rx;
	private Range ry;
	private int resolution;
	/*
	 * Constructors:
	 */
	public Graph_Para(int width, int height, Range rx, Range ry, int resolution) {
		this.width = width;
		this.height = height;
		this.rx = rx;
		this.ry = ry;
		this.resolution = resolution;
	}//Graph_Para
	/*
	 * Getters:
	 */
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Range getRx() {
		return rx;
	}
	/*
	 * Setters:
	 */
	public void setRx(Range rx) {
		this.rx = rx;
	}
	public Range getRy() {
		return ry;
	}
	public void setRy(Range ry) {
		this.ry = ry;
	}
	public int getResolution() {
		return resolution;
	}
	public void setResolution(int resolution) {
		this.resolution = resolution;
	}
	
	
}
