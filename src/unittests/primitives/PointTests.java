/**
 * 
 */
package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.Test;

import primitives.Point;
import primitives.Vector;

/**
 * @author chaya gayer 214309718, shira gayer 214309700
 *
 */

class PointTests {

    /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}.
     */
    Point p=new Point(1,1,1);
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(new Point(2, 3, 4),p.add(new Vector(1, 2, 3)), //
                "Wrong point add");


        // =============== Boundary Values Tests ==================
        // there are no boundary tests
    }

    /**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}.
     */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(p, new Point(2, 3, 4).subtract(new Point(1, 2, 3)), //
                "Wrong point subtract");

        // =============== Boundary Values Tests ==================
        // TC11: test subtracting same point
        assertThrows(IllegalArgumentException.class, () -> p.subtract(p), //
                "Subtract P from P must throw exception");
    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
     */
    @Test
    void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(14, new Point(1, 1, 1).distanceSquared(new Point(2, 3, 4)), 0.0001, //
                "Wrong squared distance between the point and itself");

        // =============== Boundary Values Tests ==================
        // TC11: test distance with the same point
        assertEquals(0, new Point(1, 2, 3).distanceSquared(new Point(1, 2, 3)), 0.0001, //
                "Wrong squared distance between the point and itself");
    }

    /**
     * Test method for {@link primitives.Point#distance(primitives.Point)}.
     */
    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(Math.sqrt(14), new Point(1, 1, 1).distance(new Point(2, 3, 4)), 0.0001, //
                "Wrong distance between the point and itself");

        // =============== Boundary Values Tests ==================
        // TC11: test distance with the same point
        assertEquals(0, new Point(1, 2, 3).distance(new Point(1, 2, 3)), 0.0001, //
                "Wrong distance between the point and itself");
    }
}


