package geometries;
import java.util.List;
import primitives.*;

public interface Intersectable 
{
	/**
	 * Finding intersection-points with a given ray
	 * @param ray A ray
	 * @return All the intersection-points of this geometry and the given ray
	 */
	 List<Point> findIntersections(Ray ray);
	
}


