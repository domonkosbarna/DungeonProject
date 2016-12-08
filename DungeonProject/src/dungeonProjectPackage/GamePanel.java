package dungeonProjectPackage;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	
	public GameWindow gw;
	
	public GamePanel(GameWindow gw)
	{
		this.gw = gw;
	}
	
	public void paint(Graphics g)
	{
		//System.out.println("paint");
		
		g.setColor(new Color(214, 214, 214,200));
		g.fillRect(0, 0, gw.frame.getWidth(), gw.frame.getHeight());
		
		
		for(int x =0; x < gw.level.map.width; x++)
		{
			for(int y = 0; y < gw.level.map.height; y++)
			{
				if(gw.level.map.tiles[x][y].type == 1)
				{
					g.setColor(new Color(203, 140, 52, 175));
				}
				else
				{
					g.setColor(Color.GRAY);
				}
				g.fillRect(x * gw.sizeFactor + gw.xOffset, y * gw.sizeFactor + gw.yOffset, gw.sizeFactor, gw.sizeFactor);
			}
		}
		//hero start pos
		g.setColor(new Color(177, 254, 175,200));
		g.fillRect(gw.level.heroStartPos.width*gw.sizeFactor + gw.xOffset, gw.level.heroStartPos.height * gw.sizeFactor + gw.yOffset, gw.sizeFactor, gw.sizeFactor);
		
		//monster start pos
		g.setColor(new Color(254, 175, 175,200));
		g.fillRect(gw.level.monsterStartPos.width*gw.sizeFactor + gw.xOffset, gw.level.monsterStartPos.height * gw.sizeFactor + gw.yOffset, gw.sizeFactor, gw.sizeFactor);
		
		
		if(gw.hero!=null)
		{
			g.setColor(Color.blue);
			g.fillOval(gw.hero.x + gw.xOffset, gw.hero.y+ gw.yOffset, gw.sizeFactor, gw.sizeFactor);
		}
		
		if(gw.monster!=null)
		{
			g.setColor(Color.BLACK);
			g.fillOval(gw.monster.x + gw.xOffset, gw.monster.y+ gw.yOffset, gw.sizeFactor, gw.sizeFactor);
		}
		
		
		//if debug then grid
		if(gw.debug)
		{
			
			g.setColor(new Color(0,0,0,255));
			for(int i= 0; i< gw.frame.getHeight()/gw.sizeFactor; i++ )
			{
				g.drawLine(0, i*gw.sizeFactor+ gw.yOffset%gw.sizeFactor, (int) gw.frame.getWidth(), i*gw.sizeFactor + gw.yOffset%gw.sizeFactor );
			}
			
			for(int i= 0; i< gw.frame.getWidth()/gw.sizeFactor; i++ )
			{
				g.drawLine(i*gw.sizeFactor + gw.xOffset%gw.sizeFactor, 0, i*gw.sizeFactor + gw.xOffset%gw.sizeFactor, (int) gw.frame.getHeight());
			}
			
		}
		
		
		
	}
}
