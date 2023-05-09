/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import geometries.Sphere;

/**
 * @author chaya gayer 214309718, shira gayer 214309700
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
@Test
public void testFindIntersections() {
    Sphere sphere = new Sphere(new Point(1, 0, 0), 1d);

    // ============ Equivalence Partitions Tests ==============

    // TC01: Ray's line is outside the sphere (0 points)
    assertNull("Ray's line out of sphere",
                        sphere.findIntersections(new Ray( new Vector(1, 1, 0),new Point(-1, 0, 0))));

    // TC02: Ray starts before and crosses the sphere (2 points)
    Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
    Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
    List<Point> result = sphere.findIntersections(new Ray( new Vector(3, 1, 0),new Point(-1, 0, 0) ));
                                                          
    assertEquals("Wrong number of points", 2, result.size());
    if (result.get(0).getXyz().getD1() > result.get(1).getXyz().getD1())
        result = List.of(result.get(1), result.get(0));
    assertEquals("Ray crosses sphere", List.of(p1, p2), result);

    // TC03: Ray starts inside the sphere (1 point)
    result = sphere.findIntersections(new Ray(new Vector(-1, -1, -2),new Point(1, 0.5, 0)));
    assertEquals("Wrong number of points", 1, result.size());
    
    // TC04: Ray starts after the sphere (0 points)
    assertNull("The ray starts after the sphere", sphere.findIntersections(new Ray(new Vector(1, 2, 0),new Point(4, 10, 0))));

    // =============== Boundary Values Tests ==================

    // ** Group: Ray's line crosses the sphere (but not the center)
    // TC11: Ray starts at sphere and goes inside (1 points)
    result = sphere.findIntersections(new Ray(new Vector(-1, 0, 1),new Point(2, 0, 0)));
    assertEquals("Wrong number of points", 1, result.size());
    
    // TC12: Ray starts at sphere and goes outside (0 points)
    assertNull("The ray starts at sphere and goes outside", sphere.findIntersections(new Ray(new Vector(1, 0, 0),new Point(3, 0, 0))));

    // ** Group: Ray's line goes through the center
    // TC13: Ray starts before the sphere (2 points)
    result = sphere.findIntersections(new Ray(new Vector(0, 1, 0),new Point(1, -2, 0)));
    assertEquals("Wrong number of points", 2, result.size());
    if (result.get(0).getXyz().getD1() > result.get(1).getXyz().getD1())
        result = List.of(result.get(1), result.get(0));
    assertEquals("Ray crosses sphere", List.of(new Point(1, 1, 0), new Point(1, -1, 0)), result);
    
    // TC14: Ray starts at sphere and goes inside (1 points)
    result = sphere.findIntersections(new Ray(new Vector(0, 1, 0),new Point(1, -1, 0)));
    assertEquals("Wrong number of points", 1, result.size());
    
    // TC15: Ray starts inside (1 points)
    result = sphere.findIntersections(new Ray(new Vector(4, 0, 0),new Point(0.5, 0, 0)));
    assertEquals("Wrong number of points", 1, result.size());
    
    // TC16: Ray starts at the center (1 points)
    result = sphere.findIntersections(new Ray(new Vector(2.52,-5.02, 0),new Point(1, 0, 0)));
    assertEquals("Wrong number of points", 1, result.size());
    
    // TC17: Ray starts at sphere and goes outside (0 points)
    result = sphere.findIntersections(new Ray(new Vector(1, 0, 0),new Point(2, 0, 0)));
    assertNull("Wrong number of points", result);
    assertNull("The ray starts at sphere and goes outside", sphere.findIntersections(new Ray(new Vector(0, 1, 0),new Point(1, 1, 0))));
    
    // TC18: Ray starts after sphere (0 points)	        
    assertNull("The ray starts  after sphere", sphere.findIntersections(new Ray(new Vector(0, 1, 0),new Point(1, 2, 0))));
    
    // ** Group: Ray's line is tangent to the sphere (all tests 0 points)
    // TC19: Ray starts before the tangent point
    assertNull("The ray starts before the tangent point", sphere.findIntersections(new Ray(new Vector(-2, -1, 0),new Point(-0.5,-0.5, 0))));

    // TC20: Ray starts at the tangent point
    assertNull("The ray starts at the tangent point", sphere.findIntersections(new Ray(new Vector(-1,-1, 0),new Point(1, 0, 1))));

    // TC21: Ray starts after the tangent point
    assertNull("The ray starts after the tangent point", sphere.findIntersections(new Ray(new Vector(0, -2, 1),new Point(1, 1, 1))));

    // ** Group: Special cases
    // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
    assertNull("The ray's line is outside, ray is orthogonal to ray start to sphere's center line", sphere.findIntersections(new Ray(new Vector(0, -2, 1),new Point(-0.5, 0, 0))));

}

}