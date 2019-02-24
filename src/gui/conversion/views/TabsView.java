package gui.conversion.views;

import javax.swing.JTabbedPane;
import gui.conversion.model.ConversionModel;

public final class TabsView extends JTabbedPane{
	public TabsView(ConversionModel model) {
		if(model == null) throw new NullPointerException("ConversionModel null !");
		VideoSettingsView vsp = new VideoSettingsView(model);
		SoundSettingsView ssp = new SoundSettingsView(model);
		add("Video",vsp);
		add("Son",ssp);
		model.addObserver(vsp);
		model.addObserver(ssp);
	}
}
