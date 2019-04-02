package gui.processing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import gui.style.*;

public class ProcessingPan extends JPanel {

	public ProcessingPan(ProcessingModel m) {
		this.setBackground(Color.GRAY);
		LibraryView pb = new LibraryView(m);
		this.setSize(1000,625);
		System.out.println("width "+getWidth() +"height"+getHeight());
		pb.setPreferredSize(new Dimension((int)(getWidth()*(0.2)), (int)(getHeight()*(0.9))));
		ButtonPan pm = new ButtonPan();
		StylizedJPanel processPan = new StylizedJPanel();
		processPan.setLayout(new BoxLayout(processPan, BoxLayout.PAGE_AXIS));
		processPan.setBackground(Color.GRAY);
		processPan.add(pm);
		StylizedJPanel processPanButton = new StylizedJPanel(new BorderLayout());
		processPan.setPreferredSize(new Dimension((int)(getWidth()*(0.1)), (int)(getHeight()*(0.4))));
		StylizedJPanel processPanSpace = new StylizedJPanel();
		processPanSpace.setPreferredSize(new Dimension((int)(getWidth()*(0.1)), (int)(getHeight()*(0.05))));
		processPanSpace.setBackground(Color.GRAY);
		processPan.add(processPanSpace);
		processPanButton.setPreferredSize(new Dimension(processPan.getWidth(), processPan.getHeight()/3));
		processPanButton.add(new StylizedJButton("<html> <head><style> p{ text-align:center }</head><body><p> Démarrer le <br> traitement </p></body></html>"));
		processPan.add(processPanButton);
		PicturePan pi = new PicturePan(m);
		pi.setPreferredSize(new Dimension((int)(getWidth()*(0.6)), (int)(getHeight()*(0.9))));
		this.add(pb);
		this.add(processPan);
		this.add(pi);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

}
