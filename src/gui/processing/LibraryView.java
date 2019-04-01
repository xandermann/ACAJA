package gui.processing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class LibraryView extends JPanel {
	
	private ProcessingModel model;

	public LibraryView(ProcessingModel m) {
		this.model = m;
		this.setPreferredSize(new Dimension(250, 550));
		this.setLayout(new GridLayout(5,1));
		createAll();
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		createAll();
		ProcessingTools.drawDeco(g, this.getHeight(), this.getWidth());
		
	}
	
	public void createAll() {
		removeAll();
		
		for(File i : model.getImages()) {
			JPanel j = new JPanel() {
				@Override
				public void paintComponent(Graphics g) {
					super.paintComponents(g);
					Image image = null;
					try { image = ImageIO.read(i);} catch (IOException e) {e.printStackTrace();}
				
					g.drawImage(image,0,0,this.getWidth(),150,null);
				}
			};
			j.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					model.addForm(10, 10, 200, 150, 'i');
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
			this.add(j);
		}
		
		if(model.getImages().size()<5) {
			for(int i = 0;i< 5-model.getImages().size();i++) {
				JPanel j = new JPanel();
				this.add(j);
			}
		}
		revalidate();
	}
}
