package geometries;
import java.util.List;
import static primitives.Util.*;
import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
//class for the triangle

//class for the triangle
public class Triangle extends Polygon
{
	/**
	 * constructor of triangle gets 3 points
	 * @param p1 first point of the triangle
	 * @param p2 second point of the triangle
	 * @param p3 third point of the triangle
	 */
	public Triangle(Point p1,Point p2,Point p3) 
	{
		super(p1,p2,p3);
		
	}
	//@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray){
    List<GeoPoint> intersections = plane.findGeoIntersections(ray);
    if (intersections == null) return null;

    Point p0 = ray.getP0();
    Vector v = ray.getDir();

    Vector v1 = vertices.get(0).subtract(p0);
    Vector v2 = vertices.get(1).subtract(p0);
    Vector v3 = vertices.get(2).subtract(p0);

    //Check every side of the triangle
    double s1 = v.dotProduct(v1.crossProduct(v2));

    if (isZero(s1)) return null;

    double s2 = v.dotProduct(v2.crossProduct(v3));

    if (isZero(s2)) return null;

    double s3 = v.dotProduct(v3.crossProduct(v1));

    if (isZero(s3)) return null;

    if (!((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0))) return null;

    return List.of(new GeoPoint(this,intersections.get(0).point));
}

}