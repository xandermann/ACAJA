package gui.conversion.views;

import java.awt.Dimension;

import javax.swing.JTabbedPane;
import gui.general.Context;

public final class TabsView extends JTabbedPane{
	public TabsView() {
		Context.$C(3, this);
		
		setSize(new Dimension(320, 200));
		
		SoundSettingsView ssp = new SoundSettingsView();
		VideoSettingsView vsp = new VideoSettingsView(ssp);
		add("Video",vsp);
		add("Son",ssp);
		Context.$M.addObserver(vsp);
		Context.$M.addObserver(ssp);
	}
}