package gui.processing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PictureVisualView extends JPanel implements Observer{
	
	private ProcessingModel model;

	public PictureVisualView(ProcessingModel m) {
		model = m;
		this.setPreferredSize(new Dimension(500, 350));
		DrawChange d = new DrawChange(model);
		this.addMouseListener(d);
		this.setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image pic = null;
		
		if(this.model.getCurrentFile() != null) {
			try {
				pic = ImageIO.read(this.model.getMinia());
				g.drawImage(pic, 0,0,this.getWidth(),this.getHeight(), this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		g.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
		Image monImage =null;
		
		for (int i = 0; i < this.model.getListRect().size(); i++) {
			int[] tab = this.model.getTabInt(i);
			
				
			switch (this.model.getType(i)) {
			case 'c':
				g.setColor(Color.BLUE);
				monImage =new ImageIcon("img/test.png").getImage();
				break;
			case 'f':
				g.setColor(Color.GREEN);
				monImage =new ImageIcon("img/test.png").getImage();
				break;

			default:
				break;
			}
			
			g.drawRect(tab[0],tab[1],tab[2],tab[3]);
			g.drawImage(monImage, tab[0],tab[1],tab[2],tab[3], this);
		}
		
		
		
		this.repaint();
		

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.repaint();
		
	}

}
