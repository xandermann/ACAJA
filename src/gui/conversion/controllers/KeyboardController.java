package gui.conversion.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gui.conversion.model.ConversionModel;

public final class KeyboardController implements KeyListener {
	private ConversionModel model;
	
	public KeyboardController(ConversionModel model) {
		if((this.model = model) == null) throw new NullPointerException("ConversionModel null !");
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_DELETE) 
			model.remove(model.getCurrentFile()); 
		else {
			if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
				System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
}
