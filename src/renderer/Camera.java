/**
 * 
 */
package renderer;
import static primitives.Util.*;

import java.util.MissingResourceException;

import primitives.*;
/**
 * @author chaya gayer ,shira gayer
 *
 */
public class Camera 
{
	private Point p0;    //location
	private Vector vUp, vTo, vRight; 
	private double width, height, distance;
	/**
	 * point p0
	 * @return p0
	 */
	
	public Point getP0() {
		return p0;
	}
	/**
	 * vector up
	 * @return vUp
	 */
	public Vector getvUp() {
		return vUp;
	}
	/**
	 * vector To
	 * @return vTo
	 */
	public Vector getvTo() {
		return vTo;
	}
	/**
	 * vector right
	 * @return vRight
	 */
	public Vector getvRight() {
		return vRight;
	}
	/**
	 * the width
	 * @return width
	 */
	public double getWidth() {
		return width;
	}
	/**
	 * height
	 * @return height
	 */
	public double getHeight() {
		return height;
	}
	/**
	 * distance
	 * @return distance
	 */
	public double getDistance() {
		return distance;
	}
	/**
	 * 
	 * @param p0
	 * @param vTo
	 * @param vUp
	 *  Camera constructor: builds a camera by moving a starting point vector, and viewing a vector up.
	 *  View vector and up vector must be orthogonal to each other, and if not, an exception is thrown.
	 */
	public Camera(Point p0, Vector vTo, Vector vUp) {
		if(!isZero(vUp.dotProduct(vTo)))
			throw new IllegalArgumentException();
		this.p0 = p0;
		this.vUp = vUp.normalize();
		this.vTo = vTo.normalize();
		this.vRight=(vTo.crossProduct(vUp)).normalize();
	}
	/**
	 * Setting the size of the camera display: sets the size of the camera by moving the width and height.
	 * @param width
	 * @param height
	 * @return
	 */
	public Camera setVPSize(double width, double height)
	{
		this.width=width;
		this.height=height;
		return this;	
	}
	/**
	 * Setting the distance of the camera display: defines the distance of the camera display from a starting point.
	 * @param distance
	 * @return
	 */
	public Camera setVPDistance(double distance) {
		this.distance=distance;
		return this;
	}
	/**
	 * Construction of a ray: produces a ray according to the position of a display point on the camera display 
	 * and uses the use of the appropriate vector according to the position and angles of the camera.
	 * @param nX
	 * @param nY
	 * @param j
	 * @param i
	 * @return
	 * @throws MissingResourceException
	 */
	public Ray constructRay(int nX, int nY, int j, int i)throws MissingResourceException
	{
		 
			if(alignZero(width)<=0 || alignZero(height)<=0 || alignZero(distance)<=0)
				throw new MissingResourceException("The view-plane's fields saved in the camera (- width, height and distance) must be updated to a positive number", "double", null);
			
			Point pCenter=this.p0.add(this.vTo.scale(this.distance));
			double rY=alignZero(this.height/nY);
			double rX=alignZero(this.width/nX);
		    double yI=alignZero((i-0.5*(nY-1))*rY);
		    double xJ=alignZero((j-0.5*(nX-1))*rX);
		    
		    Point pIJ=pCenter;
		    if (!isZero(xJ))
		    	pIJ = pIJ.add(vRight.scale(xJ));
		    if (!isZero(yI))
		    	pIJ = pIJ.add(vUp.scale(-yI));
		    

		    return new Ray( pIJ.subtract(this.p0),this.p0);
		}
		

		
	}




