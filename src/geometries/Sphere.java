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
	   public List<Point> findIntersections(Ray ray)
	   {
		 if (ray.getP0().equals(center)) // if the begin of the ray in the center, the point, is on the radius
				return List.of(new Point(ray.getPoint(radius).getXyz()));
			Vector u = center.subtract(ray.getP0());
			double tM = Util.alignZero(ray.getDir().dotProduct(u));
			double d = Util.alignZero(Math.sqrt(u.length()*u.length()- tM * tM));
			double tH = Util.alignZero(Math.sqrt(radius*radius - d*d));
			double t1 = Util.alignZero(tM+tH);
			double t2 = Util.alignZero(tM-tH);
			
			
			if (d > radius)
				return null; // there are no instructions

			
			if (t1 <=0 && t2<=0)
				return null;
			
			if (t1 > 0 && t2 >0)
				return List.of(new Point(ray.getPoint(t1).getXyz()),new Point(ray.getPoint(t2).getXyz()));
			if (t1 > 0)
			{
				return List.of(new Point(ray.getPoint(t1).getXyz()));
			}

			else
				return List.of(new Point(ray.getPoint(t2).getXyz()));
		
	   }

}
