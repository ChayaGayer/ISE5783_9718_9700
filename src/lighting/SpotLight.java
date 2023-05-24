package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Util;
import primitives.Vector;

/**
 * Class spotlight
 *
 * @author
 */
/**
 * SpotLight's class. Extends the class PointLight.
 * Represents the spot light-source by inheriting it's "PointLight"'s fields intensity, position-point
 * and the light's discount-factors Kc, Kl and Kq, and adding the direction-vector field.
 */
public class SpotLight extends PointLight {

	private Vector direction;

	/**
	 * SpotLight's Constructor
	 * @param intensity the light's intensity
	 * @param position the light's position-point
	 * @param direction the light's direction
	 */
	public SpotLight(Color intensity, Point position, Vector direction) {
		super(intensity, position);
		this.direction=direction.normalize();
		// TODO Auto-generated constructor stub
	}

	public PointLight setNarrowBeam(int i) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public Color getIntensity(Point p) {
		
		return super.getIntensity(p).scale(Math.max(0, this.direction.dotProduct(super.getL(p))));
		
		
	}
}
