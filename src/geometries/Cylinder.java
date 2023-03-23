package geometries;
import primitives.Ray;
import primitives.Point;
import primitives.Vector;

public class Cylinder extends Tube {

	private double height;
	public Cylinder(double radius,Ray axisRay,double height) {
		super(radius,axisRay);
		this.height=height;
	}
	public Vector getNormal(Point p)
	{
		return null;
	}
	public double  getH()
	{
		return this.height;
	}

}
