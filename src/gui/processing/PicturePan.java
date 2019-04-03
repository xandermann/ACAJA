package gui.processing;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import gui.general.Context;
import resources.ResourceConstants;

public class PicturePan extends JPanel implements Observer{

	private static final long serialVersionUID = 2812027917094282289L;
	private PictureVisualView pictureView;

	public PicturePan() {
		this.setLayout(new GridBagLayout());
		pictureView = new PictureVisualView();
		this.add(pictureView);
		((ProcessingModel)Context.$M).addObserver(this);
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ProcessingTools.drawDeco(g, getHeight(), getWidth());
		ImageIcon m = new ImageIcon(ResourceConstants.ACAJA_LOGO_OPACITY_PATH);
		Image monImage = m.getImage();
		g.drawImage(monImage, getHeight() / 2 - 130, getWidth() / 2 - 150, this);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
}
