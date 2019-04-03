package gui.processing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	private Dimension dimHoriz;
	private Dimension dimVerti;
	
	
	public PictureVisualView() {
		dimHoriz = new Dimension(500,350);
		dimVerti = new Dimension(350,500);
		this.setSize(dimHoriz);
		DrawChange d = new DrawChange((ProcessingModel)Context.$M);
		this.addMouseListener(d);
		this.addMouseMotionListener(d);
		this.setOpaque(false);
		((ProcessingModel)Context.$M).addObserver(this);
		this.repaint();
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
	

	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		if(Context.$M.getCurrentFile() != null) {
			try {
				//this.remove(pictureView);
				BufferedImage pic = ImageIO.read(((ProcessingModel)Context.$M).getMinia());
				if(((ProcessingModel)(Context.$M)).isRotate180()) {
					pic = rotateImageByDegrees(pic,180);
					//pictureView = new PictureVisualView(dimHoriz,pic);
					setSize(dimHoriz);
					setLocation(new Point(50,110));
				} else if(((ProcessingModel)(Context.$M)).isRotateLeft()) {
					pic = rotateImageByDegrees(pic,-90);
					//pictureView = new PictureVisualView(dimVerti,pic);
					//pictureView.setLocation(new Point(125,30));
					setSize(dimVerti);
					setLocation(new Point(125,30));
				} else if(((ProcessingModel)(Context.$M)).isRotateRight()) {
					pic = rotateImageByDegrees(pic,90);
					setSize(dimVerti);
					setLocation(new Point(125,30));
				} else if(((ProcessingModel)(Context.$M)).isRotate180Right()) {
					pic = flipImage(pic);
					pic = rotateImageByDegrees(pic,90);
					setSize(dimVerti);
					setLocation(new Point(125,30));
				} else if(((ProcessingModel)(Context.$M)).isRotate180Left()) {
					pic = flipImage(pic);
					pic = rotateImageByDegrees(pic,-90);
					setSize(dimVerti);
					setLocation(new Point(125,30));
				}else {
					setSize(dimHoriz);
					setLocation(new Point(50,110));
				}	
				if(pic != null)
					g.drawImage(pic, 0,0,this.getWidth(),this.getHeight(), this);
			} catch (IOException e) {
				e.printStackTrace();
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
				try {
					monImage = new ImageIcon(ImageIO.read(((ProcessingModel)Context.$M).getListRect().get(i).getImageA()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				default:
					break;
			}
			
			//System.out.println("Coordonnees : " + tab[0] + ", " + tab[1] + ", " + tab[2] + ", " + tab[3]);
			g.drawRect(tab[0],tab[1],tab[2],tab[3]);
			//System.out.println("Image :" + monImage.getImage().toString());
			g.drawImage(monImage.getImage(), tab[0],tab[1],tab[2],tab[3], this);	
			
		}	
	}
	
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.repaint();
	}

}
