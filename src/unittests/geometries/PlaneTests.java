/**
 * 
 */
package unittests.geometries;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import primitives.Ray;
import geometries.Plane;
import primitives.Point;
import primitives.Vector;
import static primitives.Util.isZero;

/**
 * @author chaya gayer 214309718, shira gayer 214309700
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
    @Test
	public void testfindIntersections() 
	{
		try
		{
			Plane myPlane = new Plane(new Point(0,5,0), new Point(-5,0,0), new Point(0,0,3));
			// =============== Boundary Values Tests ==================
			
			//Ray is parallel to the plane
			// TC01: the ray included in the plane
			Ray myRay= new Ray(new Vector(-5,0,0),new Point(0,5,0));//the plane include this ray
			assertNull("A included ray has zero intersection points", myPlane.findIntersections(myRay));
			// TC02: the ray not included in the plane
			myRay= new Ray( new Vector(5,0,0) ,new Point(0,-5,0));//the plane included this ray
			assertNull("An un included ray has zero intersection points", myPlane.findIntersections(myRay));
			
			//Ray is orthogonal to the plane
			// TC03: before the plane
			myRay= new Ray( new Vector(-3,3,5),new Point(2,4,0));//the ray is orthogonal to the plane
			assertEquals("Ray is orthogonal to the plane and starts before the plane",1, myPlane.findIntersections(myRay).size());
			// TC04: at the plane
			myRay= new Ray( new Vector(-3,3,5),new Point(-5,0,0));//the ray is orthogonal to the plane
			assertNull("Ray is orthogonal to the plane and starts at the plane", myPlane.findIntersections(myRay));
			// TC05: after the plane
			myRay= new Ray(new Vector(-3,3,5),new Point(-7,2,4));//the ray is orthogonal to the plane
			assertNull("Ray is orthogonal to the plane and starts after the plane",myPlane.findIntersections(myRay));
			
			//Ray is neither orthogonal nor parallel to and begins at the plane
			// TC06:
			myRay= new Ray( new Vector(1,0,0),new Point(-1,-1,0));//the ray isn't orthogonal or parallel to the plane
			assertNull("Ray is neither orthogonal nor parallel to and begins at reference point in the plane", myPlane.findIntersections(myRay));
			
			//Ray is neither orthogonal nor parallel to the plane and begins in
			//the same point which appears as reference point in the plane
			// TC07:
			myRay= new Ray( new Vector(-5,4,-3),new Point(0,0,3));//the ray isn't orthogonal or parallel to the plane but not intersects the plane
			assertNull("Ray is neither orthogonal nor parallel to and begins at the plane", myPlane.findIntersections(myRay));
			
			// ============ Equivalence Partitions Tests ================
			// TC08: The Ray must be neither orthogonal nor parallel to the plane
			//Ray does not intersect the plane
			myRay= new Ray( new Vector(-3,-7,0),new Point(1,2,0));
			assertNull("Ray is neither orthogonal nor parallel but doesnt intersects the plane", myPlane.findIntersections(myRay));
			
			// TC09:
			// Ray intersects the plane
			myRay= new Ray( new Vector(-5.75,3.57,0),new Point(4,3,0));//the ray isn't orthogonal or parallel to the plane and intersects the plane
			assertEquals("Ray is neither orthogonal nor parallel and intersects the plane ",1, myPlane.findIntersections(myRay).size());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
        	fail("not need throws exception!");
		}
	}
}