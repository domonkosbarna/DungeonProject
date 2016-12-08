package dungeonProjectPackage;

import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow extends WindowAdapter {
	
	
	public JFrame frame;
	public JPanel panel;
	
	public Hero hero =null;
	public Monster monster =null;
	
	private PlayerActionListener pal;
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
