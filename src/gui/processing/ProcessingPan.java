package gui.processing;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ProcessingPan extends JPanel {

	public ProcessingPan(ProcessingModel m) {
		this.setBackground(Color.GRAY);
		LibraryView pb = new LibraryView(m);
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
