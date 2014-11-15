//BET YOU CANT GUESS WHAT THIS DOES!
package UsefulThings;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.Menu;

public final class ImageGetter {
	public static BufferedImage getImage(String path)
	{
		java.net.URL imgURL = Menu.class.getResource(path);
		if (imgURL != null)
			try {
				return ImageIO.read(imgURL);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else System.err.println("nope");
		return null;
	}
}
