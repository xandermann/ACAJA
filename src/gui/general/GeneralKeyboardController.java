 package gui.general;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import exceptions.UnfindableResourceException;
import gui.alerts.Alert;
import gui.alerts.AlertSettings;
/**
 * [ CLASSE POUR REALISER DES RACCOURCIS CLAVIER GENERIQUES. ]
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public class GeneralKeyboardController implements KeyListener {
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_A :
				if((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 ) {
					//CTRL + SHIFT + A
					if((e.getModifiers() & KeyEvent.SHIFT_MASK) != 0) 
 						AlertSettings.INTERRUPTOR = true;
					//CTRL + A
 					else
						Actions.input();
				}
			break;
			
			
			case KeyEvent.VK_C :
				if((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 ) 
					Actions.switchMode();
			break;
			
			
			case KeyEvent.VK_D :
				if((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 ) {
					//CTRL + SHIFT + D
					if((e.getModifiers() & KeyEvent.SHIFT_MASK) != 0) 
						AlertSettings.INTERRUPTOR = false;
					//CTRL + D
 					else 
						Actions.inputs();
				}
			break;
			
			
			//CTRL + I
			case KeyEvent.VK_I :
				if((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 )
					try {
						Actions.inspect();
					} catch (UnfindableResourceException e1) {
						Alert.shortAlert(Alert.FAILURE, "Impossible d'ouvrir l'inspecteur <br> de reponses");
					}
			break;
			
			
			//CTRL + P
			case KeyEvent.VK_P :
				if((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 ) 
					Actions.set();
			break;
			
			
			//CTRL + Q
			case KeyEvent.VK_Q :
				if((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) 
					Actions.quit();
			break;
			
			
			//CTRL + R
			case KeyEvent.VK_R :
				if((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 ) 
						Actions.output();
			break;
			
			
			//CTRL + S
			case KeyEvent.VK_S :
				if((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 ) 
						Actions.save();
			break;
			
			
			//ECHAP
			case KeyEvent.VK_ESCAPE :
				Actions.quit();
			break;
			
			
			case KeyEvent.VK_DELETE :
				//CTRL + SUPPR
				if((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)
					Context.$M.clear();
				//SUPPR
				else
					Context.$M.remove(Context.$M.getCurrentFile()); 
			break;
		}
	}
}
