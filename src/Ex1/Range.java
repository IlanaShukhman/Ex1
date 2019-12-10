package Ex1;
/**
 * This class represents a simple 1D range of shape [min,max]
 * @author boaz_benmoshe
 *
 */
public class Range {
	private double _min, _max;
	public Range(double min, double max) {
		set_min(min);
		set_max(max);
	}
	public Range() {
		set_min(0);
		set_max(0);
	}
	public boolean isIn(double d) {
		boolean inSide = false;
		if(d>=this.get_min() && d<=this.get_max()) {inSide=true;}
		return inSide;
	}
	public String toString() {
		String ans = "["+this.get_min()+","+this.get_max()+"]";
		if(this.isEmpty()) {ans = "Empty Range";}
		return ans;
	}
	public boolean isEmpty() {
		return this.get_min()>this.get_max();
	}
	public double get_max() {
		return _max;
	}
	private void set_max(double _max) {
		this._max = _max;
	}
	public double get_min() {
		return _min;
	}
	private void set_min(double _min) {
		this._min = _min;
	}
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Range)
		{
			if(((Range) o).get_max()==this._max && ((Range) o).get_min()==this._min)
				return true;
			return false;
		}//if
	    return false;
	}//equals
	
	/**
	 * Parser From Jsom to this object
	 */
	public void setValues(String json)
	{
		json=json.replaceAll("\\[","");
		json=json.replaceAll("\\]","");
		System.out.println(json);
		int index=json.indexOf(',');
		String max=json.substring(index+1);
		String min=json.substring(0,index);
		double Min=Double.valueOf(min);
		double Max=Double.valueOf(max);
		set_max(Max);
		set_min(Min);
		
	}//setValues
}
