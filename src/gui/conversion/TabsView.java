package gui.conversion;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JTabbedPane;

public final class TabsView extends JTabbedPane{
	private ConversionModel model;
	
	public TabsView(ConversionModel m) {
		this.model = m;
		VideoSettingsPanel vsp = new VideoSettingsPanel(this.model);
		SoundSettingsPanel ssp = new SoundSettingsPanel(this.model);
		this.add("Video",vsp);
		this.add("Son",ssp);
		model.addObserver(vsp);
		model.addObserver(ssp);
	}
}
