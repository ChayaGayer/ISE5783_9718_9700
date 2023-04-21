package geometries;

//abstract class for the RadialGeometry
public abstract class RadialGeometry implements Geometry {
	protected final double radius;
/**
 * constructor for the radius
 * @param Radius
 */
	public RadialGeometry( double Radius) {
		this.radius=Radius;
	}

}
