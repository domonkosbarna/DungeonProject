package dungeonProjectPackage;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Game  implements Runnable {
 public boolean running;
 public GameWindow gw = null;
 public Level level;
 public int battleStatus = 0;
 public int moveDistancex = 0;
 public int moveDistancey = 0;
 
 public int i = 0; //activePlayerIndex
	ArrayList<Entity> Encounter = new ArrayList<Entity>();
 
 
 //public Dimension movingto
 
 public Entity activePlayer = null;
 
 
 	public boolean positionIsEnemy(Dimension d1, Dimension d2)
 	{
 		/*System.out.println("==========================================");
 		System.out.println((int) ((d1.getWidth()-gw.xOffset) / gw.sizeFactor  ));
 		System.out.println((int) ((d1.getHeight()-gw.yOffset) / gw.sizeFactor ));
		System.out.println((int) ((d2.getWidth()-gw.xOffset) / gw.sizeFactor  ));
 		System.out.println((int) ((d2.getHeight()-gw.yOffset) / gw.sizeFactor  ));*/
 		
 		if((int) ((d1.getWidth()-gw.xOffset) / gw.sizeFactor  ) == (int) ((d2.getWidth()-gw.xOffset) / gw.sizeFactor  ) && (int) ((d1.getHeight()-gw.yOffset) / gw.sizeFactor ) == (int) ((d2.getHeight()-gw.yOffset) / gw.sizeFactor  ))
 		{
 		
 		return true;
 		}
 		else return false;
 	}
 	
	public Dimension convertPixelToTile(Dimension d)
	{
		
		
		return new Dimension((int) ((d.getWidth()-gw.xOffset) / gw.sizeFactor  ),(int) ((d.getHeight()-gw.yOffset) / gw.sizeFactor));
	}
 	
 	
	public Dimension convertTileToPixel(Dimension d)
	{
		return new Dimension((int) ((d.getWidth()*gw.sizeFactor)+gw.xOffset),(int) ((d.getHeight()*gw.sizeFactor)+gw.yOffset));
	}
	
	
	
	public Game()
	{
		level = new Level(0);
		gw = new GameWindow(level);
		start();
	}
	
	
	public synchronized void start(){
		running = true;
		new Thread(this).start();
	}
	
	public synchronized void stop(){
		running = false;
	}

	@Override
	public void run() {
		
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60D;
		
		int ticks = 0;
		int frames = 0;
		
		
		long lastTimer = System.currentTimeMillis();
		double delta =0;
		
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime)/nsPerTick;
			lastTime = now; 
			boolean shouldRender = true;
			
			
			
			while(delta >= 1)
			{
				ticks++;

				tick();
				delta-=1;
				shouldRender = true;
				
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if (shouldRender)
			{
			frames++;
			render();
			}
			
			if(System.currentTimeMillis() - lastTimer >= 1000 )
			{
				lastTimer += 1000;
				//System.out.println(ticks + ", " + frames);
				frames = 0;
				ticks = 0;
			}
			
			
		}
		
	}
	
	public void tick(){
		


		
		
		double battleDistance;
		double enemydistance;
		
		
		enemydistance = Math.sqrt((gw.hero.x - gw.monster.x)*(gw.hero.x - gw.monster.x) + (gw.hero.y - gw.monster.y)*(gw.hero.y - gw.monster.y));
		battleDistance = Math.sqrt((gw.hero.x - gw.monster.x)*(gw.hero.x - gw.monster.x) + (gw.hero.y - gw.monster.y)*(gw.hero.y - gw.monster.y));

		switch(battleStatus)
		{
			case 0:
				
				if(gw.PlayerWantToMovex != 0)
				{
					gw.hero.x += gw.PlayerWantToMovex;
					gw.PlayerWantToMovex=0;
				}
				
				if(gw.PlayerWantToMovey != 0)
				{
					gw.hero.y += gw.PlayerWantToMovey;
					gw.PlayerWantToMovey=0;
				}
				
				
				//System.out.println("Kattintás poziciója: "+gw.hero.clickPosition.width + " , " +gw.hero.clickPosition.height );
				
				if(convertPixelToTile(gw.hero.clickPosition).width != convertPixelToTile(new Dimension(gw.hero.x+gw.xOffset,gw.hero.y+gw.yOffset)).width || convertPixelToTile(gw.hero.clickPosition).height != convertPixelToTile(new Dimension(gw.hero.x+gw.xOffset,gw.hero.y+gw.yOffset)).height)			
				{
					moveDistancex = gw.hero.clickPosition.width - (gw.hero.x)-gw.xOffset;
					moveDistancey = gw.hero.clickPosition.height - (gw.hero.y)-gw.yOffset;
				}
				
				
							
				if (0 < moveDistancex)    
				{														// most igy egybõl odamegy és rááll naggyon vicces    -ezt ugye beleirhatnám a monster movejába
					gw.hero.x += 1;
					moveDistancex -=1;
				}
				else if (0 > moveDistancex)
				{
					gw.hero.x -= 1;
					moveDistancex +=1;
				}
	
				if (0 < moveDistancey)
				{
					gw.hero.y += 1;
					moveDistancey -=1;
				}
				else if (0 > moveDistancey)
				{
					gw.hero.y -= 1;
					moveDistancey +=1;
				}
				
				
				if(battleDistance < 800)
				{
					battleStatus = 1;
				

				
					Encounter.add(gw.hero);
					Encounter.add(gw.monster);
				
	
			
					for (int i = 0; i < Encounter.size(); i++) 
					{
					    Encounter.get(i).rollInit();
					    System.out.println(Encounter.get(i).getEntityName() +" initiative is " + Encounter.get(i).getInitative());
					}
					
					Collections.sort(Encounter);
				
					System.out.println("Battle begins..! \n" );
					
					
			//		gw.hero.x = convertTileToPixel((convertPixelToTile(new Dimension(gw.hero.x,gw.hero.y)))).width-gw.xOffset;
			//		gw.hero.y = convertTileToPixel((convertPixelToTile(new Dimension(gw.hero.x,gw.hero.y)))).height-gw.yOffset;
					
										
					gw.hero.x = convertTileToPixel((convertPixelToTile(new Dimension(gw.hero.x+gw.xOffset,gw.hero.y+gw.yOffset)))).width-gw.xOffset;
					gw.hero.y = convertTileToPixel((convertPixelToTile(new Dimension(gw.hero.x+gw.xOffset,gw.hero.y+gw.yOffset)))).height-gw.yOffset;
					
					System.out.println("It's "+ Encounter.get(i).getEntityName()+"'s turn.");
				}
				break;
				
			case 1:													//battleStatus 1
				
				
				switch(Encounter.get(i).getEntityType())
				{
				case "Player":
					
				//	System.out.println("It's "+ Encounter.get(i).clickPosition+" the click position.");
					
					if(Encounter.get(i).getCurrentMovespeed() > 0)				//   1. Ha van még moveja...
					{
						
						if(convertPixelToTile(Encounter.get(i).clickPosition).width != convertPixelToTile(new Dimension(Encounter.get(i).x+gw.xOffset,Encounter.get(i).y+gw.yOffset)).width || convertPixelToTile(Encounter.get(i).clickPosition).height != convertPixelToTile(new Dimension(Encounter.get(i).x+gw.xOffset,Encounter.get(i).y+gw.yOffset)).height)			
						{
							moveDistancex = convertTileToPixel((convertPixelToTile(new Dimension(Encounter.get(i).clickPosition.width,Encounter.get(i).clickPosition.height)))).width - (Encounter.get(i).x)-gw.xOffset;
							moveDistancey = convertTileToPixel((convertPixelToTile(new Dimension(Encounter.get(i).clickPosition.width,Encounter.get(i).clickPosition.height)))).height - (Encounter.get(i).y)-gw.yOffset;
							
							
						//	moveDistancex = convertPixelToTile(new Dimension(Encounter.get(i).clickPosition.width+gw.xOffset,Encounter.get(i).clickPosition.height+gw.yOffset)).width - (Encounter.get(i).x)-gw.xOffset;
							//moveDistancey = convertPixelToTile(new Dimension(Encounter.get(i).clickPosition.width+gw.xOffset,Encounter.get(i).clickPosition.height+gw.yOffset)).height - (Encounter.get(i).y)-gw.yOffset;
							
							//moveDistancex = Encounter.get(i).clickPosition.width - (Encounter.get(i).x)-gw.xOffset;
							//moveDistancey = Encounter.get(i).clickPosition.height - (Encounter.get(i).y)-gw.yOffset;
						}
						
						
									
						if (0 < moveDistancex)    
						{														
							Encounter.get(i).x += 50;
							moveDistancex -=50;
							Encounter.get(i).setCurrentMovespeed(Encounter.get(i).getCurrentMovespeed() - 1);
							System.out.println( Encounter.get(i).getCurrentMovespeed()+"= moves left");
						}
						else if (0 > moveDistancex)
						{
							Encounter.get(i).x -= 50;
							moveDistancex +=50;
							Encounter.get(i).setCurrentMovespeed(Encounter.get(i).getCurrentMovespeed() - 1);
							System.out.println( Encounter.get(i).getCurrentMovespeed()+"= moves left");
						}
			
						if (0 < moveDistancey)
						{
							Encounter.get(i).y += 50;
							moveDistancey -=50;
							Encounter.get(i).setCurrentMovespeed(Encounter.get(i).getCurrentMovespeed() - 1);
							System.out.println( Encounter.get(i).getCurrentMovespeed()+"= moves left");
						}
						else if (0 > moveDistancey)
						{
							Encounter.get(i).y -= 50;
							moveDistancey +=50;
							Encounter.get(i).setCurrentMovespeed(Encounter.get(i).getCurrentMovespeed() - 1);
							System.out.println( Encounter.get(i).getCurrentMovespeed()+"= moves left");
						}
						
						
						
					}				
					
					
					if(Encounter.get(i).getCurrentActionPoint() > 0)		//   2. Ha van még actionPointja
					{
						if(enemydistance <=  50)
						{
							//Encounter.get(i).attack(gw.monster);
						}
											
					}
						
					if(Encounter.get(i).getCurrentMovespeed() < 1 && (Encounter.get(i).getCurrentActionPoint() < 1 || enemydistance >= 50))    //  3. Ha már nincs movement vagy AP adja át a kört 
					{
						if((i+1) < Encounter.size())
						{
							Encounter.get(i).clickPosition.width = Encounter.get(i).x+gw.xOffset;
							Encounter.get(i).clickPosition.height = Encounter.get(i).y+gw.yOffset;
							
							
							i++;
							Encounter.get(i).refreshmove();				

							
							System.out.println("It's "+ Encounter.get(i).getEntityName()+"'s turn.");

						//	moveDistancex = Encounter.get(i).clickPosition.width - (Encounter.get(i).x)-gw.xOffset;
						//	moveDistancey = Encounter.get(i).clickPosition.height - (Encounter.get(i).y)-gw.yOffset;
						
						
							
							
							
						}
						else
						{
							Encounter.get(i).clickPosition.width = Encounter.get(i).x+gw.xOffset;
							Encounter.get(i).clickPosition.height = Encounter.get(i).y+gw.yOffset;
							
							
							i=0;
							Encounter.get(i).refreshmove();

	
							System.out.println("It's "+ Encounter.get(i).getEntityName()+"'s turn.");

													
						}
						
					}	
						
						
						
					
					break;
					
				case "Enemy":
					
					while(Encounter.get(i).getCurrentMovespeed()> 0) 			// 1. Ha van még moveja
					{
						if(enemydistance > 80 )             			//Ha távolabb áll az ellenfél
						{ 
							if (Encounter.get(i).x < gw.hero.x)   
							{													
								Encounter.get(i).x += 50;
								Encounter.get(i).setCurrentMovespeed(Encounter.get(i).getCurrentMovespeed()-1);
			
							}
							else if (Encounter.get(i).x > gw.hero.x)
							{
								Encounter.get(i).x -= 50;
								Encounter.get(i).setCurrentMovespeed(Encounter.get(i).getCurrentMovespeed()-1);
							}
			
			
							if (Encounter.get(i).y < gw.hero.y)
							{
								Encounter.get(i).y += 50;
								Encounter.get(i).setCurrentMovespeed(Encounter.get(i).getCurrentMovespeed()-1);
			
							}
							else if (Encounter.get(i).y > gw.hero.y)
							{
								Encounter.get(i).y -= 50;
								Encounter.get(i).setCurrentMovespeed(Encounter.get(i).getCurrentMovespeed()-1);
							}
						}
					
					}
					
					
					if(Encounter.get(i).getCurrentActionPoint() > 0)		//   2. Ha van még actionPointja
					{
						if(enemydistance <=  50)
						{
						//	Encounter.get(i).attack(gw.hero);
						}
						
												
											
					}
						
					if(Encounter.get(i).getCurrentMovespeed() < 1 && (Encounter.get(i).getCurrentActionPoint() < 1 || enemydistance >= 50))    //  3. Ha már nincs movement vagy AP adja át a kört 
					{
						if((i+1) < Encounter.size())
						{
							
							i++;
							Encounter.get(i).refreshmove();	

						//	Encounter.get(i).clickPosition = null;
							System.out.println("It's "+ Encounter.get(i).getEntityName()+"'s turn.");

						}
						else
						{
							
							i=0;
							Encounter.get(i).refreshmove();
 
						//	Encounter.get(i).clickPosition = null;
							System.out.println("It's "+ Encounter.get(i).getEntityName()+"'s turn.");

													
						}
						
					}	
					
					
					
					break;
				
				
				}			//Switch entity vége
				
				
				
				break;		//case 1 vége
				}    //Switch battleStatus vége
	
		}			//tick() vége
				

		
		
				
		
	
	
	
	
	public void render(){
		if (gw != null)
		{
			gw.render();
		}
		
	}
	
	
	
	
	
	
	
	
}
