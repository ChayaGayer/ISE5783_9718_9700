package geometries;
import primitives.Ray;
import primitives.Util;
import primitives.Point;
import primitives.Vector;

public class Tube extends RadialGeometry
{
	protected Ray axisRay;

	public Tube(double radius,Ray axisRay)
	{
		super(radius);
		this.axisRay=axisRay;
		
	}
	public Ray getAxisRay() {
        return axisRay;
    
	}
	@Override
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


}