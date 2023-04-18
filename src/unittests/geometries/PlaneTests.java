/**
 * 
 */
package unittests.geometries;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import geometries.Plane;
import primitives.Point;
import primitives.Vector;
import static primitives.Util.isZero;

/**
 * @author user1
 *
 */




class PlaneTests {
    /**
     * Test method for {@link Plane#getNormal()} .
     */
    @Test
    void testGetNormal() {
        Point p1= new Point(1,2,0);
        Point p2= new Point(4,-9,0);
        Point p3= new Point(1,0,8);
        Plane p= new Plane(p1,p2, p3);
        Vector v1=p1.subtract(p2);
        Vector v2=p2.subtract(p3);
        Vector v3=p3.subtract(p1);
        Vector n=p.getNormal(p1);
        assertTrue(isZero(v1.dotProduct(n)),"ERROR: incorrect normal to plane");//if the dot product== 0, it's really the normal to the plane
        assertTrue(isZero(v2.dotProduct(n)),"ERROR: incorrect normal to plane");//if the dot product== 0, it's really the normal to the plane
        assertTrue(isZero(v3.dotProduct(n)),"ERROR: incorrect normal to plane");//if the dot product== 0, it's really the normal to the plane
    }
    /** Test method for {@link geometries.Plane#Plane(primitives.Point,primitives.Point,primitives.Point)}. */
    @Test
    public void testConstructor(){
        // =============== Boundary Values Tests ==================
        // TC01:The points are on the same line
        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(new Point(1, 2, 3), new Point(2, 4, 6), new Point(4, 8, 12)), //
                "Constructed a plane with points that are in the same line");
        //TC02:The first and second points are converge
              assertThrows(IllegalArgumentException.class, //
                   () -> new Plane(new Point(1, 2, 3), new Point(1, 2, 3), new Point(1, 8, 12)), //
                      "Constructed a plane with first and second points are converge");
    }
}