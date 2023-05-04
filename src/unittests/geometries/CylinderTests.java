/**
 * 
 */
package unittests.geometries;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import geometries.Cylinder;

/**
 * @author chaya gayer 214309718, shira gayer 214309700
 *
 */
/**
 * Testing Cylinder Class
 */
public class CylinderTests {

	/**
	 * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
	 */
	@Test
	public void testGetNormal() {
		
		Cylinder cyl = new Cylinder(new Ray(new Vector(0,0,1),new Point(Double3.ZERO)),5, 3); //cylinder:  x^2 + y^2 = 25  and  0 <= z <= 3
        
		// ============ Equivalence Partitions Tests ==============
        // TC01: Test for points on the cylinder's side
        assertEquals("Bad normal to cylinder - for points on the cylinder's side", new Vector(1,0,0), cyl.getNormal(new Point(5, 0, 2.5)));
        // TC02: Test for points on the cylinder's first base (where the axis starts)
        assertEquals("Bad normal to cylinder - for points on the cylinder's first base", new Vector(0,0,-1), cyl.getNormal(new Point(1, 2, 0)));
        assertEquals("Bad normal to cylinder - for points on the cylinder's first base (on the axis' starting point)", new Vector(0,0,-1), cyl.getNormal(new Point(0,0,0)));    	
        // TC03: Test for points on the cylinder's second base (where the axis "ends", by the height)
        assertEquals("Bad normal to cylinder - for points on the cylinder's second base", new Vector(new Double3(0,0,1)), cyl.getNormal(new Point(1, 2, 3)));
        assertEquals("Bad normal to cylinder - for points on the cylinder's second base (on the axis' 'end' point)", new Vector(0,0,1), cyl.getNormal(new Point(0, 0, 3)));
	    
        // =============== Boundary Values Tests ==================
        // TC10: Test for points on the border between the cylinder's side and first base (where the axis starts) - decision: the normal points as if it's a point on the cylinder's side!
        assertEquals("Bad normal to cylinder - for points on the cylinder's first base", new Vector(0,1,0), cyl.getNormal(new Point(0, 5, 0)));
        // TC11: Test for points on the border between the cylinder's side and second base (where the axis "ends", by the height) - decision: the normal points as if it's a point on the cylinder's side!
        assertEquals("Bad normal to cylinder - for points on the cylinder's second base", new Vector(0,1,0), cyl.getNormal(new Point(0, 5, 3)));
	}
	@Test
	public void testFindIntersections() {
		
	}

	
}
