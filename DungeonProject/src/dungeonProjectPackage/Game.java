package dungeonProjectPackage;

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
				System.out.println(ticks + ", " + frames);
				frames = 0;
				ticks = 0;
			}
			
			
		}
		
	}
	
	public void tick(){
		
	}
	
	
	public void render(){
		if (gw != null)
		{
			gw.render();
		}
		
	}
	
	
	
	
	
	
	
	
}
