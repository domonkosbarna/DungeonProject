package dungeonProjectPackage;

import java.awt.event.WindowAdapter;

public class GameWindow extends WindowAdapter
							implements ActionListener  {
	
	private Hero hero;
	private PlayerActionListener pal;

	@Override
	public void createGUI() {
		// TODO Auto-generated method stub
		System.out.println("MainWindow:GUI Genarated");
		
		
		hero = new Hero();

	}

	@Override
	public void displayText(String text) {
		// TODO Auto-generated method stub
		System.out.println("MainWindow:text displayed");

	}
	
	//actionHandler
	//create button -> addCommand: SelectHero -> addEventListener(pal)

}
