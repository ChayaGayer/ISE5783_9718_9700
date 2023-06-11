package renderer;

import java.util.List;
import static primitives.Util.*;
import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import lighting.SpotLight;
import primitives.Color;
import primitives.Double3;
import primitives.Material;
import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
import scene.Scene;

public class RayTracerBasic extends RayTracerBase
{
	private static final double DELTA = 0.1;
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;
	 private static final Double3 INIT_CALC_COLOR_K = Double3.ONE;

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        return intersections == null ? scene.background : calcColor(ray.findClosestGeoPoint(intersections), ray);
    }
    /**
    
    /**
     * Calculates reflected ray and refraction ray
     *
     * @param geoPoint geometry point
     * @param ray      ray
     * @return color
     */
    private Color calcGlobalEffects(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Material material = geoPoint.geometry.getMaterial();
        Ray reflectedRay = constructReflectionRay(geoPoint, geoPoint.geometry.getNormal(geoPoint.point), ray.getDir());
        Ray refractedRay = constructRefractionRay(geoPoint, geoPoint.geometry.getNormal(geoPoint.point), ray.getDir());
        return calcGlobalEffects(geoPoint, level, color, material.kR, k, reflectedRay)
                .add(calcGlobalEffects(geoPoint, level, color, material.kT, k, refractedRay));
    }

    /**
     * TODO
     * @param geoPoint
     * @param level
     * @param color
     * @param kx
     * @param k
     * @param ray
     * @return
     */
    private Color calcGlobalEffects(GeoPoint geoPoint, int level, Color color, Double3 kx, Double3 k, Ray ray) {
        Double3 kkx = kx.product(k);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
        GeoPoint reflectedPoint = findClosestIntersection(ray);
        if (reflectedPoint != null) {
            color = color.add(calcColor(reflectedPoint, ray, level - 1, kkx).scale(kx));
        }
        return color;
    }

    /**
     * function calculates specular color
     *
     * @param material    material of geometry
     * @param normal      normal of geometry
     * @param lightVector light vector
     * @param nl          dot product of normal and light vector
     * @param vector      direction of ray
     * @return specular color
     */
    private Double3 calcSpecular(Material material, Vector normal, Vector lightVector, double nl, Vector vector) {
        Vector reflectedVector = lightVector.subtract(normal.scale(2 * nl));
        double cosTeta = alignZero(-vector.dotProduct(reflectedVector));
        return cosTeta <= 0 ? Double3.ZERO : material.kS.scale(Math.pow(cosTeta, material.nShininess));

    }

    /**
     * function calculates diffusive color
     *
     * @param material material of geometry
     * @param nl dot product of normal and light vector
     * @return diffusive color
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(Math.abs(nl));
    }

    /**
     * function calculates color of point
     *
     * @param geoPoint point to color
     * @return color
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(geoPoint, ray, k);
        return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, ray, level, k));
    }

    
    

    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray,Double3 k) 
    {
        Color color = geoPoint.geometry.getEmission();
        Vector vector = ray.getDir();
        Vector normal = geoPoint.geometry.getNormal(geoPoint.point);
        double nv = alignZero(normal.dotProduct(vector));
        if (nv == 0)
            return color;
        Material material = geoPoint.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector lightVector = lightSource.getL(geoPoint.point);
            double nl = alignZero(normal.dotProduct(lightVector));
            if (nl * nv > 0)
            {
                //if (unshaded(geoPoint, lightVector, normal, lightSource)) {
            	Double3 ktr = transparency(geoPoint,lightSource,lightVector,normal);
            	if(!ktr.product(k).lowerThan(MIN_CALC_COLOR_K))
            	{
            		Color iL=lightSource.getIntensity(geoPoint.point).scale(ktr);
            		color=color.add(iL.scale(calcDiffusive(material, nl)),iL.scale(calcSpecular(material, normal, lightVector, nl, vector)));
            	}
            	
            }
        }
        return color;
    }


    
    private boolean unshaded(GeoPoint geoPoint, Vector l, Vector n, LightSource lightSource) {
        Ray lightRay = new Ray(geoPoint.point, l.scale(-1), n);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

        if (intersections != null) {
            double distance = lightSource.getDistance(geoPoint.point);
            for (GeoPoint intersection : intersections) {
                if (intersection.point.distance(geoPoint.point) < distance)
                    return false;
            }
        }
        return true;
    }
    /**
     * function will construct a reflection ray
     *
     * @param geoPoint geometry point to check
     * @param normal   normal vector
     * @param vector   direction of ray to point
     * @return reflection ray
     */
    private Ray constructReflectionRay(GeoPoint geoPoint, Vector normal, Vector vector) {
        Vector reflectedVector = vector.subtract(normal.scale(2 * vector.dotProduct(normal)));
        return new Ray(geoPoint.point, reflectedVector, normal);
    }

    /**
     * function will construct a refraction ray
     *
     * @param geoPoint geometry point to check
     * @param normal   normal vector
     * @param vector   direction of ray to point
     * @return refraction ray
     */
    private Ray constructRefractionRay(GeoPoint geoPoint, Vector normal, Vector vector) {
        return new Ray(geoPoint.point, vector, normal);
    }

    /**
     * Find the closest intersection point with a ray.
     *
     * @param ray The ray to checks intersections with.
     * @return The closest intersection point with the ray.
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null) return null;
        return ray.findClosestGeoPoint(intersections);
    }

    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return scene.ambientLight.getIntensity().add(
                calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, INIT_CALC_COLOR_K));
    }
    /**
	 * Gets the discount-factor of the half or full shading on the intersection-geoPoint regarding one of the light-sources.
	 * @param light the light-source
	 * @param l the light-vector of the light-source
	 * @param n the intersected-geometry's normal
	 * @param geoPoint the intersection-geoPoint
	 * @return The discount-factor of the shading on this intersection-geoPoint
	 */
	private Double3 transparency(GeoPoint gp, LightSource light,Vector l, Vector n)
	{
		Vector lightDiraction=l.scale(-1);
		Ray lightRay=new Ray(gp.point, lightDiraction,n);
		List<GeoPoint> intersections=scene.geometries.findGeoIntersections(lightRay);
		if(intersections==null)
		{
			return Double3.ONE;
		}
		double distance=light.getDistance(gp.point);
		Double3 ktr = Double3.ONE;
		for(GeoPoint geoPoint : intersections)
		{
			if(alignZero(geoPoint.point.distance(gp.point))<=distance)
			{
				ktr=ktr.product(geoPoint.geometry.getMaterial().kT);
				if (ktr.lowerThan(MIN_CALC_COLOR_K))
					return Double3.ZERO;
			}
		}
		return ktr;
	}
}