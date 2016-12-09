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
					moveDistancex = gw.hero.clickPosition.width - (gw.hero.x)-70;
					moveDistancey = gw.hero.clickPosition.height - (gw.hero.y)-100;
				}
				
				
				System.out.println("Hero poziciója: "+gw.hero.x+ " "+gw.hero.y +"\n "+gw.hero.clickPosition+"\n"+moveDistancex +" "+ moveDistancey);
			
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
				
				
				if(battleDistance < 500)
				{
					battleStatus = 1;
				
					ArrayList<Entity> Encounter = new ArrayList<Entity>();
				
					Encounter.add(gw.hero);
					Encounter.add(gw.monster);
				
	
			
					for (int i = 0; i < Encounter.size(); i++) 
					{
					    Encounter.get(i).rollInit();
					    //System.out.println(Encounter.get(i).getEntityName() +" initiative is " + Encounter.get(i).getInitative());
					}
					
					Collections.sort(Encounter);
				
					//System.out.println("Fight! \n" );
					for (int i = 0; i < Encounter.size(); i++) 
					{
						//System.out.println(i +"\n" + Encounter.get(i).getEntityName() );
					}
				}
				break;
				
			case 1:
				
				System.out.println("case 1");
				if(enemydistance > 50 )                 //hurrááá! ha 50-et lep 80-ra allitsd!
				{ //(gw.monster.getMovespeed() > 0) || 
					if (gw.monster.x < gw.hero.x)   
					{														// most igy egybõl odamegy és rááll naggyon vicces    -ezt ugye beleirhatnám a monster movejába
						gw.monster.x += 1;
						gw.monster.setMovespeed(gw.monster.getMovespeed()-1);
	
					}
					else if (gw.monster.x > gw.hero.x)
						gw.monster.x -= 1;
						gw.monster.setMovespeed(gw.monster.getMovespeed()-1);
	
	
					if (gw.monster.y < gw.hero.y)
					{
						gw.monster.y += 1;
						gw.monster.setMovespeed(gw.monster.getMovespeed()-1);
	
					}
					else if (gw.monster.y > gw.hero.y)
						gw.monster.y -= 1;
						gw.monster.setMovespeed(gw.monster.getMovespeed()-1);
				}
				else
				{ 
				
					while(gw.hero.getHP()>0 && gw.monster.getHP()>0 )
					{
		
						Scanner input = new Scanner(System.in);
						String userInput = "";  
						
					
						System.out.println("***********\nThe monster is attacking!\n");
						//monster.ACrest();
						gw.monster.attack(gw.hero);
						
						
						System.out.println("Please type 'roll' to attack!\nThe monster has "+gw.monster.getCurrentHP() +" hit points left.\n"
								+ "You have "+gw.hero.getCurrentHP() +" hit points left.");
						userInput =  input.nextLine();	
						
						
						
						switch(userInput) 
						{
						
							case "roll" : 
								//hero.ACrest();
								gw.hero.attack(gw.monster);
								break;
							case "drink":
								//hero.potion();
								break;
							case "def":
								//hero.def();
								break;
							/*case "cast":
								if(hero.getCls() == 1) 
								{
								hero.cast(monster);
								}
								else {
									System.out.println("You are not a wizard, you can't cast spells!\n");	
									userInput =  input.nextLine();	
								}
								break;*/
								
							default: 
								System.out.println("Maybe You misspelled it! \nPlease type 'roll' to attack!\n");
								userInput =  input.nextLine();	
							break;
						}
						
						if(gw.monster.getCurrentHP()<=0)
						{
							break;	
						}
					
					}
				
				
					break;
				/*
				if(Entity == null)
				{
					activePlayer = Encounter.get(0);
				}
		
				}
				else
				{
					if(activePlayer.getMovespeed() > 0 || activePlayer.getActionPoint() > 0)
					{
					if(activePlayer.clickPosition)	
					}
				}
				*/
				
				}
	
		}
				
}
		
		
				
		
	
	
	
	
	public void render(){
		if (gw != null)
		{
			gw.render();
		}
		
	}
	
	
	
	
	
	
	
	
}
