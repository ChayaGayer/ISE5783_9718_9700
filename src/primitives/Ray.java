
package primitives;
import static primitives.Util.*;
import geometries.Intersectable.GeoPoint;
import java.util.List;
import java.util.MissingResourceException;

//class for the ray
public class Ray
{
	final Point p0;
	final Vector dir;
/**
 * constructor that get a point and vec
 * @param dir
 * @param p0
 */
	public Ray( Vector dir,  Point p0)
	{
		this.p0=p0;
		this.dir=dir.normalize();
	}
	@Override
	public boolean equals(Object obj)
	{
	        if (this == obj) return true;
	        if (obj instanceof Ray other)
	            return this.p0.equals(other.p0) && this.dir.equals(other.dir);
	        return false;
	}
	@Override
    public String toString() {
        return "Ray:" +
                "p0=" + p0 +
                ", dir=" + dir;
    }
	public Point getP0() {
        return this.p0;
	}
	public Vector getDir()
	{
		return this.dir.normalize();
	}
	/**
	 * Getting a point on the ray
	 * @param t The "distance" between the ray's starting-point and the wanted point
	 * @return The point on the ray whose "distance" from the ray's starting-point is t
	 */
	public Point getPoint(double t) {
		if(isZero(t))
			return this.p0;
		Vector vec=(this.dir).scale(t);
		return (this.p0).add(vec);
	}
	/**
	Finds the closest point in a given list of points to a reference point.
	@param pointList The list of points to search through.
	@return The closest point to the reference point. Returns null if the pointList is empty.
	 * @throws Exception 
	*/
	public Point findClosestPoint(List<Point> points)  {
		 return points == null || points.isEmpty() ? null
		 : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
		}

	/**
	 * Get the closest geoPoint to the ray's starting-point out of a given collection of geoPoints
	 * @param points A collection of geoPoints
	 * @return The geoPoint which is the closest to the ray's starting-point
	 * @throws Exception 
	 */
	/*public GeoPoint findClosestGeoPoint(List<GeoPoint> points) {
		GeoPoint retPoint=null;
		double currMinDis = Integer.MAX_VALUE;
		for (GeoPoint point : points) {
			double pointDis=alignZero(this.p0.distance(point.point));
			if(pointDis < currMinDis) {
				retPoint = point;
				currMinDis = pointDis;
			}
		}
		
		return retPoint;
	}*/

	 public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPointList)  {

		
	        GeoPoint closestPoint = null;
	        double minDistance = Double.MAX_VALUE;
	        double geoPointDistance; // the distance between the "this.p0" to each point in the list

	        if (!geoPointList.isEmpty()) {
	            for (var geoPoint : geoPointList) {
	                geoPointDistance = this.p0.distance(geoPoint.point);
	                if (geoPointDistance < minDistance) {
	                    minDistance = geoPointDistance;
	                    closestPoint = geoPoint;
	                }
	            }
	        }
	        return closestPoint;
	    }
	


}
