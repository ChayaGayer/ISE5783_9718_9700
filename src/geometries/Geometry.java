
package geometries;
import primitives.Point;
import primitives.Vector;;
/**
 * an interface for the geometry
 * @author chaya gayer 214309718, shira gayer 214309700
 *
 */
public interface Geometry 
{
/**
 *  Method that get a point and return the normal vector at this point
 * @param p
 * @return
 */
public Vector getNormal(Point p);

}