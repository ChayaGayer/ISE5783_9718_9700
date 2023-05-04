package geometries;
import primitives.Ray;
import primitives.Util;

import java.util.List;

import primitives.Point;
import primitives.Vector;
//class for the tube
public class Tube extends RadialGeometry
{
	protected Ray axisRay;
	/**
	 * constructor of Tube
	 * @param axisRay is a ray of the tube
	 * @param radius of the tube
	 */
	public Tube(double radius,Ray axisRay)
	{
		super(radius);
		this.axisRay=axisRay;
		
	}
	/**
	 * 
	 * @return
	 */
	public Ray getAxisRay() {
        return axisRay;
    
	}
	@Override
	/**
	 * function that returns the vector normal to the tube in the point p
	 * @param point is a point
	 * @return the normal to the triangle in the point
	 */
    public Vector getNormal(Point point)
	{
			Vector dir = axisRay.getDir();
			Point p0 = axisRay.getP0();

			var t = dir.dotProduct(point.subtract(p0));
			if (Util.isZero(t))
				return point.subtract(p0).normalize();
			var o = p0.add(dir.scale(t));
			return point.subtract(o).normalize();
		}

	 @Override
	   public List<Point> findIntersections(Ray ray)
	   {
	   	return null;
	   }
}