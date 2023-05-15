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
/**
 * The Camera class represents a virtual camera used for rendering images.
 * It defines the camera's location, orientation, size, distance, and provides methods for rendering and image manipulation.
 */
public class Camera {
    private Point p0; // The location of the camera
    private Vector vUp; // The up vector of the camera
    private Vector vTo; // The view vector of the camera
    private Vector vRight; // The right vector of the camera
    private double width; // The width of the camera display
    private double height; // The height of the camera display
    private double distance; // The distance of the camera display from the location
    private ImageWriter imageWriter; // The image writer used for output
    private RayTracerBase rayTracer; // The ray tracer used for rendering

    /**
     * Returns the location of the camera.
     *
     * @return The location of the camera.
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Returns the up vector of the camera.
     *
     * @return The up vector of the camera.
     */
    public Vector getvUp() {
        return vUp;
    }

    /**
     * Returns the view vector of the camera.
     *
     * @return The view vector of the camera.
     */
    public Vector getvTo() {
        return vTo;
    }

    /**
     * Returns the right vector of the camera.
     *
     * @return The right vector of the camera.
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     * Returns the width of the camera display.
     *
     * @return The width of the camera display.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of the camera display.
     *
     * @return The height of the camera display.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the distance of the camera display.
     *
     * @return The distance of the camera display.
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Constructs a Camera object with the specified location, view vector, and up vector.
     * The view vector and up vector must be orthogonal to each other, otherwise an IllegalArgumentException is thrown.
     *
     * @param p0   The location of the camera.
     * @param vTo  The view vector of the camera.
     * @param vUp  The up vector of the camera.
     * @throws IllegalArgumentException If the view vector and up vector are not orthogonal.
     */
    public Camera(Point p0, Vector vTo, Vector vUp) {
        if (!isZero(vUp.dotProduct(vTo)))
            throw new IllegalArgumentException("The view vector and up vector must be orthogonal");

        this.p0 = p0;
        this.vUp = vUp.normalize();
        this.vTo = vTo.normalize();
        this.vRight = vTo.crossProduct(vUp).normalize();
    }

    /**
     * Sets the size of the camera display.
     *
     * @param width  The width of the camera display.
     * @param height The height of the camera display.
     * @return The Camera object itself.
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Sets the distance of the camera display.
     *
     * @param distance The distance of the camera display.
     * @return The Camera object itself.
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
		
	/**

	Sets the ray tracer used for rendering the image.
	@param rayTracer The ray tracer object.
	@return The Camera object itself.
	*/
	public Camera setRayTracer(RayTracerBase rayTracer) {
	this.rayTracer = rayTracer;
	return this;
	}
	/**

	Sets the image writer used for output.
	@param imageWriter The image writer object.
	@return The Camera object itself.
	*/
	public Camera setImageWriter(ImageWriter imageWriter) {
	this.imageWriter = imageWriter;
	return this;
	}
	/**

	Renders the image using the configured ray tracer and image writer.

	@throws UnsupportedOperationException If the image writer or ray tracer is missing.
	*/
	public void renderImage() {
	if (this.imageWriter == null)
	throw new UnsupportedOperationException("Missing imageWriter");
	if (this.rayTracer == null)
	throw new UnsupportedOperationException("Missing rayTracerBase");

	for (int i = 0; i < this.imageWriter.getNy(); i++) {
	for (int j = 0; j < this.imageWriter.getNx(); j++) {
	Color color = castRay(j, i);
	this.imageWriter.writePixel(j, i, color);
	}
	}
	}

	/**

	Casts a ray through a specified pixel and returns the color of the intersection point.
	@param j The x-coordinate of the pixel.
	@param i The y-coordinate of the pixel.
	@return The color of the intersection point.
	*/
	private Color castRay(int j, int i) {
	Ray ray = constructRay(this.imageWriter.getNx(),this.imageWriter.getNy(),j,i);
	return this.rayTracer.traceRay(ray);
	}
	/**

	Prints a grid on top of the image, with equal square intervals.

	@param interval The interval between the grid's rows and columns.

	@param color The color of the grid.

	@throws MissingResourceException If the image writer is null.

	@throws IllegalArgumentException If the interval is not a divisor of both Nx and Ny.
	*/
	public void printGrid(int interval, Color color) throws MissingResourceException, IllegalArgumentException {
	if (this.imageWriter == null)
	throw new MissingResourceException("The render's field imageWriter mustn't be null", "ImageWriter", null);

	if (this.imageWriter.getNx() % interval != 0 || this.imageWriter.getNy() % interval != 0)
	throw new IllegalArgumentException("The grid is supposed to have squares, therefore the given interval must be a divisor of both Nx and Ny");

	for (int i = 0; i < this.imageWriter.getNx(); i++) {
	for (int j = 0; j < this.imageWriter.getNy(); j++) {
	if (i % interval == 0 || (i + 1) % interval == 0 || j % interval == 0 || (j + 1) % interval == 0)
	this.imageWriter.writePixel(i, j, color);
	}
	}
	//return this;
	}

	/**

	Writes the image to an output file.
	@throws MissingResourceException If the image writer is null.
	*/
	public void writeToImage() throws MissingResourceException {
	if (this.imageWriter == null)
	throw new MissingResourceException("The render's field imageWriter mustn't be null", "ImageWriter", null);
	this.imageWriter.writeToImage();
	//return this;
	}
}