package gui.conversion;

import javax.swing.JTabbedPane;

public final class TabsView extends JTabbedPane{
	private ConversionModel model;
	
	public TabsView(ConversionModel model) {
		this.model = model;
		VideoSettingsPanel vsp = new VideoSettingsPanel(model);
		SoundSettingsPanel ssp = new SoundSettingsPanel(model);
		add("Video",vsp);
		add("Son",ssp);
		model.addObserver(vsp);
		model.addObserver(ssp);
	}
}
