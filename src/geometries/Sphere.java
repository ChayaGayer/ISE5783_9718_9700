package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

/**
 * Class Sphere is the basic class representing a sphere in Cartesian
 * 3-Dimensional coordinate system.
 * 
 */

public class Sphere extends RadialGeometry {
	private final Point center;

	/**
	 * Sphere constructor based on a point and radius.
	 * 
	 * @param p   center point
	 * @param rad radius
	 */
	public Sphere(Point p, double rad) {
		super(rad);
		center = p;
		if (cbr) {
			double minX = center.getX() - radius;
			double maxX = center.getX() + radius;
			double minY = center.getY() - radius;
			double maxY = center.getY() + radius;
			double minZ = center.getZ() - radius;
			double maxZ = center.getZ() + radius;
			this.box = new Border(minX, minY, minZ, maxX, maxY, maxZ);
		}
	}

	/**
	 * Get center point of sphere
	 * 
	 * @return center point
	 */
	public Point getPoint() {
		return center;
	}

	@Override
	public String toString() {
		return "" + center + ", " + radius;
	}

	@Override
	public Vector getNormal(Point p) {
		return p.subtract(center).normalize();
	}

	@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double dis) {
		Point p0 = ray.getP0(); // ray's starting point
        Point O = this.center; //the sphere's center point
        Vector V = ray.getDir(); // "the v vector" from the presentation

        // if p0 on center, calculate with line parametric representation
        // the direction vector normalized.
        if (O.equals(p0)) {
            Point newPoint = p0.add(ray.getDir().scale(this.radius));
            return List.of(new GeoPoint(this,newPoint));
        }

        Vector U = O.subtract(p0);
        double tm = V.dotProduct(U);
        double d = Math.sqrt(U.lengthSquared() - tm * tm);
        if (d >= this.radius) {
            return null;
        }

        double th = Math.sqrt(this.radius * this.radius - d * d);
        double t1 = tm - th;
        double t2 = tm + th;

        if (t1 > 0 && t2 > 0) {
            Point p1 = ray.getPoint(t1);
            Point p2 = ray.getPoint(t2);
            return List.of(new GeoPoint(this,p1),new GeoPoint(this,p2));
        }

        if (t1 > 0) {
            Point p1 = ray.getPoint(t1);
            return List.of(new GeoPoint(this,p1));
        }

        if (t2 > 0) {
            Point p2 = ray.getPoint(t2);
            return List.of(new GeoPoint(this,p2));
        }
        return null;
    
}

}