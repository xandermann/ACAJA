package gui.conversion.views_controllers;

import javax.swing.JTabbedPane;
import gui.conversion.model.ConversionModel;

public final class TabsView extends JTabbedPane{
	public TabsView(ConversionModel model) {
		if(model == null) throw new NullPointerException("ConversionModel null !");
		SoundSettingsView ssp = new SoundSettingsView(model);
		VideoSettingsView vsp = new VideoSettingsView(model);
		add("Video",vsp);
		add("Son",ssp);
		model.addObserver(vsp);
		model.addObserver(ssp);
	}
}
