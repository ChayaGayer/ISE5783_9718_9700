package geometries;
import java.util.List;
import static primitives.Util.*;
import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
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
	/**
	 * Finds the intersection points of a given ray with the current triangle.
	 * @param myRay The ray that intersects with the triangle
	 * @return A list of intersection points between the given ray and the triangle, or null if there is no intersection
	 */
	/*public List<Point> findIntersections(Ray myRay)	{
		List<Point> rayPoints = plane.findIntersections(myRay);
		if (rayPoints == null)
			return null;

		//check if the point in out or on the triangle:
		Vector v1 = vertices.get(0).subtract(myRay.getP0());
		Vector v2 = vertices.get(1).subtract(myRay.getP0());
		Vector v3 = vertices.get(2).subtract(myRay.getP0());
		
		Vector n1 = v1.crossProduct(v2).normalize();
		Vector n2 = v2.crossProduct(v3).normalize();
		Vector n3 = v3.crossProduct(v1).normalize();

		//The point is inside if all 𝒗 ∙ 𝑵𝒊 have the same sign (+/-)
		if (Util.alignZero(n1.dotProduct(myRay.getDir())) > 0 && Util.alignZero(n2.dotProduct(myRay.getDir())) > 0 && Util.alignZero(n3.dotProduct(myRay.getDir())) > 0) {
			return rayPoints;
		}
		else if (Util.alignZero(n1.dotProduct(myRay.getDir())) < 0 && Util.alignZero(n2.dotProduct(myRay.getDir())) < 0 && Util.alignZero(n3.dotProduct(myRay.getDir())) < 0) {
			return rayPoints;
		}
		if (Util.isZero(n1.dotProduct(myRay.getDir())) || Util.isZero(n2.dotProduct(myRay.getDir())) || Util.isZero(n3.dotProduct(myRay.getDir())))
			return null; //there is no intersection point
		return null;
	}*/
	//
	@Override
	/**
	 * Finding intersection-geoPoints with a given ray
	 * @param ray A ray
	 * @return All the intersection-geoPoints of this triangle and the given ray
	 */
	  //Check if the ray intersect the plane.
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