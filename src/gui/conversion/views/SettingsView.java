package gui.conversion.views;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.conversion.model.ConversionModel;
import gui.style.StyleTheme;
import gui.style.StylizedJPanel;

public abstract class SettingsView extends StylizedJPanel implements Observer {
	protected ConversionModel model;
	protected boolean isChange;
	
	public SettingsView(ConversionModel model) {
		super();
		if((this.model = model) == null) throw new NullPointerException("ConversionModel null !");
		isChange = true;
		setSize(new Dimension(300, 400));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

	@Override
	public abstract void update(Observable o, Object arg);
}
