package gui_conversion;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JTabbedPane;

public class TabsView extends JTabbedPane implements Observer{
	
	public ConversionModel model;
	
	public TabsView(ConversionModel m) {
		this.model = m;
		this.reevaluateTabbedPanel();
	}
	
	private void reevaluateTabbedPanel() {
		VideoSettingsPanel vsp = new VideoSettingsPanel(this.model);
		SoundSettingsPanel ssp = new SoundSettingsPanel(this.model);
		this.add("Vidéo",vsp);
		this.add("Son",ssp);
		//this.repaint();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		//this.repaint();
	}
}
