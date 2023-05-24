package scene;

import lighting.AmbientLight;
import lighting.LightSource;

import java.util.LinkedList;
import java.util.List;

import geometries.Geometries;
import primitives.Color;

//================== Scene class (PDS - Plain Data Structure) ==================//
public class Scene {

	public String name;
	public Color background=Color.BLACK;
	public AmbientLight ambientLight = AmbientLight.NONE;
	public Geometries geometries = new Geometries();
	 public List<LightSource> lights=new LinkedList<LightSource>();

	
	/**
	 * Scene's Constructor
	 * @param name The scene's name
	 */
	public Scene(String name) {
		this.name = name;
	}
	
	/**
	 * Setter to the scene's background-color
	 * @param background The background-color
	 * @return The updated scene
	 */
	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}

	/**
	 * Setter to the scene's ambient-light-color
	 * @param ambientLight The ambient-light-color
	 * @return The updated scene
	 */
	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this;
	}

	/**
	 * Setter to the scene's geometries-collection
	 * @param geometries The geometries-collection
	 * @return The updated scene
	 */
	public Scene setGeometries(Geometries geometries) {
		this.geometries = geometries;
		return this;
	}
	/**
     * @param lights, set a list of light source of the scene
     * @return the scene
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }
}

   
