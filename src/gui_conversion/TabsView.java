package gui_conversion;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JTabbedPane;

public class TabsView extends JTabbedPane implements Observer{
	
	public ConversionModel model;
	
	public TabsView(ConversionModel m) {
		this.model = m;
	}
	
	private void reevaluateTabbedPanel() {
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
	}
}
