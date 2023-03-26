package primitives;

public class Point {
	/**
	 *The field of coordinates of type Double3, without permission
	 */	
	final Double3 xyz;
	/**
	 *The constructor accepts three double numbers for the coordinate values
	 */	
public Point(double d1,double d2,double d3)
{
	this.xyz=new Double3(d1,d2,d3);
}
/**
 * The field of coordinates of type Double3, without permission
 */
 Point(Double3 xyz){
	this.xyz=xyz;
}
 /**
	 * The comparison method equals
	 */
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (!(obj instanceof Point)) return false;
    Point other = (Point)obj;
    return this.xyz.equals(other.xyz);
 }
@Override
public String toString() {
	return "Point (xyz=" + xyz.toString() + ")";
}
/**
 * Vector subtraction - receives a second point in the parameter,
 *  returns a vector from the second point to the point on which 
 *  the operation is performed
 * @param p
 * @return new Vector after the subtraction
 */
public Vector subtract(Point p)
{
	return new Vector(this.xyz.subtract(p.xyz));
}
/**
 * Adding a vector to a point
 * @param vec
 * @return new Point
 */
public Point add(Vector vec)
{
	return new Point(vec.xyz.add(this.xyz));
}
/**
* calculate squared distance between two points
* @param p- point
* @return squared distance between two points 
*/

public double distanceSquared(Point p) {
    return (xyz.d1 - p.xyz.d1) * (xyz.d1 - p.xyz.d1) +
        (xyz.d2 - p.xyz.d2) * (xyz.d2 - p.xyz.d2) +
        (xyz.d3 - p.xyz.d3) * (xyz.d1 - p.xyz.d3);
}


public double distance(Point p)
{
	return (Math.sqrt(distanceSquared( p)));
}






}