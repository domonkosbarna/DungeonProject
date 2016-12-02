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
		if(gw.hero!=null)
		{
			g.setColor(Color.blue);
			g.fillOval(gw.hero.x + gw.xOffset, gw.sizeFactor + gw.yOffset, gw.sizeFactor, gw.sizeFactor);
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
