package gui.processing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageView extends JPanel {
	public ImageView() {
		this.setPreferredSize(new Dimension(600, 550));
		this.add(new JLabel("Video a editer"));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ProcessingTools.drawDeco(g, this.size().height, this.size().width);
		ImageIcon m = new ImageIcon(resources.ResourceConstants.ACAJA_LOGO_OPACITY_PATH);
		Image monImage = m.getImage();
		g.drawImage(monImage, this.size().height / 2 - 130, this.size().width / 2 - 150, this);
	}
}
