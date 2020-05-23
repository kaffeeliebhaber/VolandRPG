package de.voland.game.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static BufferedImage loadImage(final String path) {
		
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}
	
//	public static BufferedImage loadImage(URL url) {
//		return ImageLoader.loadImage(url.getPath());
//	}
}
