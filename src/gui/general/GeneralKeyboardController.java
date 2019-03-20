 package gui.general;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gui.alerts.AlertManager;
import gui.conversion.ConversionModel;
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
}
