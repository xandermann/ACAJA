package gui.treatment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelPictureVisual extends JPanel {

	public PanelPictureVisual() {
		this.setPreferredSize(new Dimension(500, 350));
		this.setOpaque(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawLine(0, 0, 0, this.getHeight());
		g.drawLine(0, 0, this.getWidth(), 0);
	
	}
}
