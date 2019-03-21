package gui.conversion.views;

import javax.swing.JTabbedPane;
import gui.general.Context;

public final class TabsView extends JTabbedPane{
	public TabsView() {
		if(model == null) throw new NullPointerException("ConversionModel null !");
		SoundSettingsView ssp = new SoundSettingsView();
		VideoSettingsView vsp = new VideoSettingsView(ssp);
		add("Video",vsp);
		add("Son",ssp);
		Context.$M.addObserver(vsp);
		Context.$M.addObserver(ssp);
	}
}