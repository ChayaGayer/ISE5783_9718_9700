
package geometries;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;;
/**
 * an interface for the geometry
 * @author chaya gayer 214309718, shira gayer 214309700
 *
 */
public abstract class Geometry extends Intersectable 
{
	protected Color emission=Color.BLACK;

	private Material material = new Material();
	
	/**
	 * Getter material
	 * @return Geometry's material
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * Setter to the geometry's material
	 * @param material the geometry's material
	 * @return The updated geometry
	 */
	public Geometry setMaterial(Material material) {
		this.material = material;
		return this;
	}
	
/**
 *  Method that get a point and return the normal vector at this point
 * @param p
 * @return
 */
public abstract Vector getNormal(Point p);

/**
 * Getter emission
 * @return Geometry's emission
 */
public Color getEmission() {
	return emission;
}

/**
 * Setter to the geometry's emission-light
 * @param emission the geometry's emission-light
 * @return The updated geometry
 */
public Geometry setEmission(Color emission) {
	this.emission = emission;
	return this;
}
}