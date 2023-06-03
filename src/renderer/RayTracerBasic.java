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
import primitives.Vector;
import scene.Scene;

public class RayTracerBasic extends RayTracerBase
{
	private static final double DELTA = 0.1;

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> points = scene.geometries.findGeoIntersections(ray);

        if (points != null) {
            GeoPoint closePoint = ray.findClosestGeoPoint(points);
            return calcColor(closePoint, ray);
        }
        //no points
        return scene.background;
    }
    /**
     * Get the color of an intersection point
     *
     * @param point point of intersection
     * @return Color of the intersection point
     */
    
    //}
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return geoPoint.geometry.getEmission().add(scene.ambientLight.getIntensity(), calcLocalEffects(geoPoint, ray));
    }
    

    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Color color = Color.BLACK;
        Vector vector = ray.getDir();
        Vector normal = gp.geometry.getNormal(gp.point);
        double nv = alignZero(normal.dotProduct(vector));
        if (nv == 0)
            return color;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector lightVector = lightSource.getL(gp.point);
            double nl = alignZero(normal.dotProduct(lightVector));
            if (nl * nv > 0) {
                if (unshaded(gp, lightVector, normal, lightSource, nv)) {
                    Color lightIntensity = lightSource.getIntensity(gp.point);
                    color = color.add(lightIntensity.scale(calcDiffusive(material, nl)), lightIntensity.scale(calcSpecular(material, normal, lightVector, nl, vector)));
                }
            }
        }
        return color;
    }


    private Double3 calcDiffusive(Material m, double nl) {
       if(nl<0)
    	   return m.kD.scale(-nl);
       else
    	   return m.kD.scale(nl);
    	   
    	   
    }

    private Double3 calcSpecular( Material m , Vector n, Vector l,  double nl, Vector v) 
    {
      return m.kS.scale(Math.pow(Math.max(0, v.scale(-1).dotProduct(l.subtract(n.scale(2*nl)))), m.nShininess));
       
    }
    private boolean unshaded(GeoPoint gp, Vector l, Vector n, LightSource lightSource, double nv) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector epsVector = n.scale(nv < 0 ? DELTA : -DELTA);
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray( lightDirection,point);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

        if (intersections != null) {
            double distance = lightSource.getDistance(gp.point);
            for (GeoPoint intersection : intersections) {
                if (intersection.point.distance(gp.point) < distance)
                    return false;
            }
        }

        return true;
    }

}