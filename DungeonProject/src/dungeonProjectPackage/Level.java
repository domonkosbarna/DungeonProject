package dungeonProjectPackage;

import java.awt.Dimension;

public class Level {
public Map map;
public Dimension heroStartPos;
public Dimension monsterStartPos;

	public Level(int levelNum)
	{
		if (levelNum == 0)
		{
			heroStartPos = new Dimension(2,2);
			monsterStartPos = new Dimension(16,15);
			map = new Map(20,20);
		}
	}
}
