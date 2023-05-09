package unittests.renderer;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;


import geometries.*;
import primitives.*;
import renderer.*;




/**
 * Testing Integrations
 */
public class IntegrationTests {
	

	/**
	 * Helping function: Gets a scene and constructs all rays through 
	 * the pixels in the view plane throughout the geometries
	 * @param nX number of columns in the view plane
	 * @param nY number of rows in the view plane
	 * @param geo the Intersectable object, representing the scene's geometry/ies
	 * @param cam the camera
	 * @return the number of intersection points in this scene
	 */
	private int helpingconstructRayThroughPixel(int nX, int nY, Intersectable geo, Camera cam) {
		int intersectionsNum=0;
		for (int i=0; i<nX; i++)
			for (int j=0; j<nY; j++) {
				List<Point> intersections=geo.findIntersections(cam.constructRay(nX, nY, j, i));
				if(intersections!=null)
					intersectionsNum += intersections.size();
			}
		
		return intersectionsNum;
	}
	

	
	@Test
	public void testConstructRayThroughPixelWithSphere() {
		// TC01: Sphere's in front of camera; intersects only through the middle pixel in the view plane
		Camera cam=new Camera(new Point(0,0,0), new Vector(0,0,-1), new Vector(0,1,0)).setVPSize(3, 3).setVPDistance(1);
		Sphere sphere=new Sphere(new Point(0,0,-3), 1);
		assertEquals("Sphere's in front of camera - intersects only through the middle pixel in the view plane; 2 points expected", 2, helpingconstructRayThroughPixel(3,3,sphere,cam));
		
		// TC02: Sphere's in front of camera; intersects through all the pixels in the view plane
		cam=new Camera(new Point(0,0,0.5), new Vector(0,0,-1), new Vector(0,1,0)).setVPSize(3, 3).setVPDistance(1);
		sphere=new Sphere(new Point(0,0,-2.5), 2.5);
		assertEquals("Sphere's in front of camera - intersects through all the pixels in the view plane; 18 points expected", 18, helpingconstructRayThroughPixel(3,3,sphere,cam));
		
		// TC03: Sphere's in front of camera; intersects through all the pixels in the view plane except 4 corners
		sphere=new Sphere(new Point(0,0,-2), 2);
		assertEquals("Sphere's in front of camera - intersects through all the pixels in the view plane except 4 corners; 10 points expected", 10, helpingconstructRayThroughPixel(3,3,sphere,cam));
		
		// TC04: Camera's inside the Sphere; intersects through all the pixels in the view plane
		cam=new Camera(new Point(0,0,0), new Vector(0,0,-1), new Vector(0,1,0)).setVPSize(3, 3).setVPDistance(1);
		sphere=new Sphere(new Point(0,0,-2), 4);
		assertEquals("Camera's inside the Sphere - intersects through all the pixels in the view plane; 9 points expected", 9, helpingconstructRayThroughPixel(3,3,sphere,cam));
		
		// TC05: Sphere's behind the camera; no intersections
		sphere=new Sphere(new Point(0,0,1), 0.5);
		assertEquals("Sphere's behind the camera - no intersections; 0 points expected", 0, helpingconstructRayThroughPixel(3,3,sphere,cam));
		
	}
	
	
	@Test
	public void testConstructRayThroughPixelWithPlane() {
		
		Camera cam=new Camera(new Point(0,0,0), new Vector(0,0,-1), new Vector(0,1,0)).setVPSize(3, 3).setVPDistance(1);
		
		// TC01: Plane's straight in front of camera - orthogonal to it's vTo direction; intersects through all the pixels in the view plane
		Plane plane=new Plane(new Point(0,0,-5), new Vector(0,0,1));
		assertEquals("Plane's straight in front of camera - orthogonal to it's vTo direction - intersects through all the pixels in the view plane; 9 points expected", 9, helpingconstructRayThroughPixel(3,3,plane,cam));
		plane=new Plane(new Point(0,0,-5), new Vector(0,0,-1));
		assertEquals("Plane's straight in front of camera - orthogonal to it's vTo direction - intersects through all the pixels in the view plane; 9 points expected", 9, helpingconstructRayThroughPixel(3,3,plane,cam));
	
		// TC02: Plane's tilted in front of camera - not orthogonal to it's vTo direction; intersects through all the pixels in the view plane
		plane=new Plane(new Point(0,0,-5), new Vector(0,-0.5,1));
		assertEquals("Plane's tilted in front of camera - not orthogonal to it's vTo direction - intersects through all the pixels in the view plane; 9 points expected", 9, helpingconstructRayThroughPixel(3,3,plane,cam));
		plane=new Plane(new Point(0,0,-5), new Vector(0,0.5,-1));
		assertEquals("Plane's tilted in front of camera - not orthogonal to it's vTo direction - intersects through all the pixels in the view plane; 9 points expected", 9, helpingconstructRayThroughPixel(3,3,plane,cam));
		
		// TC03: Plane's tilted in front of camera - not orthogonal to it's vTo direction; not intersecting through all the pixels in the view plane
		plane=new Plane(new Point(0,0,-5), new Vector(0,-5,1));
		assertEquals("Plane's tilted in front of camera - not orthogonal to it's vTo direction - not intersecting through all the pixels in the view plane; 6 points expected", 6, helpingconstructRayThroughPixel(3,3,plane,cam));
		plane=new Plane(new Point(0,0,-5), new Vector(0,5,-1));
		assertEquals("Plane's tilted in front of camera - not orthogonal to it's vTo direction - not intersecting through all the pixels in the view plane; 6 points expected", 6, helpingconstructRayThroughPixel(3,3,plane,cam));
	}
	
	
	@Test
	public void testConstructRayThroughPixelWithTriangle() {
		
		Camera cam=new Camera(new Point(0,0,0), new Vector(0,0,-1), new Vector(0,1,0)).setVPSize(3, 3).setVPDistance(1);
		
		// TC01: Triangle's in front of camera; intersects only through the middle pixel in the view plane
		Triangle triangle=new Triangle(new Point(0, 1, -2),new Point(1, -1, -2),new Point(-1, -1, -2));
		assertEquals("Triangle's in front of camera - intersects only through the middle pixel in the view plane; 1 point expected", 1, helpingconstructRayThroughPixel(3,3,triangle,cam));
		
		// TC02: Triangle's in front of camera; intersects through the middle pixel in the view plane and the one above it
		triangle=new Triangle(new Point(0, 20, -2),new Point(1, -1, -2),new Point(-1, -1, -2));
		assertEquals("Triangle's in front of camera - intersects only through the middle pixel in the view plane and the one above it; 2 points expected", 2, helpingconstructRayThroughPixel(3,3,triangle,cam));
				
	}

}
