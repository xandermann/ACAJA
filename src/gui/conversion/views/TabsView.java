package gui.conversion.views;

import java.awt.Dimension;

import javax.swing.JTabbedPane;
import gui.general.Context;

public final class TabsView extends JTabbedPane{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6644027167257732786L;

	public TabsView() {
		Context.$C(3, this);
		
		//setSize(new Dimension(320, 200));
		
		SoundSettingsView ssp = new SoundSettingsView();
		ssp.setSize(new Dimension(getWidth(),getHeight()));
		VideoSettingsView vsp = new VideoSettingsView(ssp);
		vsp.setSize(new Dimension(getWidth(),getHeight()));
		add("Video",vsp);
		add("Son",ssp);
		Context.$M.addObserver(vsp);
		Context.$M.addObserver(ssp);
	}
}