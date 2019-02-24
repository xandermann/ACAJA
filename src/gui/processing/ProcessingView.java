package gui.processing;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ProcessingView extends JPanel {

	public ProcessingView() {
		this.setBackground(Color.GRAY);

		LibraryView pb = new LibraryView();
		ButtonView pm = new ButtonView();
		PictureView pi = new PictureView();
		this.add(pb);
		this.add(pm);
		this.add(pi);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

}
