package gui.processing;


import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import resources.ResourceConstants;

public class PicturePan extends JPanel {

	private static final long serialVersionUID = 2812027917094282289L;
	private ProcessingModel model;
	
	public PicturePan(ProcessingModel m) {
		this.setLayout(new GridBagLayout());
		PictureVisualView pic = new PictureVisualView(m);
		model = m;
		this.add(pic);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ProcessingTools.drawDeco(g, getHeight(), getWidth());
		ImageIcon m = new ImageIcon(ResourceConstants.ACAJA_LOGO_OPACITY_PATH);
		Image monImage = m.getImage();
		g.drawImage(monImage, getHeight() / 2 - 130, getWidth() / 2 - 150, this);
		
		this.repaint();
		
	}
}
