package gui.processing;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ProcessingPan extends JPanel {

	public ProcessingPan() {
		this.setBackground(Color.GRAY);

		LibraryView pb = new LibraryView();
		ButtonPan pm = new ButtonPan();
		PicturePan pi = new PicturePan();
		this.add(pb);
		this.add(pm);
		this.add(pi);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

}
