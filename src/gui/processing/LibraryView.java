package gui.processing;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import gui.general.Context;

public class LibraryView extends JPanel implements Observer{
	private static final long serialVersionUID = -2415628952891609528L;
	private Image image;

	public LibraryView() {
		this.setLayout(new GridLayout(4, 1));
		this.repaint();
		createAll();
	}

	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ProcessingTools.drawDeco(g, this.getHeight(), this.getWidth());
	}
	
	

	public void createAll() {
		removeAll();

		for (File i : ((ProcessingModel)Context.$M).getImages()) {
			JPanel j = new JPanel() {
				private static final long serialVersionUID = 4570923892169107221L;
				public void paintComponent(Graphics g) {
					super.paintComponents(g);
					try {
						image = ImageIO.read(i);
					} catch (IOException e) {}
					g.drawImage(image, 12, 12, this.getWidth() - 24, 120, null);
				}
			};
			this.add(j);
		}

		this.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				int ind = e.getY() / (getHeight() / 4);
				((ProcessingModel)Context.$M).addForm(10, 10, 200, 150, 'i', ((ProcessingModel)Context.$M).getImages().get(ind));
			}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
		});

		revalidate();
		repaint();
	}


	
	@Override
	public void update(Observable o, Object arg) {
		createAll();
	}
}
