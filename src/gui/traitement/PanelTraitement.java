package gui.traitement;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelTraitement extends JPanel {
	
	
	public PanelTraitement() {
		this.setBackground(Color.GRAY);
		
		PanelLibrary pb = new PanelLibrary();
		PanelButton pm = new PanelButton();
		PanelImage pi = new PanelImage();
		this.add(pb);
		this.add(pm);
		this.add(pi);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	
}
