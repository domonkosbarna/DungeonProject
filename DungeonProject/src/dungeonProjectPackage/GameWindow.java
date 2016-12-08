package dungeonProjectPackage;

import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow extends WindowAdapter {
	
	
	public JFrame frame;
	public JPanel panel;
	
	public Hero hero =null;
	public Monster monster =null;
	
	private PlayerActionListener pal;
	public BufferedImage[] Floors; 
	public BufferedImage[] Walls; 
	public Level level =null;
	public int sizeFactor = 50;
	public int xOffset = 70;
	public int yOffset = 100;
	public boolean debug = false;
	public int PlayerWantToMovex=0;
	public int PlayerWantToMovey=0;
	
	public GameWindow(Level level)
	{
		this.level = level;
		createGUI();
	}
	//@Override
	public void createGUI() {
		// TODO Auto-generated method stub
		debug = true;
		
		Floors = new BufferedImage[4];
		Walls = new BufferedImage[1];
		try {	
			
			Floors[0] = ImageIO.read(new File("Floor1.bmp"));
			Floors[1] = ImageIO.read(new File("Floor2.bmp"));
			Floors[2] = ImageIO.read(new File("Floor3.bmp"));
			Floors[3] = ImageIO.read(new File("Floor4.bmp"));
			
			Walls[0] = ImageIO.read(new File("Wall1.jpg"));
		
		} catch (IOException e) {
			System.out.println("szar a képbeolvasas");
		}
		
		
		
		
		frame = new JFrame("GAMEWINDOW");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		
		panel = new GamePanel(this);
		
		
		frame.add(panel);
		frame.setVisible(true);
		
		System.out.println("MainWindow:GUI Genarated");
		
		hero = new Hero();
		hero.x = level.heroStartPos.width*sizeFactor;
		hero.y = level.heroStartPos.height*sizeFactor;
		
		monster = new Monster();
		monster.x = level.monsterStartPos.width*sizeFactor;  
		monster.y = level.monsterStartPos.height*sizeFactor;
		
		pal = new PlayerActionListener(this);
		frame.addKeyListener(pal);
	}

	//@Override
	public void displayText(String text) {
		// TODO Auto-generated method stub
		System.out.println("MainWindow:text displayed");

	}
	
	
	public void render()
	{
		panel.repaint();
	}
	//actionHandler
	//create button -> addCommand: SelectHero -> addEventListener(pal)

}
