package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public interface LightSource {

    /**
     * @param p point on the geometry
     * @return intensity color on that point
     */
    public Color getIntensity(Point p);

    /**
     * @param p point on the geometry
     * @return the vector between p and position point
     */
    public Vector getL(Point p);


	/**
	 * Gets the distance between the light-source and the given point 
	 * @param point the given point 
	 * @return The distance between the light-source and the given point
	 */
	//public double getDistance(Point point);

	public double getDistance(Point point);

}