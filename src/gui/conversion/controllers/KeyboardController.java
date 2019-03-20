 package gui.conversion.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gui.alerts.AlertManager;
import gui.conversion.model.ConversionModel;
import resources.ResourcesManager;

public final class KeyboardController implements KeyListener {
	private ConversionModel model;
	
	public KeyboardController(ConversionModel model) {
		if((this.model = model) == null) throw new NullPointerException("ConversionModel null !");
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_DELETE :
				model.remove(model.getCurrentFile()); 
			break;
			case KeyEvent.VK_ESCAPE :
				ResourcesManager.clearResources();
				System.exit(0);
			break;
			//CTRL + SHIFT + A
			case KeyEvent.VK_A :
				if((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && (e.getModifiers() & KeyEvent.SHIFT_MASK) != 0)
					AlertManager.INTERRUPTOR = true;
			break;
			//CTRL + SHIFT + D
			case KeyEvent.VK_D :
				if((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && (e.getModifiers() & KeyEvent.SHIFT_MASK) != 0)
					AlertManager.INTERRUPTOR = false;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
}
