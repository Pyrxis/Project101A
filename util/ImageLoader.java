package main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Simple utility tool used to import image files
 * @author Cheol Woo Park
 * Imported from another existing personal project
 */
public class ImageLoader {
	
	/**
	 * The method will search the given file while with "/" and ".png" attached.
	 * Only supports .png files
	 * @param fileName
	 * @return BufferedImage
	 */
	public BufferedImage load(String fileName) {
		try {
			return ImageIO.read(getClass().getResource("/" + fileName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
