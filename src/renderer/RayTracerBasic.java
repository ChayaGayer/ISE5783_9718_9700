package renderer;

import java.util.List;
import static primitives.Util.*;
import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import lighting.SpotLight;
import primitives.Color;
import primitives.Double3;
import primitives.Material;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

public class RayTracerBasic extends RayTracerBase{
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
    private Color calcColor(GeoPoint point, Ray ray)
    {/*if (point==null)
    	return Color.BLACK;
    else {*/
        return this.scene.ambientLight.getIntensity()
                
                .add(calcLocalEffects(point, ray));
    }
    //}
    

    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
    	Color color = gp.geometry.getEmission();
    	Vector v = ray.getDir (); Vector n = gp.geometry.getNormal(gp.point);
    	double nv = alignZero(n.dotProduct(v)); if (nv == 0) return color;
    	Material material = gp.geometry.getMaterial();

    	for (LightSource lightSource : scene.lights) {
    	Vector l = lightSource.getL(gp.point);
    	double nl = alignZero(n.dotProduct(l));
    	if (nl * nv > 0) { // sign(nl) == sing(nv)
    	Color iL = lightSource.getIntensity(gp.point);
    	color = color.add(iL.scale(calcDiffusive(material, nl)),

    	iL.scale(calcSpecular(material, n, l, nl, v)));

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


}