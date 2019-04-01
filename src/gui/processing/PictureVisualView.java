package gui.processing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import gui.general.Context;

public class PictureVisualView extends JPanel implements Observer{

	public PictureVisualView(ProcessingModel m) {
		this.setPreferredSize(new Dimension(500, 350));
		DrawChange d = new DrawChange(m);
		this.addMouseListener(d);
		this.addMouseMotionListener(d);
		this.setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image pic = null;
		
		if(Context.$M.getCurrentFile() != null) {
			try {
				pic = ImageIO.read(((ProcessingModel)Context.$M).getMinia());
				g.drawImage(pic, 0,0,this.getWidth(),this.getHeight(), this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		g.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
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
