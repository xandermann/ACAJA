 package gui.general;

import java.awt.event.*;
import java.io.File;
import gui.JFileChooserManager;
import gui.alerts.*;
import gui.conversion.*;
import resources.ResourcesManager;

public abstract class GeneralKeyboardController implements KeyListener {
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
 					else {
						//CTRL + A
						if((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) {
							try {
								   File f = JFileChooserManager.chooseFile();
								   GeneralContext.MODEL.add(f);
									if(GeneralContext.MODEL instanceof ConversionModel && GeneralContext.MODEL.getCurrentFile() == null)
										((ConversionWindow) GeneralContext.WINDOW).redrawFirstTime();
								   GeneralContext.MODEL.setCurrentFile(
										   GeneralContext.MODEL.getFiles().get(GeneralContext.MODEL.getFiles().size()-1));
									Alert.shortAlert(Alert.SUCCESS, "Import realise avec succes.");
								} catch (Exception exc) {
									Alert.shortAlert(Alert.FAILURE, "Echec de l'import.");
								}
						}
					}
				}
			break;
			
			
			//CTRL + SHIFT + D
			case KeyEvent.VK_D :
				if((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && (e.getModifiers() & KeyEvent.SHIFT_MASK) != 0)
					AlertManager.INTERRUPTOR = false;
			break;
			
			
			case KeyEvent.VK_DELETE :
				//CTRL + SUPPR
				if((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)
					GeneralContext.MODEL.clear();
				//SUPPR
				else
					GeneralContext.MODEL.remove(GeneralContext.MODEL.getCurrentFile()); 
			break;
		}
	}
}
