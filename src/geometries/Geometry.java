
package geometries;
import primitives.Point;
import primitives.Vector;;
/**
 * an interface for the geometry
 * @author user1
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