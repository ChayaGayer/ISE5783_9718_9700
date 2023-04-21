package geometries;
import primitives.Point;
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

}
