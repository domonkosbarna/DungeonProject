package dungeonProjectPackage;

import java.util.Random;

public class Tile {
	public int type;
	public int tileNumber;

	
	public Tile(int type)
	{
		this.type = type;
		
		Random rand = new Random();
		if(type==1)
		{
			tileNumber = rand.nextInt(4) + 1;
		}
		else
		{
			tileNumber = rand.nextInt(1) + 1;
		}
	}
}
