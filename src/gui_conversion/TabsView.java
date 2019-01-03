package gui_conversion;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JTabbedPane;

public class TabsView extends JTabbedPane implements Observer{
	
	public ConversionModel model;
	
	public TabsView(ConversionModel m) {
		this.model = m;
		VideoSettingsPanel vsp = new VideoSettingsPanel(this.model);
		SoundSettingsPanel ssp = new SoundSettingsPanel(this.model);
		this.add(vsp);
		this.add(ssp);
	}
	
	private void reevaluateTabbedPanel() {
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
	}
}
