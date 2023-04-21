/**
 * 
 */
package unittests.geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import geometries.Tube;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author chaya gayer 214309718, shira gayer 214309700
 *
 */



public class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	@Test
	
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		var tube = new Tube(1, new Ray(new Vector(0, 0, 1), new Point(0, 0, 1)));

		// Check that the normal is correct
		assertEquals("getNormal(Point3D) -The normal to the Tube is not correct ",
				new Vector(new Point(1, 0, 0).getXyz()).normalize(), tube.getNormal(new Point(1, 0, 6)));
		
		// =============== Boundary Values Tests ==================
		// Check when the point is in front of the head Ray
		assertEquals("getNormal() faild - point is in front of the head Ray!", new Vector(new Point(0, 1, 0).getXyz()),
				tube.getNormal(new Point(0, 1, 1)));
	}
}
