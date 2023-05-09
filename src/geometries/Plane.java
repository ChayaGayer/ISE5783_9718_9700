package geometries;

import java.util.List;

import primitives.*;


// a class for a plane
public class Plane implements Geometry {

	private Point p0;
	private Vector normal;
	/**
	 *  constructor of plane that gets 3 points. save one and calculates the normal to the plane
	 * @param p1 first point in the plane
	 * @param p2 second point in the plane
	 * @param p3 third point in the plane
	 */
public Plane(Point p1,Point p2,Point p3)
	{
	Vector v1 = (p1.subtract(p2));//get one vector on plane
	Vector v2 = (p1.subtract(p3));//get second vector on plane
	this.normal = v1.crossProduct(v2).normalize();//if v1 and v2 are on the same direction of vector- the cross product and the normal will be zero vector.		
	this.p0 = p1;
}
	
/**
 * constructor that get a point and the normal vector
 * @param q0
 * @param normal
 *
 */
public Plane(Point q0,Vector normal)
{
this.p0=q0;
this.normal=normal.normalize();
}
/**
 * get the point in the plane function
 * @return the point in the plane
 */
public Point getP0() {
    return p0;
}
/**
 * get normal to the plane function
 * @return normal to the plane
 */
public Vector getNormal() {
    return normal;
}

@Override
/**
 *  function that returns the vector normal to the plain in the point p
 *  @param p is a point
 *  @return the the normal to the plane
 */
public Vector getNormal(Point point) {
    return normal;
}
/**
 * Finds the intersection point(s) of a given Ray with the Plane.
*@param myRay the Ray to intersect with the Plane
*@return a List of Point objects representing the intersection point(s) of the Ray and the Plane.
If no intersection occurs, returns null.
*@throws IllegalArgumentException if the given Ray or any of its components are null or uninitialized
 */
@Override

public List<Point> findIntersections(Ray myRay) {
	double nv = normal.dotProduct(myRay.getDir());
	if (Util.isZero(nv))
	{
		return null;
	}
	
	try 
	{
		// Calculate the parameter t of the Ray equation for the intersection point
		Vector pSubtractP0 = p0.subtract(myRay.getP0());
		double t = Util.alignZero((normal.dotProduct(pSubtractP0))/nv);

		if(t <= 0)
		{
			return null;
		}
		return List.of(new Point((myRay.getPoint(t)).getXyz()));
	}
	// If an exception is thrown during the calculation of the intersection, return null
	catch(Exception ex) 
	{
		return null;
	}
}
}

