/**
 * 
 */
package unittests.renderer;

import org.junit.jupiter.api.Test;
import geometries.*;
import lighting.*;
import primitives.*;
import renderer.*;
import scene.Scene;


class MiniProject1 {

	private Scene scene = new Scene("Test scene");

	
	@Test
	public void Sphere1() 
	{
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(200, 200).setVPDistance(1000);

		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.15)));

		scene.geometries.add( //

				new Sphere(new Point(0, 0, 0), 80) //
						.setEmission(new Color(java.awt.Color.yellow)) //
						.setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setKt(0.6)));

		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
				.setkL(4E-5).setkQ(2E-7));

		ImageWriter imageWriter = new ImageWriter("before", 600, 600);

				camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene));

		camera.renderImage();
		camera.writeToImage();

	}
	
	@Test
	public void Sphere2() 
	{
		int numOfRays=80;
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(200, 200).setVPDistance(1000);

		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.15)));

		scene.geometries.add( //
				new Sphere(new Point(0, 0, 0), 80) //
				.setEmission(new Color(java.awt.Color.yellow)) //
				.setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setKt(0.6)));

		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
				.setkL(4E-5).setkQ(2E-7));

		ImageWriter imageWriter = new ImageWriter("after", 600, 600);

				camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)).setNumOfRays(numOfRays);

		camera.renderImage();
		camera.writeToImage();
	}

}
