package gui.traitement;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;

import tools.*;

public class PanelImage extends JPanel{
	public PanelImage() {
		this.setPreferredSize(new Dimension(600, 550));
		this.add(new JLabel("Vidéo à éditer"));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ToolTraitement.drawDeco(g, this.size().height, this.size().width);
		
	}
}
