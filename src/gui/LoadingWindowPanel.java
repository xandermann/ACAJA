package gui;

import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class LoadingWindowPanel extends JPanel {

	private JProgressBar bar;
	private Graphics g;

	public LoadingWindowPanel() {
		this.bar = new JProgressBar();
	}

	public void paintComponent(Graphics g) {
		this.bar.paint(g);
		this.g = g;
	}

	public void setProgress(int progress) {
		this.bar.setValue(progress);
		this.bar.update(g);
	}

}
