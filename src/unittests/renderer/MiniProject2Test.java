/**
 * 
 */

package unittests.renderer;

import org.junit.Test;
import lighting.AmbientLight;
import lighting.PointLight;

import primitives.*;
import renderer.*;

import geometries.*;
import scene.Scene;

/**
 * Tests for BVH 
 *
 *
 */
public class MiniProject2Test {
	private Scene scene = new Scene("Test scene").setCBR();

	// part 1
	@Test
	public void PicturePart1() {
		Camera camera = new Camera(new Point(5, -10, 1000), new Vector(-0.035, 0, -1).normalize(),
				new Vector(0, 1, 0)) //
						.setVPSize(200, 200).setVPDistance(1000).setDebugPrint(0.2) 
						.setMultiThreading(3);
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.3));
		scene.geometries.add(
				new Polygon(new Point(-150, -160, -10), new Point(-150, 90, -400), new Point(165, 90, -400),
						new Point(165, -160, -10)).setMaterial(new Material().setKr(0.3).setKt(0.25).setkD(0.5))
								.setEmission(new Color(0, 0, 0)),
				new Triangle(new Point(49, 0, -150), new Point(-33, 0, -150), new Point(10, -70, -150))
						.setEmission(new Color(255, 140, 190)), // orange

				new Triangle(new Point(49, 0, -150), new Point(10, -70, -150), new Point(59, 3, -150))
						.setEmission(new Color(255, 140, 190)), // orange
				new Sphere(new Point(-8, 20, -150), 25).setEmission(new Color(0, 30, 60))// blue icecream
						.setMaterial(new Material().setKt(1).setkD(0.5).setnShininess(30).setkS(0.5)),
				new Sphere(new Point(35, 20, -150), 25).setEmission(new Color(50, 0, 0))// red icecream
						.setMaterial(new Material().setKr(1).setkD(0.5).setnShininess(30).setkS(0.5)),
				new Sphere(new Point(10, 50, -150), 25).setEmission(new Color(0, 50, 0))// green icecream
						.setMaterial(new Material().setKt(1).setkD(0.5).setnShininess(30).setkS(0.5)),
				new Sphere(new Point(10, 75, -150), 7).setEmission(new Color(70, 0, 0))// cherry
						.setMaterial(new Material().setKt(1).setkD(0.5).setnShininess(30).setkS(0.5)),
				// face
				new Sphere(new Point(35, 20, -100), 12).setEmission(new Color(0, 0, 0)), // red icecream
				new Sphere(new Point(-8, 20, -100), 12).setEmission(new Color(0, 0, 0)), // blue icecream
				new Sphere(new Point(40, 25, -90), 5).setEmission(new Color(255, 255, 255)), // red icecream
				new Sphere(new Point(-3, 25, -90), 5).setEmission(new Color(255, 255, 255)), // blue icecream
				// mouse
				new Sphere(new Point(10, -27, -100), 10).setEmission(new Color(50, 0, 0)),
				new Sphere(new Point(10, -20, -100), 10).setEmission(new Color(255, 140, 190)),
				// ice cream bar
				new Polygon(new Point(-80, -15, -150), new Point(-110, -25, -150), new Point(-110, 65, -150),
						new Point(-80, 75, -150)).setEmission(new Color(227, 28, 36))
								.setMaterial(new Material().setKt(0.5).setKr(1).setKr(0.5)), // red
				new Polygon(new Point(-80, -15, -150), new Point(-110, -25, -150), new Point(-110, -35, -150),
						new Point(-80, -25, -150)).setEmission(new Color(26, 240, 52))
								.setMaterial(new Material().setKt(0.5).setKr(1).setKr(0.5)), // green
				new Polygon(new Point(-101, -32, -150), new Point(-89, -28, -150), new Point(-89, -48, -150),
						new Point(-101, -52, -150)).setEmission(new Color(202, 165, 38))
								.setMaterial(new Material().setKt(0.5).setKr(1).setKr(0.5)), // yellow

				// nucleuses
				new Sphere(new Point(-100, -10, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-80, -12, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-87, -3, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-95, 7, -110), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-80, 15, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-90, 20, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-100, 27, -100), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-87, 33, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-95, 42, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-80, 47, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-87, 55, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-98, 56, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)));
		scene.lights.add(new PointLight(new Color(100, 100, 500), new Point(-50, -50, 50))//
				.setkL(0.00001).setkQ(0.00001));
		scene.setBVH();
		ImageWriter imageWriter = new ImageWriter("mini_part1", 600, 600);
		
		Render render = new Render() 
				.setImageWriter(imageWriter) 
				.setCamera(camera) 
				.setRayTracer(new RayTracerBasic(scene).setNumberOfRays(100));

		render.renderImage();
		render.writeToImage();
		
	}

	// part 2
	@Test
	public void PicturePart2() {
		Camera camera = new Camera(new Point(5, -10, 1000), new Vector(-0.035, 0, -1).normalize(),
				new Vector(0, 1, 0)) //
						.setVPSize(200, 200).setVPDistance(1000);
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.3));

		Geometries geoRedIceCream = new Geometries();
		geoRedIceCream.add(
				// eyes
				new Sphere(new Point(35, 20, -100), 12).setEmission(new Color(0, 0, 0)), // red icecream
				new Sphere(new Point(40, 25, -90), 5).setEmission(new Color(255, 255, 255)), // red icecream
				// ice
				new Sphere(new Point(35, 20, -150), 25).setEmission(new Color(50, 0, 0))// red icecream
						.setMaterial(new Material().setKr(1).setkD(0.5).setnShininess(30).setkS(0.5)));

		Geometries geoBlueIceCream = new Geometries();
		geoBlueIceCream.add(
				// eyes
				new Sphere(new Point(-8, 20, -100), 12).setEmission(new Color(0, 0, 0)), // blue icecream
				new Sphere(new Point(-3, 25, -90), 5).setEmission(new Color(255, 255, 255)), // blue icecream
				// ice
				new Sphere(new Point(-8, 20, -150), 25).setEmission(new Color(0, 30, 60))// blue icecream
						.setMaterial(new Material().setKt(1).setkD(0.5).setnShininess(30).setkS(0.5)));

		Geometries geo11 = new Geometries();
		geo11.add(geoRedIceCream, geoBlueIceCream,
				new Sphere(new Point(10, 50, -150), 25).setEmission(new Color(0, 50, 0))// green icecream
						.setMaterial(new Material().setKt(1).setkD(0.5).setnShininess(30).setkS(0.5)),
				new Sphere(new Point(10, 75, -150), 7).setEmission(new Color(70, 0, 0))// cherry
						.setMaterial(new Material().setKt(1).setkD(0.5).setnShininess(30).setkS(0.5)));
		Geometries geo12 = new Geometries();
		geo12.add(
				new Triangle(new Point(49, 0, -150), new Point(-33, 0, -150), new Point(10, -70, -150))
						.setEmission(new Color(255, 140, 190)), // orange

				new Triangle(new Point(49, 0, -150), new Point(10, -70, -150), new Point(59, 3, -150))
						.setEmission(new Color(255, 140, 190)), // orange
				// mouse
				new Sphere(new Point(10, -27, -100), 10).setEmission(new Color(50, 0, 0)),
				new Sphere(new Point(10, -20, -100), 10).setEmission(new Color(255, 140, 190)));

		Geometries geo1 = new Geometries();
		geo1.add(geo11, geo12);

		Geometries redIce = new Geometries();
		redIce.add(
				// nucleuses
				new Sphere(new Point(-100, -10, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-80, -12, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-87, -3, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-95, 7, -110), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-80, 15, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-90, 20, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-100, 27, -100), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-87, 33, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-95, 42, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-80, 47, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-87, 55, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-98, 56, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				// ice cream bar
				new Polygon(new Point(-80, -15, -150), new Point(-110, -25, -150), new Point(-110, 65, -150),
						new Point(-80, 75, -150)).setEmission(new Color(227, 28, 36))
								.setMaterial(new Material().setKt(0.5).setKr(1).setKr(0.5)) // red
		);

		Geometries geo2 = new Geometries();
		geo2.add(redIce,
				new Polygon(new Point(-80, -15, -150), new Point(-110, -25, -150), new Point(-110, -35, -150),
						new Point(-80, -25, -150)).setEmission(new Color(26, 240, 52))
								.setMaterial(new Material().setKt(0.5).setKr(1).setKr(0.5)), // green
				new Polygon(new Point(-101, -32, -150), new Point(-89, -28, -150), new Point(-89, -48, -150),
						new Point(-101, -52, -150)).setEmission(new Color(202, 165, 38))
								.setMaterial(new Material().setKt(0.5).setKr(1).setKr(0.5))// yellow

		);
		scene.geometries.add(new Polygon(new Point(-150, -160, -10), new Point(-150, 90, -400),
				new Point(165, 90, -400), new Point(165, -160, -10))
						.setMaterial(new Material().setKr(0.3).setKt(0.25).setkD(0.5)).setEmission(new Color(0, 0, 0)),
				geo1, geo2);
		scene.lights.add(new PointLight(new Color(100, 100, 500), new Point(-50, -50, 50), 5)//
				.setkL(0.00001).setkQ(0.00001));

		ImageWriter imageWriter = new ImageWriter("mini_part2", 600, 600);
		Render render = new Render() 
				.setImageWriter(imageWriter) 
				.setCamera(camera) 
				.setRayTracer(new RayTracerBasic(scene).setNumberOfRays(100));

		render.renderImage();
		render.writeToImage();
	}

	
	//@Test
	/*
	public void PicturePart3() {
		Camera camera = new Camera(new Point(5, -10, 1000), new Vector(-0.035, 0, -1).normalize(),
				new Vector(0, 1, 0)) //
						.setVPSize(200, 200).setVPDistance(1000);
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.3));
		Geometries geo = new Geometries();
		geo.add(new Polygon(new Point(-150, -160, -10), new Point(-150, 90, -400), new Point(165, 90, -400),
				new Point(165, -160, -10)).setMaterial(new Material().setKr(0.3).setKt(0.25).setkD(0.5))
						.setEmission(new Color(0, 0, 0)),
				new Triangle(new Point(49, 0, -150), new Point(-33, 0, -150), new Point(10, -70, -150))
						.setEmission(new Color(255, 140, 190)), // orange

				new Triangle(new Point(49, 0, -150), new Point(10, -70, -150), new Point(59, 3, -150))
						.setEmission(new Color(255, 140, 190)), // orange
				new Sphere(new Point(-8, 20, -150), 25).setEmission(new Color(0, 30, 60))// blue icecream
						.setMaterial(new Material().setKt(1).setkD(0.5).setnShininess(30).setkS(0.5)),
				new Sphere(new Point(35, 20, -150), 25).setEmission(new Color(50, 0, 0))// red icecream
						.setMaterial(new Material().setKr(1).setkD(0.5).setnShininess(30).setkS(0.5)),
				new Sphere(new Point(10, 50, -150), 25).setEmission(new Color(0, 50, 0))// green icecream
						.setMaterial(new Material().setKt(1).setkD(0.5).setnShininess(30).setkS(0.5)),
				new Sphere(new Point(10, 75, -150), 7).setEmission(new Color(70, 0, 0))// cherry
						.setMaterial(new Material().setKt(1).setkD(0.5).setnShininess(30).setkS(0.5)),
				// face
				new Sphere(new Point(35, 20, -100), 12).setEmission(new Color(0, 0, 0)), // red icecream
				new Sphere(new Point(-8, 20, -100), 12).setEmission(new Color(0, 0, 0)), // blue icecream
				new Sphere(new Point(40, 25, -90), 5).setEmission(new Color(255, 255, 255)), // red icecream
				new Sphere(new Point(-3, 25, -90), 5).setEmission(new Color(255, 255, 255)), // blue icecream
				// mouse
				new Sphere(new Point(10, -27, -100), 10).setEmission(new Color(50, 0, 0)),
				new Sphere(new Point(10, -20, -100), 10).setEmission(new Color(255, 140, 190)),
				// ice cream bar
				new Polygon(new Point(-80, -15, -150), new Point(-110, -25, -150), new Point(-110, 65, -150),
						new Point(-80, 75, -150)).setEmission(new Color(227, 28, 36))
								.setMaterial(new Material().setKt(0.5).setKr(1).setKr(0.5)), // red
				new Polygon(new Point(-80, -15, -150), new Point(-110, -25, -150), new Point(-110, -35, -150),
						new Point(-80, -25, -150)).setEmission(new Color(26, 240, 52))
								.setMaterial(new Material().setKt(0.5).setKr(1).setKr(0.5)), // green
				new Polygon(new Point(-101, -32, -150), new Point(-89, -28, -150), new Point(-89, -48, -150),
						new Point(-101, -52, -150)).setEmission(new Color(202, 165, 38))
								.setMaterial(new Material().setKt(0.5).setKr(1).setKr(0.5)), // yellow

				// nucleuses
				new Sphere(new Point(-100, -10, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-80, -12, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-87, -3, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-95, 7, -110), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-80, 15, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-90, 20, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-100, 27, -100), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-87, 33, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-95, 42, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-80, 47, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-87, 55, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)),
				new Sphere(new Point(-98, 56, -95), 2).setEmission(new Color(0, 0, 0))
						.setMaterial(new Material().setKt(0.25).setKr(0.5)));

		scene.geometries.add(geo);
		scene.lights.add(new PointLight(new Color(100, 100, 500), new Point(-50, -50, 50), 5)//
				.setkL(0.00001).setkQ(0.00001));
		ImageWriter imageWriter = new ImageWriter("miniBVH", 600, 600);
		Render render = new Render() 
				.setImageWriter(imageWriter) 
				.setCamera(camera) 
				.setRayTracer(new RayTracerBasic(scene).setNumberOfRays(100));

		render.renderImage();
		render.writeToImage();

	}
	
	*/
}
