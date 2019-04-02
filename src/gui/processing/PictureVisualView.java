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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1662237756804313398L;
	
	public PictureVisualView(ProcessingModel m) {
		this.setPreferredSize(new Dimension(500, 350));
		DrawChange d = new DrawChange(m);
		this.addMouseListener(d);
		this.addMouseMotionListener(d);
		this.setOpaque(false);
	}

	
	public BufferedImage rotateImag (BufferedImage imag, int n) { //n rotation in gradians

        double rotationRequired = Math.toRadians (n);
        double locationX = imag.getWidth() / 2;
        double locationY = imag.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);         
       BufferedImage newImage =new BufferedImage(imag.getWidth(), imag.getHeight(), imag.getType()); //20, 20 is a height and width of imag ofc
       op.filter(imag, newImage);

       return(newImage);
     }
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		BufferedImage pic = null;
		AffineTransform tx = null;
		AffineTransformOp op = null;
		if(Context.$M.getCurrentFile() != null) {
			try {
				pic = ImageIO.read(((ProcessingModel)Context.$M).getMinia());
				setSize(new Dimension(500,350));
				g.drawImage(pic, 0,0,this.getWidth(),this.getHeight(), this);
				
				/*
				if(((ProcessingModel)(Context.$M)).isRotate180()) {
					if(getWidth() == 350) {
						setSize(new Dimension(500,350));
						setLocation(new Point(160,50));
						repaint();
					}
					
					
					pic = rotateImag(pic,180);
					g.drawImage(pic, 0,0,this.getWidth(),this.getHeight(), this);
					//this.setLocation(new Point())
				}
				
				if(((ProcessingModel)(Context.$M)).isRotateLeft()) {
					setSize(new Dimension(350, 500));
					
					pic = rotateImag(pic,90);
					g.drawImage(pic, 0,0,this.getHeight(),this.getWidth(), this);
					
				}
				*/
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
		
		
		
		this.repaint();
		

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.repaint();
		
	}

}
