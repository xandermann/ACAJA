package gui.processing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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

public class PictureVisualView extends JPanel implements Observer{

	private static final long serialVersionUID = 1662237756804313398L;
	BufferedImage picture;
	public PictureVisualView(Dimension dim, BufferedImage image) {
		this.setSize(dim);
		DrawChange d = new DrawChange((ProcessingModel)Context.$M);
		this.addMouseListener(d);
		this.addMouseMotionListener(d);
		this.setOpaque(false);
		picture = image;
		((ProcessingModel)Context.$M).addObserver(this);
	}


	

	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		AffineTransform tx = null;
		AffineTransformOp op = null;
		
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		ImageIcon monImage =null;
		
		for (int i = 0; i < ((ProcessingModel)Context.$M).getListRect().size(); i++) {
			int[] tab = ((ProcessingModel)Context.$M).getTabInt(i);
			
				
			switch (((ProcessingModel)Context.$M).getType(i)) {
				case 'c':
					g.setColor(Color.BLUE);
					monImage =new ImageIcon("img/test.png");
					break;
				case 'f':
					g.setColor(Color.GREEN);
					monImage =new ImageIcon("img/test.png");
					break;
				case 'i':
					monImage = new ImageIcon(((ProcessingModel)Context.$M).getListRect().get(i).getImageA());
				default:
					break;
			}
			
			g.drawRect(tab[0],tab[1],tab[2],tab[3]);
			g.drawImage(monImage.getImage(), tab[0],tab[1],tab[2],tab[3], this);
			
			
			
		}
		if(picture != null)
			g.drawImage(picture, 0,0,this.getWidth(),this.getHeight(), this);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.repaint();
	}

}
