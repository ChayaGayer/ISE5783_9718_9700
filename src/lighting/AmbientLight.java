package lighting;
import primitives.Color;
import primitives.Double3;

/**
 * Ambient Light for all object in 3D space
 *
 * this class represented us the Ambient Light (תאורה סביבתית)
 */
public class AmbientLight extends Light{

    //private final Color intensity; // intensity (עצמה) of ambient Light
    
    public static final AmbientLight NONE=new AmbientLight(Color.BLACK,Double3.ZERO);
    



    /**
     * default constructor that create ambientLight in black
     */
    public AmbientLight()
    {
        super(Color.BLACK);
   }

    /**
     * create AmbientLight of the scene
     * @param Ia the color of ambientLight
     * @param Ka factor of the ambientLight
     */
    public AmbientLight(Color Ia , Double3 Ka) 
    {
        super(Ia.scale(Ka));
    }

    public AmbientLight(Color in) {
        super(in);
    }

	public AmbientLight(Color ia, double d) {
		// TODO Auto-generated constructor stub
		super(ia.scale(new Double3(d,d,d)));
	}
}
