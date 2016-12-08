package dungeonProjectPackage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game  implements Runnable {
 public boolean running;
 public GameWindow gw = null;
 public Level level;

	
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
		

		boolean battleStatus = false;
		
		
		double battleDistance;
		double enemydistance;
		
		
		enemydistance = Math.sqrt((gw.hero.x - gw.monster.x)*(gw.hero.x - gw.monster.x) + (gw.hero.y - gw.monster.y)*(gw.hero.y - gw.monster.y));
		battleDistance = Math.sqrt((gw.hero.x - gw.monster.x)*(gw.hero.x - gw.monster.x) + (gw.hero.y - gw.monster.y)*(gw.hero.y - gw.monster.y));

		
		
		if(battleDistance < 500)
		{					
			battleStatus = true;
			
			
			ArrayList<Entity> Encounter = new ArrayList<Entity>();
			
			Encounter.add(gw.hero);
			Encounter.add(gw.monster);
			

		
			for (int i = 0; i < Encounter.size(); i++) {
			    Encounter.get(i).rollInit();
			    System.out.println(Encounter.get(i).getEntityName() +" initiative is " + Encounter.get(i).getInitative());
			}
			
			Collections.sort(Encounter);
			
			System.out.println("Fight! \n" );
			for (int i = 0; i < Encounter.size(); i++) {
				System.out.println(i +"\n" + Encounter.get(i).getEntityName() );
			}
		
					
	       
	
			
			
		Scanner input = new Scanner(System.in);
		String userInput = "";  
		
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
		//	gw.hero.currentstat();            // nem ide majd ezt a ketto sort
		//	gw.monster.currentstat();
			
			System.out.println("***********\nThe monster is attacking!\n");
			//monster.ACrest();
			gw.monster.attack(gw.hero);
			
			
			System.out.println("Please type 'roll' to attack!\nThe monster has "+gw.monster.getCurrentHP() +" hit points left.\n"
					+ "You have "+gw.hero.getCurrentHP() +" hit points left.");
			userInput =  input.nextLine();	
			
			
			
			switch(userInput) {
			
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
			
			
		}
		
		}
				
		
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
	}
	
	
	public void render(){
		if (gw != null)
		{
			gw.render();
		}
		
	}
	
	
	
	
	
	
	
	
}
