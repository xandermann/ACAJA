package gui.processing;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PictureVisualView extends JPanel {

	public PictureVisualView() {
		this.setPreferredSize(new Dimension(500, 350));
		this.setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect(0, 0, this.size().width - 1, this.size().height - 1);

	}
}