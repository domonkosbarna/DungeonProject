package dungeonProjectPackage;
import java.awt.Rectangle;

public class Entity {
	
	int x;
	int y;
	

	Rectangle bounds;

	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	

	public Rectangle getBounds() 
	{
		return bounds;
	}
	
	public void setCoords(int j,int l)
	{
		x = j;
		y = l;
	}
	
	public void setX(int j) 
	{
		x = j;
	}
	
	public void setY(int l) 
	{
		y = l;
	}
	
	public void setBounds(Rectangle b)
	{
		bounds = b;
	}
	
}

