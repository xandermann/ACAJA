package gui.conversion;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public final class ConversionWindowController implements WindowListener {
	private ConversionModel model;
	
	public ConversionWindowController(ConversionModel model) {
		this.model = model;
	}
	
	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {
		model.saveImports();
	}
	
	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}
}
