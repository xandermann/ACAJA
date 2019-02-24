package gui.conversion.views;

import javax.swing.JTabbedPane;

import gui.conversion.model.ConversionModel;

public final class TabsView extends JTabbedPane{
	private ConversionModel model;
	
	public TabsView(ConversionModel model) {
		this.model = model;
		VideoSettingsView vsp = new VideoSettingsView(model);
		SoundSettingsView ssp = new SoundSettingsView(model);
		add("Video",vsp);
		add("Son",ssp);
		model.addObserver(vsp);
		model.addObserver(ssp);
	}
}
