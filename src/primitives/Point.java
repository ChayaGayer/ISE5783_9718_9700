package primitives;

public class Point
{
	 Double3 xyz;
	 /**constructors
	  * constructor that receiving the values of the three coordinates from Double
	  * constructors that receiving Object from Double3 type
	  * @param xyz
	  */
		 
	public Point(Double3 xyz)
	{
		this.xyz=xyz;
	}
	public Point(double d1,double d2,double d3)
	{
		this.xyz=new Double3(d1,d2,d3);
	}
	
	/**
	 * Override equals func that compare between two points and return true if they equal,otherwise false
	 */
	@Override
	  public boolean equals(Object obj) {
	      if (this == obj) return true;
	      if (obj == null) return false;
	      if (!(obj instanceof Point)) return false;
	      Point other = (Point)obj;
	      return this.xyz.equals(other.xyz);
	   }
	/**
	 * Override to string func
	 * @return String
	 */ 
	@Override
	public String toString() {
		return "Point [xyz=" + xyz.toString() + "]";
	}
	/**
	 * Get Func
	 * @return Double3
	 */
	/*public Double3 getXyz() {
		return xyz;
	}*/
	
	/**
	 * Sum two floating point triads into a new triad where each couple of numbers
	 * is summarized
	 * 
	 * @param rhs right handle side operand for addition
	 * @return result of add
	 */
	public Point add(Vector v) {
		return new Point(v.xyz.add(this.xyz));
	}
	/**
	 * Subtract two floating point triads into a new triad where each couple of
	 * numbers is subtracted
	 * 
	 * @param rhs right handle side operand for addition
	 * @return result of add
	 */
	public Vector subtract(Point p) {
		return new Vector(p.xyz.subtract(this.xyz));
	}
	/**
	 * @param p- point
	 * @return the squared distance between two points 
	 */
	double DistanceSquared(Double3 d) 
	{
		//Point p=new Point(d);
		Point po=new Point(d.subtract(this.xyz));
		return po.xyz.d1*po.xyz.d1 + po.xyz.d2*po.xyz.d2 + po.xyz.d3*po.xyz.d3;
	}
	/**
	 * @param p- point
	 * @return the distance between two points 
	 */
	 double Distance(Double3 d) 
	 {
		//Point p=new Point(d);
		return Math.sqrt(DistanceSquared(d));
	 }
}
