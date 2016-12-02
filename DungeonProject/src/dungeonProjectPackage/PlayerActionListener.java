package dungeonProjectPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlayerActionListener implements ActionListener, MouseListener, KeyListener {

	private GameWindow gw;
	
	public PlayerActionListener(GameWindow gw)
	{
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Action performed:"+ arg0);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_A) {
            gw.hero.x--;
            //System.out.println("balra");
        }
        else if(e.getKeyCode() == KeyEvent.VK_D) {
        	gw.hero.x++;
        }
        else if(e.getKeyCode() == KeyEvent.VK_W) {
        	gw.hero.y--;
        	//System.out.println(gw.hero.y);
        }
        else if(e.getKeyCode() == KeyEvent.VK_S) {
            gw.hero.y++;
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
        	gw.xOffset++;
            //System.out.println("balra");
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
        	gw.xOffset--;
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP) {
        	gw.yOffset++;
        	//System.out.println(gw.hero.y);
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
        	gw.yOffset--;
        }
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
