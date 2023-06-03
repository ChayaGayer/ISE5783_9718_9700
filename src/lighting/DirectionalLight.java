package lighting;

import primitives.*;


/**
 * DirectionalLight's class. Extends the class Light and implements the interface-class LightSource.
 * Represents the directional light-source by inheriting it's "Light"'s field intensity and a direction-vector.
 */
public class DirectionalLight extends Light implements LightSource {

	
	private Vector direction;

	
	/**
	 * DirectionalLight's Constructor
	 * @param intensity the light's intensity
	 * @param direction the light's direction
	 */
	public DirectionalLight(Color intensity, Vector direction) {
		super(intensity);
		this.direction=direction.normalize();
		// TODO Auto-generated constructor stub
	}


	@Override
	public Color getIntensity(Point p) {
		return super.getIntensity();

	}


	@Override
	public Vector getL(Point p) {
		return this.direction;
	}
	@Override
	public double getDistance(Point p) {
		return Double.POSITIVE_INFINITY;
	}

}

