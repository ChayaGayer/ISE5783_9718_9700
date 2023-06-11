/**
 * 
 */
package unittests.renderer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.*;
import renderer.*;
import scene.Scene;
import primitives.*;

public class IceCream {

private Scene scene = new Scene("Test scene");

@Test
public void OurPicture() {
Camera camera = new Camera(new Point(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0));
camera.setVPDistance(1000).setVPSize(200,200);
scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.15)));
scene.geometries.add( //

		
new Sphere(new Point(25, -30, 100),30).setEmission(new Color(21,0, 81))
.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(20).setKt(0).setKr(0)),
new Triangle(new Point(-45, -20, 100), new Point(45, -20,100), new Point(0, 115, 150))
.setEmission(new Color(153,76,0)).setMaterial(new Material().setkD(0).setkS(0.8).setnShininess(6)),
new Sphere(new Point(-25, -30, 100),30).setEmission(new Color(java.awt.Color.BLACK))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30)),
new Sphere(new Point(0, -70, 100),30).setEmission(new Color(102, 32, 20))
.setMaterial(new Material().setkD(0.2).setkS(0.4).setnShininess(30).setKt(0).setKr(0))
);
		
new Plane(new Point(1500, 1500, 0),new Point(-1500, -1500, 3850), new Point(-1500, 1500, 0))
                    .setEmission(new Color(java.awt.Color.BLACK).reduce(5))
                    .setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(2000));
scene.lights.add(new DirectionalLight(new Color(java.awt.Color.darkGray), new Vector(-0.5, 0.5, 0)));
scene.lights.add(new SpotLight(new Color(1020, 400, 400),new Point(40,-40,-160), new Vector(-1, 1, 4))
             .setkC(1).setkL(4E-4).setkQ(2E-5));
scene.lights.add(new SpotLight(new Color(700, 400, 400),new Point(300, 30,0), new Vector(-2, 3, 3))
              .setkC(1).setkL(4E-4).setkQ(2E-5));
camera.setImageWriter(new ImageWriter("iceCream", 600, 600))
.setRayTracer(new RayTracerBasic(scene));
camera.renderImage(); //
camera.printGrid(50, new Color(51,0,0));
camera.writeToImage();

}

}
