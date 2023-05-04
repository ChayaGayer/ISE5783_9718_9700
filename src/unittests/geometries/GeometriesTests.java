/**
 * 
 */
package unittests.geometries;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import geometries.*;
import primitives.*;




/**
 * @author user1
 *
 */
class GeometriesTests {

	/**
	 * Test method for {@link geometries.Geometries#findIntsersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntsersections() 
	{
		
		try 
		{
			
			//=====Empty body collection (BVA)=====//
			Geometries collection= new Geometries();
			assertNull("An empty body collection must return null", collection.findIntersections(new Ray(new Vector(9,5,-1),new Point(-4, -3, 2))));
			
			//=====No cut shape (BVA)=====//
			Sphere sphere = new Sphere(new Point(1, 0, 0), 1); 
			Triangle triangle = new Triangle(new Point(-4,0,0), new Point(0, 0, 5), new Point(0, -5, 0));
			Plane plane = new Plane (new Point(0, 0, 1), new Point(1, 0, 0), new Point(4, 0, 2));
			
			collection.add(sphere, triangle, plane/*, tube ,cylinder*/);
		
			assertNull("No cut shape must return 0",collection.findIntersections(new Ray( new Vector(-10,-1,0),new Point(0, -8, 0))));
			
			//=====Only one shape is cut (BVA)=====//
			//the plane cut
			assertEquals("wrong number of intersactions", 1, collection.findIntersections(new Ray( new Vector(3.4,3,1.57),new Point(-0.8, -3, 1))).size());

			
			//=====Some (but not all) shapes are cut (EP)=====//
			//triangle and plane cut
			assertEquals("wrong number of intersactions", 2, collection.findIntersections(new Ray( new Vector(9,5,-1),new Point(-4, -3, 2))).size());
			

			
			//=====All shapes are cut (BVA)=====//
			assertEquals("wrong number of intersactions", 4, collection.findIntersections(new Ray( new Vector(6,3,0.5),new Point(-4, -3, 0))).size());

		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("dont need throws exceptions!!!");
		}
	
	}

}
