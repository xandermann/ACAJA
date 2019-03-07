package gui.conversion.views_controllers;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;

import gui.conversion.model.ConversionModel;

public final class TabsView extends JTabbedPane{
	//private SoundSettingsView ssp;
	
	public TabsView(ConversionModel model) {
		if(model == null) throw new NullPointerException("ConversionModel null !");
		//addChangeListener(new TabsView_changeAdapter(this));
		SoundSettingsView ssp = new SoundSettingsView(model);
		VideoSettingsView vsp = new VideoSettingsView(model, ssp);
		add("Video",vsp);
		add("Son",ssp);
		model.addObserver(vsp);
		model.addObserver(ssp);
		
	}
	
	/*public void stateChanged(ChangeEvent e) {
		    JTabbedPane tabSource = (JTabbedPane) e.getSource();
		    String tab = tabSource.getTitleAt(tabSource.getSelectedIndex());
		    if (tab.equals("Son")) {
		      ssp.updateAudioCodecs();
		    }
		    
		  }*/
}


/*class TabsView_changeAdapter implements javax.swing.event.ChangeListener {
	TabsView adapted;
	TabsView_changeAdapter(TabsView s_adapted) {
	    adapted = s_adapted;
	  }
	  public void stateChanged(ChangeEvent e) {
	    adapted.stateChanged(e);
	  }
	}*/