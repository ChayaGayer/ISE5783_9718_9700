package geometries;
import primitives.Ray;
import primitives.Point;
import primitives.Vector;
import static primitives.Util.*;

// a class for cylinder extends Tube
public class Cylinder extends Tube
{

	final double height;
	 
	/**
	 * constructor
	 * @param radius The cylinder's radius
	 * @param height The cylinder's height
	 * @param axisRay The ray of the cylinder's axis
	 */
	public Cylinder(Ray axisRay, double radius,double height) {
		super(radius, axisRay);
		 this.height= height;
	}
	/**
	 * get normal for the cylinder
	 */
	public Vector getNormal(Point p0)
	{

		Point firstBaseCenter=axisRay.getP0();
		boolean isOnFirstBaseCenter=false;
		Vector vecFirstBase=new Vector (1,1,1);
		if(p0.equals(firstBaseCenter))
			isOnFirstBaseCenter=true;
		else
			vecFirstBase=p0.subtract(firstBaseCenter);
		
		Point secondBaseCenter=axisRay.getP0().add(axisRay.getDir().scale(height));
		boolean isOnSecondBaseCenter=false;
		Vector vecSecondBase=new Vector (1,1,1);
		if(p0.equals(secondBaseCenter))
			isOnSecondBaseCenter=true;
		else
			vecSecondBase=p0.subtract(secondBaseCenter);
		
		
		//if the point is on the first base (and as decided - not on the border with the cylinder's side):
		if(isOnFirstBaseCenter||(isZero(vecFirstBase.dotProduct(axisRay.getDir()))&&vecFirstBase.length()<this.radius))
			return axisRay.getDir().scale(-1).normalize();
		//if the point is on the second base (and as decided - not on the border with the cylinder's side):
		else if(isOnSecondBaseCenter||(isZero(vecSecondBase.dotProduct(axisRay.getDir()))&&vecSecondBase.length()<this.radius))
			return axisRay.getDir().normalize();
		//if the point is on the cylinder's side:
		else
			return super.getNormal(p0);
	}
	
	/**
	 * Getter height
	 * @return Cylinder's height
	 */
	public double  getH()
	{
		return this.height;
	}

}
