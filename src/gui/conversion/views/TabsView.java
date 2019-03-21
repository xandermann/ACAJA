package gui.conversion.views;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;

import gui.conversion.ConversionContext;
import gui.conversion.ConversionModel;
import gui.general.GeneralContext;

public final class TabsView extends JTabbedPane{
	public TabsView() {
		if(model == null) throw new NullPointerException("ConversionModel null !");
		SoundSettingsView ssp = new SoundSettingsView();
		VideoSettingsView vsp = new VideoSettingsView(ssp);
		add("Video",vsp);
		add("Son",ssp);
		ConversionContext.$M.addObserver(vsp);
		ConversionContext.$M.addObserver(ssp);
	}
}