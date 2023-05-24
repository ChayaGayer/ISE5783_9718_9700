package lighting;

import primitives.*;

/**
 * Light's class.
 * Represents light by it's color's overall intensity. 
 */
abstract class Light {
	
	private Color intensity;

	/**
	 * Getter intensity
	 * @return the light's intensity
	 */
	public Color getIntensity() {
		return intensity;
	}

	/**
	 * Light's Constructor
	 * @param intensity  the light's intensity
	 */
	protected Light(Color intensity) {
		this.intensity = intensity;
	} 

}
