
package primitives;
import static primitives.Util.*;
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


}
