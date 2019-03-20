 package gui.conversion;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gui.alerts.AlertManager;
import gui.general.GeneralKeyboardController;
import resources.ResourcesManager;

public final class ConversionKeyboardController extends GeneralKeyboardController {
	private ConversionModel model;
	
	public ConversionKeyboardController(ConversionModel model) {
		if((this.model = model) == null) throw new NullPointerException("ConversionModel null !");
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
		switch(e.getKeyCode()) {
			case KeyEvent.VK_DELETE :
				//CTRL + SUPPR
				if((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)
					model.clear();
				//SUPPR
				else
					model.remove(model.getCurrentFile()); 
			break;
		}
	}
}
