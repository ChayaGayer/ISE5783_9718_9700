
package geometries;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * Interface Geometry is an interface that represents any geometry in general.
 * 
 * @author Chaya Gayer , Shira Gayer 
 */

public abstract class Geometry extends Intersectable {
	/**
	 * emission light of the shapes
	 */
	protected Color emission = Color.BLACK;

	private Material material = new Material();

	/**
	 * get normal vector, perpendicular at the point
	 * 
	 * @param p point on the geometric surface
	 * @return normal vector
	 */
	public abstract Vector getNormal(Point p);

	/**
	 * getter for geometries emission light
	 * 
	 * @return emission
	 */
	public Color getEmission() {
		return emission;
	}

	/**
	 * a setter for geometries emission light
	 * 
	 * @param em color for the setting
	 * @return this geometry
	 */
	public Geometry setEmission(Color em) {
		emission = em;
		return this;
	}

	/**
	 * getter for geometry material
	 * 
	 * @return material
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * a setter for geometry material
	 * 
	 * @param mat material of the geometry
	 * @return this geometry
	 */
	public Geometry setMaterial(Material mat) {
		material = mat;
		return this;
	}

}