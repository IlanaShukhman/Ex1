		
		package myMath;
		
		import java.util.Comparator;
	
	
		
		/**
		 * This class represents a simple "Monom" of shape ax^b, where a is a real number and b is an integer (summed a none negative), 
		 * see: https://en.wikipedia.org/wiki/Monomial 
		 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
		 * @author Michael
		 *
		 */
		public class Monom implements function{
			public static final Monom ZERO = new Monom(0,0);
			public static final Monom MINUS1 = new Monom(-1,0);
			public static final double EPSILON = 0.0000001;
			public static final Comparator<Monom> _Comp = new Monom_Comperator();
			private double _coefficient; 
			private int _power;

			public Monom()
			{
				set_coefficient(0);
				set_power(0);
			}//Monom
			/**
			 * Builder Constructor
			 * @param a - Real number coefficient 
			 * @param b - Natural number power
			 */
			public Monom(double a, int b){
				this.set_coefficient(a);
				this.set_power(b);
			}
			/**
			 * Copy constructor
			 * @param ot - Copy new monom
			 */
            public Monom(Monom ot) 
			{
				set_coefficient(ot.get_coefficient());
				set_power(ot.get_power());
			}//Monom
			/**
			 * Check if two monoms have the same value
			 */
            @Override 
            public boolean equals(Object m)
            {
            	if(m instanceof Monom) {
				if(this._coefficient==0 && ((Monom) m).get_coefficient()==0)
				    return true;
				else {    
					if(Math.abs(this._coefficient-((Monom) m).get_coefficient())<this.EPSILON && this._power==((Monom) m).get_power() )
						return true;
					return false;
				    }//else
            	}//if
            	return false;
			}//boolean
			
			public static Comparator<Monom> getComp() 
			{
				return _Comp;
				}//getComp
			public int get_power() {
				return this._power;
			}
			
			/** 
			 * Derivative function
			 * @return - monom after derivative
			 */
			public Monom derivative() {
				if(this.get_power()==0) 
					return getNewZeroMonom();
				return new Monom((Double)this.get_coefficient()*this.get_power(), this.get_power()-1);
			}//derivative
			/**
			 * Calculating function by input variable x value
			 */
			public double f(double x) {
				double ans=0;
				double p = this.get_power();
				double pow=Math.pow(x, p);
				ans = this.get_coefficient()*pow;
				return ans;
			}//f-function
			/**
			 * Boolean function
			 * @return true if monom is zero else false
			 */
			public boolean isZero() 
			{
				return this.get_coefficient() == 0;
				}//is zero
			// ***************** add your code below **********************
			/**
			 * Builder Constructor
			 * @param s - monom format in string
			 */
			public Monom(String s) 
			{
				s=s.toLowerCase();
				if(s.charAt(0)=='+' && s.length()>1)
					s=s.substring(1);
				if(!ifMonom(s))
					throw new RuntimeException("ERR this is not monom at all "+s);
				else if(!s.contains("x"))
			    {
					this.set_power(0);
				    this.set_coefficient(Double.parseDouble(s));
			    }//else if    
				else if(!s.contains("^"))
				{
					if(s.equals("x") || s.equals("+x"))
					{
						set_coefficient(1);
						set_power(1);
					}//if
					else if(s.equals("-x"))
					{
						set_coefficient(-1);
						set_power(1);
					}//else if
					else {
					int index=s.indexOf('x');
					String coe=s.substring(0, index);
					double num=Double.parseDouble(coe);
					if(num!=0) {
						this.set_power(1);
						if (num%1==0) {
							this.set_coefficient((int)num);
						}//if
						else
							this.set_coefficient(num);
					    }//else
					else
						setNewZeroMonom();
					}//else
				}//else if
				else
				{
					if(s.charAt(0)=='x')
					{
						set_coefficient(1);
						String pow=s.substring(2);
						set_power(Integer.parseInt(pow));
					}//if
					else if(s.substring(0,2).equals("-x"))
					{
						set_coefficient(-1);
						String pow=s.substring(3);
						set_power(Integer.parseInt(pow));
					}//else if
					else if(s.substring(0,2).equals("+x"))
					{
						set_coefficient(+1);
						String pow=s.substring(3);
						set_power(Integer.parseInt(pow));
					}//else if
					else
					{
					int index=s.indexOf('x');
					System.out.println();
					String coe=s.substring(0, index);
					String pow=s.substring(index+2);
					double num=Double.parseDouble(coe);
					if(num!=0) {
						if(num%1==0)
							this.set_coefficient((int)num);
						else
							this.set_coefficient(num);
				    	set_power(Integer.parseInt(pow));
					    }
					else
					setNewZeroMonom();
					}//else
				}//else	   
			}//monom
			/**
			 * Adding two monoms and output to adding one
			 * @param m - another monom
			 */
			public void add(Monom m)
			{
				if(isZero() && !m.isZero())
				{
					set_coefficient(m.get_coefficient());
					set_power(m.get_power());
				}//if
				else if(!isZero() && m.isZero())
				{
					
				}//else if
			    else if(m.get_power()==get_power())
			    {
			    	if(m.get_coefficient()+get_coefficient()!=0)
					set_coefficient(get_coefficient()+m._coefficient);
			    	else
			    		setNewZeroMonom();
			    }
				else
					throw new RuntimeException("ERR: the");
			}//add
			/**
			 * Multiplying two monoms to one
			 * @param d - another monom
			 */
			public void multipy(Monom d) 
			{
				set_coefficient(get_coefficient()*d.get_coefficient());
				set_power(get_power()+d.get_power());
			}//multipy
			/**
			 * Presentation of monom by String
			 */
			public String toString() {
				String ans = "";
				if(this._coefficient==0)		
					    ans+="0";		
				else if(this._power==0)
					ans+=Double.toString(this._coefficient);
				else if(this._power==1)
				{
					ans+=Double.toString(this._coefficient)+"x";
				}//else if
				else
					ans+=Double.toString(this._coefficient)+"x^"+Integer.toString(this._power);
				return ans;
			}
			
		   
			
			/**
			 * This function check if giving string monomial have proper form as following the shape:
			 * ax^b, where a is a real number and b is an integer by using string matches format.
			 */
           public boolean ifMonom(String monom)
			{
				if(!monom.matches("-?\\d+(?:.\\d+)?e\\^-?\\d+(?:.\\d+)?x") && //exponential
				        !monom.matches("-?\\d+(?:.\\d+)?") && //constant
				        !monom.matches("-?[a-zA-Z]") && //variable
				        !monom.matches("-?\\d+(?:.\\d+)?[a-zA-Z]") && //constant w/ variable
				        !monom.matches("-?[a-zA-Z]\\^-?\\d+(?:.\\d+)?") && //variable w/ exponent
				        !monom.matches("-?\\d+(?:.\\d+)?[a-zA-Z]\\^-?\\d+(?:.\\d+)?"))//constant w/ variable w/ exponent
					return false;
				return true;
			}//
         //Setters:
			private void set_coefficient(double a){
				this._coefficient = a;
			}//setPower
			private void set_power(int p) {
				if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
				this._power = p;
			}//setPower
			public double get_coefficient() {return this._coefficient;}
				
					
			private static Monom getNewZeroMonom() {return new Monom(ZERO);}//getNewZeroMonom
			private void setNewZeroMonom() 
			{
				this._coefficient=0;
				this._power=0;
			}//setNewZeroMonom
			
		
		}//Monom Class
