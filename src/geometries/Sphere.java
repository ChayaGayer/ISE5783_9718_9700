package geometries;
import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
//a class for the sphere
public class Sphere extends RadialGeometry 
{
	private final Point center;
	
/**
 * constructor
 * @param center
 * @param radius
 */
	public Sphere(Point center,double radius)
	{   super(radius);
		this.center=center;
		
	}
	/**
	 *  function that returns the vector normal to the sphere in the point p
	 *   @param p is a point
	 *   @return the vector normal to the sphere
	 */
	public Vector getNormal(Point p)
	{
		var x = p.subtract(center).normalize();
		return x;
	}
	 @Override
	 /**
	  * Finds the intersection point(s) of a given Ray with the Sphere.
	  * @param ray the Ray to intersect with the Sphere
	  * @return a List of Point objects representing the intersection point(s) of the Ray and the Sphere.
	  * If no intersection occurs, returns null.
	  */
	 public List<Point> findIntersections(Ray ray) {
	     // If the beginning of the Ray is at the center of the Sphere, return the point on the radius as the intersection
	     if (ray.getP0().equals(center)) {
	         return List.of(new Point(ray.getPoint(radius).getXyz()));
	     }
	     
	     // Calculate the vector u from the beginning of the Ray to the center of the Sphere
	     Vector u = center.subtract(ray.getP0());
	     // Calculate the parameter tM of the Ray equation for the closest point to the center of the Sphere
	     double tM = Util.alignZero(ray.getDir().dotProduct(u));
	     // Calculate the distance d between the closest point and the center of the Sphere
	     double d = Util.alignZero(Math.sqrt(u.length() * u.length() - tM * tM));
	     
	     // If d is greater than the radius, there is no intersection
	     if (d > radius) {
	         return null;
	     }
	     
	     // Calculate the parameter tH for the distance from the closest point to the intersection point(s)
	     double tH = Util.alignZero(Math.sqrt(radius * radius - d * d));
	     // Calculate the parameter t1 for the first intersection point
	     double t1 = Util.alignZero(tM + tH);
	     // Calculate the parameter t2 for the second intersection point
	     double t2 = Util.alignZero(tM - tH);
	     
	     // If both t1 and t2 are negative, there is no intersection
	     if (t1 <= 0 && t2 <= 0) {
	         return null;
	     }
	     // If both t1 and t2 are positive, there are two intersection points
	     else if (t1 > 0 && t2 > 0) {
	         return List.of(new Point(ray.getPoint(t1).getXyz()), new Point(ray.getPoint(t2).getXyz()));
	     }
	     // If only t1 is positive, there is one intersection point at t1
	     else if (t1 > 0) {
	         return List.of(new Point(ray.getPoint(t1).getXyz()));
	     }
	     // If only t2 is positive, there is one intersection point at t2
	     else {
	         return List.of(new Point(ray.getPoint(t2).getXyz()));
	     }
	 }
}
