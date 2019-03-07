package gui.processing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import resources.ResourceConstants;

public class PicturePan extends JPanel {
	
	private ProcessingModel model;
	public PicturePan(ProcessingModel m) {
		this.model = m;
		this.setPreferredSize(new Dimension(600, 550));
		this.setLayout(new GridBagLayout());
		
		PictureVisualView pic = new PictureVisualView(this.model);
		this.add(pic);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ProcessingTools.drawDeco(g, this.size().height, this.size().width);
		ImageIcon m = new ImageIcon(ResourceConstants.ACAJA_LOGO_OPACITY_PATH);
		Image monImage = m.getImage();
		g.drawImage(monImage, this.size().height / 2 - 130, this.size().width / 2 - 150, this);
	}
}
