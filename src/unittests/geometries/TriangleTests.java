/**
 * 
 */
package unittests.geometries;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;
import primitives.Point;
import primitives.Ray;
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
@Test
public void testfindIntersections() 
{
    try
    {
    	Triangle triangle = new Triangle(new Point(0, 1, 0), new Point(2, 6, 0), new Point(5, 0, 0));
    

    // ============ Equivalence Partitions Tests ====================

    // TC01: The ray cuts the plane In front of the side of the triangle
    Ray ray = new Ray( new Vector(new Point(-2.68, 5.72, 0).getXyz()),new Point(6.94, -2.39, 0));
    assertNull("the intersection point is out of the triangle - need 0 intersections", triangle.findIntersections(ray));

    // TC02: The ray intersects the plane on which the triangle is in front of the vertex
    ray = new Ray(new Vector(new Point(2.54, 2.23, 0).getXyz()),new Point(-0.93, 6.2, 0));
    assertNull("the intersection point is out of the triangle - need 0 intersections", triangle.findIntersections(ray));

    // TC03: The ray cuts the plane within the boundaries of the triangle
    ray = new Ray( new Vector(new Point(4.09, -0.69, -2.3).getXyz()),new Point(-2.09, 2.69, 2.3));
    assertEquals("the intersection point is in the triangle - need 1 intersections", 1, triangle.findIntersections(ray).size());
    
   

    // =============== Boundary Values Tests ==================

    // TC11: The ray intersects on the side of the triangle
    ray = new Ray( new Vector(new Point(-0.15, 3.07, -2.14).getXyz()),new Point(4.26, -1.28, 2.14));
    assertNull("on the side - need 0 intersections", triangle.findIntersections(ray));
    
    // TC12: The ray intersects on one of the vertices of the triangle
    ray = new Ray( new Vector(new Point(1.3, 0.71, -1.44).getXyz()),new Point(3.7, -0.71, 1.44));
    assertNull("on the vertex - need 0 intersections", triangle.findIntersections(ray));
    
    // TC13: The ray On the straight line continuing the side of the triangle
    ray = new Ray( new Vector(new Point(2.97, 1.28, 0).getXyz()),new Point(3.86, -4.95, 0));
    assertNull("the intersection point is out of the triangle - need 0 intersections", triangle.findIntersections(ray));

    }
    catch(Exception ex)
    {
    	ex.printStackTrace();
    	fail("not need throws exception!");
    }

}
}
