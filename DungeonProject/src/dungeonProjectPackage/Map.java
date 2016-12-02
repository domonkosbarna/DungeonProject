package dungeonProjectPackage;

public class Map {

	public Tile[][] tiles;
	int width = 0;
	int height = 0;
	public Map(int width, int height)
	{
		this.height = height;
		this.width = width;
		tiles = new Tile[width][height];
		//feltoltes
		for(int y = 0; y < this.height; y++)
		{
			for(int x = 0; x < this.width; x++)
			{
				if(y > 0 && y < this.height-1)
				{
					if(x > 0 && x < this.width-1)
					{
						tiles[x][y] = new Tile(1);
					}
					else 
					{
						tiles[x][y] = new Tile(2);
					}
				}
				else 
				{
					tiles[x][y] = new Tile(2);
				}
			}
		}
	}
	
	
}
