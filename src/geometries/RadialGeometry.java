package geometries;


public abstract class RadialGeometry implements Geometry {
	protected double Radius;
/**
 * constructor for the radius
 * @param Radius
 */
	public RadialGeometry( double Radius) {
		this.Radius=Radius;
	}

}
