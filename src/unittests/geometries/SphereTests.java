/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import geometries.Sphere;

/**
 * @author user1
 *
 */

public class SphereTests {

/**
* Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
*/
@Test
void testGetNormal() {
     // ============ Equivalence Partitions Tests ==============
     // TC01: There is a simple single test here - using a quad
Sphere sphere = new Sphere(new Point(0,0,0), 2);
     // ensure there are no exceptions
     assertDoesNotThrow(() -> sphere.getNormal(new Point(0, 0, 2)), "");
     // generate the test result
     Vector result = sphere.getNormal(new Point(0, 0, 2));
     // ensure |result| = 1
     assertEquals(1, result.length(), 0.00000001, "Sphere's normal is not a unit vector");
  }
}