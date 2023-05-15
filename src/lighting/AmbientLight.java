package lighting;
import primitives.Color;
import primitives.Double3;

/**
 * Ambient Light for all object in 3D space
 *
 * this class represented us the Ambient Light (תאורה סביבתית)
 */
public class AmbientLight {

    private final Color intensity; // intensity (עצמה) of ambient Light
    
    public static final AmbientLight NONE=new AmbientLight(Color.BLACK,Double3.ZERO);
    

    /**
     * constructor for knowing the intensity after the light factor
     *
     * @param Ia - Light illumination (RGB עצמת האור לפי קומפוננטות)
     * @param Ka - Light factor - מקדם הנחתה של האור
     */
    public AmbientLight(Color Ia, Double3 Ka)
    {
        //calculation of the intensity after the light factor
        this.intensity = Ia.scale(Ka);
    }
    /**
     * A constructor that accepts a value of type double for the attenuation coefficient �𝒌
     * @param Ia - Light illumination (RGB עצמת האור לפי קומפוננטות)
     * @param Ka - Light factor - מקדם הנחתה של האור
     
     */
    public AmbientLight(Color Ia, double Ka)
    {
        
        this.intensity = Ia.scale(Ka);
    }

    /**
     * getter for intensity
     * @return the intensity
     */
    public Color getIntensity() 
    {
        return this.intensity;
    }
}
