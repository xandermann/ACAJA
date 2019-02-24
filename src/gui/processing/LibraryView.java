package gui.processing;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LibraryView extends JPanel {

	public LibraryView() {

		this.setPreferredSize(new Dimension(250, 550));
		this.add(new JLabel("Bibliotheque"));

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ProcessingTools.drawDeco(g, this.size().height, this.size().width);
	}
}
