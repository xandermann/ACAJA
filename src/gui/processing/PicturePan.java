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
	private Dimension dimHoriz;
	private Dimension dimVerti;
	public PicturePan() {
		this.setLayout(new GridBagLayout());
		dimHoriz = new Dimension(500,350);
		dimVerti = new Dimension(350,500);
		pictureView = new PictureVisualView(dimHoriz,null);
		this.add(pictureView);
		((ProcessingModel)Context.$M).addObserver(this);
		
	}

	public BufferedImage flipImage(BufferedImage img) {
		
	    BufferedImage mirrored = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);

	    Graphics2D graphics = (Graphics2D)mirrored.getGraphics();
	    AffineTransform transform = new AffineTransform();
	    transform.setToScale(1, -1);
	    transform.translate(0, -img.getHeight());
	    graphics.setTransform(transform);
	    graphics.drawImage(img, 0, 0, null);

	    return mirrored;
	}
	public BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {

        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, this);
        g2d.dispose();

        return rotated;
    }
	

	
	public void replacePictureVisualView() {
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ProcessingTools.drawDeco(g, getHeight(), getWidth());
		ImageIcon m = new ImageIcon(ResourceConstants.ACAJA_LOGO_OPACITY_PATH);
		Image monImage = m.getImage();
		g.drawImage(monImage, getHeight() / 2 - 130, getWidth() / 2 - 150, this);
		if(Context.$M.getCurrentFile() != null) {
			try {
				this.remove(pictureView);
				BufferedImage pic = ImageIO.read(((ProcessingModel)Context.$M).getMinia());
				if(((ProcessingModel)(Context.$M)).isRotate180()) {
					pic = rotateImageByDegrees(pic,180);
					pictureView = new PictureVisualView(dimHoriz,pic);
					pictureView.setLocation(new Point(50,110));
				} else if(((ProcessingModel)(Context.$M)).isRotateLeft()) {
					pic = rotateImageByDegrees(pic,-90);
					pictureView = new PictureVisualView(dimVerti,pic);
					pictureView.setLocation(new Point(125,30));
				} else if(((ProcessingModel)(Context.$M)).isRotateRight()) {
					pic = rotateImageByDegrees(pic,90);
					pictureView = new PictureVisualView(dimVerti, pic);
					pictureView.setLocation(new Point(125,30));
				} else if(((ProcessingModel)(Context.$M)).isRotate180Right()) {
					pic = flipImage(pic);
					pic = rotateImageByDegrees(pic,90);
					pictureView = new PictureVisualView(dimVerti, pic);
					pictureView.setLocation(new Point(125,30));
				} else if(((ProcessingModel)(Context.$M)).isRotate180Left()) {
					pic = flipImage(pic);
					pic = rotateImageByDegrees(pic,-90);
					pictureView = new PictureVisualView(dimVerti, pic);
					pictureView.setLocation(new Point(125,30));
				}else {
					pictureView = new PictureVisualView(dimHoriz,pic);
					pictureView.setLocation(new Point(50,110));
				}
				this.add(pictureView);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	
		
	}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		repaint();
		
	}
}
/*
if(Context.$M.getCurrentFile() != null) {
try {
	pic = ImageIO.read(((ProcessingModel)Context.$M).getMinia());
	
	
	
		if(getWidth() == 350) {
			setSize(new Dimension(500,350));
			setLocation(new Point(50,110));
			//repaint();
		}
		pic = rotateImag(pic,180);
		g.drawImage(pic, 0,0,this.getWidth(),this.getHeight(), this);
		} else if(((ProcessingModel)(Context.$M)).isRotateLeft()) {
		if(getWidth() == 500) {
			setSize(new Dimension(350, 500));
			
			/*
			setSize(new Dimension(500,500));
			setLocation(new Point(1,1));
		//	repaint();
		} 
		
		
		pic = rotateImag(pic,90);
		
		g.drawImage(pic, -1,1,350,500, this);
		
	}else {
		if(getWidth() == 350) {
			setSize(new Dimension(500,350));
			
			//repaint();
		}
		
		g.drawImage(pic, 0,0,this.getWidth(),this.getHeight(), this);
		
	}
	
	
	
} catch (IOException e) {
	e.printStackTrace();
}
}
*/