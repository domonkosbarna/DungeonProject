package dungeonProjectPackage;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public interface MainWindow extends WindowAdapter
implements ActionListener {
	


	public void createGUI();
	//render map
	//display welcome message
	
	public void displayText(String text);
	//display text

	

}
