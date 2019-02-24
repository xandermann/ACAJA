package gui.conversion;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class SettingsView extends JPanel implements Observer {
	protected ConversionModel model;
	protected boolean isChange;
	
	public SettingsView(ConversionModel model) {
		if((this.model = model) == null) throw new NullPointerException("ConversionModel null !");
		isChange = true;
	}

	@Override
	public abstract void update(Observable o, Object arg);
}
