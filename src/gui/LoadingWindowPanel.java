package gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import style.Style;

public class LoadingWindowPanel extends JPanel {

	private int percentCurrentProgress;
	
	public void paintComponent(Graphics g) {
		
	
		g.setColor(Style.BACKGROUND_SECONDARY);
		g.fillRect(20, 20, 200, 20);
		
		g.setColor(Style.BACKGROUND_LIGHT);
		g.fillRect(20, 20, (this.percentCurrentProgress*200)/100, 20);
		
	}

	public void setProgress(int progress) {
		this.percentCurrentProgress = progress;
		this.repaint();
	}

}
