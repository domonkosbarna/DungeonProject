package dungeonProjectPackage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {
	public int type;
	public BufferedImage img = null;
	
	public Tile(int type)
	{
		this.type = type;
		if(type == 1)
		{
			
			try {
			    img = ImageIO.read(new File("Floor1.bmp"));
			} catch (IOException e) {
			}
		}
	}
}
