package gui.traitement;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelLibrary extends JPanel{

	public PanelLibrary() {
		
		this.setPreferredSize(new Dimension(250, 550));
		this.add(new JLabel("Bibliotheque"));
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ToolTraitement.drawDeco(g, this.size().height,this.size().width);
	}
}
