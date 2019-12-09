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
	@Override
	public String toString() {
		return "Graph_Para [width=" + width + ", height=" + height + ", rx=" + rx + ", ry=" + ry + ", resolution="
				+ resolution + "]";
	}
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Graph_Para)
		{
			if(((Graph_Para) o).getHeight()==this.height && ((Graph_Para) o).getWidth()==this.width && ((Graph_Para) o).getRx()==this.rx && ((Graph_Para) o).getRy()==this.ry && ((Graph_Para) o).getResolution()==this.resolution)
					return true;
			else 
				return false;
		}//if
		return false;
	}//equals
	
	
}
