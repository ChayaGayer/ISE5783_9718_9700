/**
 * 
 */
package unittests.geometries;
import static org.junit.jupiter.api.Assertions.*;
import primitives.Point;
import primitives.Vector;
import static primitives.Util.isZero;
import org.junit.jupiter.api.Test;
import geometries.Triangle;

/**
 * @author chaya gayer 214309718, shira gayer 214309700
 *
 */



class TriangleTests {
 /** Test method for {@link geometries.Triangle#getNormal(primitives.Point)}. */
@Test
 void testGetNormal(){
	//
 // ============ Equivalence Partitions Tests ==============
 // TC01: There is a simple single test here - using a quad
	Triangle triangle=new Triangle(new Point(0,0,1),new Point(1,0,0),new Point(0,1,0));
 // ensure there are no exceptions
 assertDoesNotThrow(() -> triangle.getNormal(new Point(0, 0, 1)), "");
 // generate the test result
 Vector result = triangle.getNormal(new Point(0, 0, 1));
 // ensure |result| = 1
 assertEquals(1, result.lengthSquared(), 0.00000001, "Triangle's normal is not a unit vector");
 // ensure the result is orthogonal to all the edges
  assertTrue(isZero(result.dotProduct(new Point(1,0,0).subtract(new Point(0,0,1)))),
          "Triangle's normal is not orthogonal to one of the edges");
}
}
