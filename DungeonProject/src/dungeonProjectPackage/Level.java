package dungeonProjectPackage;

import java.awt.Dimension;

public class Level {
public Map map;
public Dimension heroStartPos;
public Dimension mosnterStarPos;

	public Level(int levelNum)
	{
		if (levelNum == 0)
		{
			heroStartPos = new Dimension(2,2);
			mosnterStarPos = new Dimension(1,6);
			map = new Map(8,10);
		}
	}
}
