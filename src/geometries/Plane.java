package geometries;
import primitives.Point;
import primitives.Vector;


public class Plane implements Geometry {

	private Point p0;
	private Vector normal;
/**
	 * constructor that get three points and calculate normal to the triangle
	 * @param p1
	 * @param p2
	 * @param p3
 */
public Plane(Point p1,Point p2,Point p3)
	{
		this.normal=null;
		this.p0=p1;
	}
/**
 * constructor that get a point and the normal vector
 * @param q0
 * @param normal
 */
public Plane(Point q0,Vector normal)
{
this.p0=q0;
this.normal=normal.normalize();
}

public Point getP0() {
    return p0;
}
public Vector getNormal() {
    return normal;
}

@Override
public Vector getNormal(Point point) {
    return normal;
}


}

