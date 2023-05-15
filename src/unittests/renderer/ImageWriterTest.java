/**
 * Testing ImageWriter Class
 */
package unittests.renderer;
import primitives.Color;
import renderer.ImageWriter;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * @author user1
 *
 */
public class ImageWriterTest {

	@Test
	public void writeImageTest() {
		
		// TC01: Constructing a experimental picture of a grid
		ImageWriter imageWriter = new ImageWriter("Write Image Test Grid", 800, 500);
		for (int i=0; i<imageWriter.getNx(); i++) {
			for (int j=0; j<imageWriter.getNy(); j++) {
				if(i%100==0 || (i+1)%100==0 || j%100==0 || (j+1)%100==0)
					imageWriter.writePixel(i, j, Color.BLACK);
				else
					imageWriter.writePixel(i, j, new Color(0,150,150));
			}
		}
		imageWriter.writeToImage();
		
	}

}

