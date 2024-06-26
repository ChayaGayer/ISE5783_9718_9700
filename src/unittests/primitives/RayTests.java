/**
 * 
 */
package unittests.primitives;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;


import org.junit.Test;

import primitives.*;

public class RayTests {


	/**
	 * Test method for {@link primitives.Ray#findClosestPoint(java.util.List)}.
	 */
	@Test
	public void testFindClosestPoint() {
		List<Point> pointList = new LinkedList<>();

        Point p1 = new Point(1, 1, 1);
        Point p2 = new Point(2, 2, 2);
        Point p3 = new Point(3, 3, 3);

        pointList.add(p1);
        pointList.add(p2);
        pointList.add(p3);

        Vector vector = new Vector(0, -0.5, 0);

        // ============ Equivalence Partitions Tests ==============
        //TC01: The closest point is in the middle of the list
        Ray ray1 = new Ray( vector,new Point(2, 2.5, 2));
        assertEquals(p2, ray1.findClosestPoint(pointList), "The point in the middle");

        // =============== Boundary Values Tests ==================
        //TC10: The closest point is the first point in the list
        Ray ray2 = new Ray( vector,new Point(1, 1.25, 1));
        assertEquals(p1, ray2.findClosestPoint(pointList), "The point is the first one");

        //TC11: The closest point is the last point in the list
        Ray ray3 = new Ray( vector,new Point(3, 3.5, 3));
        assertEquals(p3, ray3.findClosestPoint(pointList), "The point is the last one");

        //TC12: The list is null
        pointList.clear();
        assertNull(ray3.findClosestPoint(pointList), "The list is empty");
    }
	
	
}
