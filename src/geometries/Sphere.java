package geometries;
import static primitives.Util.*;
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
	 //@Override
	 /**
	  * Finds the intersection point(s) of a given Ray with the Sphere.
	  * @param ray the Ray to intersect with the Sphere
	  * @return a List of Point objects representing the intersection point(s) of the Ray and the Sphere.
	  * If no intersection occurs, returns null.
	  */
	 protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
	        Point p0 = ray.getP0(); // ray's starting point
	        Point O = this.center; //the sphere's center point
	        Vector V = ray.getDir(); // "the v vector" from the presentation

	        // if p0 on center, calculate with line parametric representation
	        // the direction vector normalized.
	        if (O.equals(p0)) {
	            Point newPoint = p0.add(ray.getDir().scale(this.radius));
	            return List.of(new GeoPoint(this,newPoint));
	        }

	        Vector U = O.subtract(p0);
	        double tm = V.dotProduct(U);
	        double d = Math.sqrt(U.lengthSquared() - tm * tm);
	        if (d >= this.radius) {
	            return null;
	        }

	        double th = Math.sqrt(this.radius * this.radius - d * d);
	        double t1 = tm - th;
	        double t2 = tm + th;

	        if (t1 > 0 && t2 > 0) {
	            Point p1 = ray.getPoint(t1);
	            Point p2 = ray.getPoint(t2);
	            return List.of(new GeoPoint(this,p1),new GeoPoint(this,p2));
	        }

	        if (t1 > 0) {
	            Point p1 = ray.getPoint(t1);
	            return List.of(new GeoPoint(this,p1));
	        }

	        if (t2 > 0) {
	            Point p2 = ray.getPoint(t2);
	            return List.of(new GeoPoint(this,p2));
	        }
	        return null;
	    
	}

	}