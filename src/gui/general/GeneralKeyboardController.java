 package gui.general;

import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

import gui.JFileChooserManager;
import gui.alerts.*;
import gui.conversion.*;
import resources.ResourcesManager;
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
			//ECHAP
			case KeyEvent.VK_ESCAPE :
				ResourcesManager.clearResources();
				System.exit(0);
			break;
			
			
			//CTRL + Q
			case KeyEvent.VK_Q :
				if((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) {
					ResourcesManager.clearResources();
					System.exit(0);
				}
			break;
			
			
			case KeyEvent.VK_A :
				if((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 ) {
					//CTRL + SHIFT + A
					if((e.getModifiers() & KeyEvent.SHIFT_MASK) != 0) 
 						AlertManager.INTERRUPTOR = true;
					//CTRL + A
 					else
						GeneralActions.input();
				}
			break;
			
			
			case KeyEvent.VK_D :
				if((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 ) {
					//CTRL + SHIFT + D
					if((e.getModifiers() & KeyEvent.SHIFT_MASK) != 0) 
						AlertManager.INTERRUPTOR = false;
					//CTRL + D
 					else 
						GeneralActions.inputs();
				}
			break;
			

			case KeyEvent.VK_DELETE :
				//CTRL + SUPPR
				if((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)
					GeneralContext.$M.clear();
				//SUPPR
				else
					GeneralContext.$M.remove(GeneralContext.$M.getCurrentFile()); 
			break;
		}
	}
}
