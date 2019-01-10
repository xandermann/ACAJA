package gui_conversion;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.*;
public class SummaryView extends JPanel implements Observer{

	private ConversionModel model;
	private JPanel j,j1,j2,j3;
	
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
		
		this.add(j);
		this.add(j1);
		this.add(j2);
		this.add(j3);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(this.model.getCurrentFile()!=null) {
			JLabel nomvar = new JLabel(this.model.getCurrentFile().getSourceFilename());
			j.add(nomvar,BorderLayout.WEST);
			//JLabel videovar = new JLabel(this.model.getCurrentFile().getSettings().get);
			
		}
		
	}
	@Override
	public void update(Observable o, Object arg) {
		this.repaint();
	}

}
