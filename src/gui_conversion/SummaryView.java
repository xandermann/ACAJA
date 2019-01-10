package gui_conversion;
import javax.swing.*;

import files.SettingsFile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.*;
public class SummaryView extends JPanel implements Observer{
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	private ConversionModel model;
	private JPanel j,j1,j2,j3;
	private JLabel nomvar,videovar,sonvar,dureevar;
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	public SummaryView (ConversionModel p_model) {
		this.model = p_model;
		this.setBackground(Color.BLUE);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel nom = new JLabel("Fichier selectionne : ");
		JLabel video = new JLabel("Codec video actuel : ");
		JLabel son = new JLabel("Codec video actuel : ");
		JLabel duree = new JLabel("Duree : ");
		
		j = new JPanel();
		j.setLayout(new FlowLayout());
		j.add(nom,BorderLayout.EAST);
		
		j1 = new JPanel();
		j1.setLayout(new FlowLayout());
		j1.add(video,BorderLayout.EAST);
		
		j2 = new JPanel();
		j2.setLayout(new FlowLayout());
		j2.add(son,BorderLayout.EAST);
		
		j3 = new JPanel();
		j3.setLayout(new FlowLayout());
		j3.add(duree,BorderLayout.EAST);
		nomvar = new JLabel("NA");
		j.add(nomvar,BorderLayout.WEST);
		videovar = new JLabel("NA");
		j1.add(videovar,BorderLayout.WEST);
		sonvar = new JLabel("NA");
		j2.add(sonvar,BorderLayout.WEST);
		//dureevar = new JLabel("NA");
		//j2.add(dureevar,BorderLayout.WEST);
		
		this.add(j);
		this.add(j1);
		this.add(j2);
		this.add(j3);
	}
	

	//=======================================================================================================================
	//=======================================================================================================================
	
	
	@Override
	public void update(Observable o, Object arg) {
		if(this.model.getCurrentFile() != null) {
			nomvar.setText(this.model.getCurrentFile().getSourceFilename());
			videovar.setText((String) this.model.getCurrentFile().getSettings().get(SettingsFile.VIDEO_CODEC));
			sonvar.setText((String) this.model.getCurrentFile().getSettings().get(SettingsFile.AUDIO_CODEC));
			//dureevar.setText((String) this.model.getCurrentFile().getSettings().get("duree"));
		}
		this.repaint();
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
