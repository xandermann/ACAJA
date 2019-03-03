package gui.processing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import resources.ResourceConstants;

public class PictureVisualView extends JPanel implements Observer{
	private Model model;

	public PictureVisualView(Model m) {
		model = m;
		this.setPreferredSize(new Dimension(500, 350));
		DrawChange d = new DrawChange(model);
		this.addMouseListener(d);
		this.setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
		
		for (int i = 0; i < this.model.getListRect().size(); i++) {
			int[] tab = this.model.getListRect().get(i).getTab();
			g.drawRect(tab[0],tab[1],(tab[2]-tab[0]),(tab[3]-tab[1]));
			ImageIcon m = new ImageIcon(ResourceConstants.ACAJA_LOGO_OPACITY_PATH);
			Image monImage = m.getImage();
			g.drawImage(monImage, tab[0],tab[1],(tab[2]-tab[0]),(tab[3]-tab[1]), this);
		}
		this.repaint();
		

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.repaint();
		
	}

}
