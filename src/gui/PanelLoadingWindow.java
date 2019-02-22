package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import gui.style.StylizedJPanel;
import resources.ResourceConstants;

public class PanelLoadingWindow extends StylizedJPanel {
	
	public void paintComponent(Graphics g) {
		try {
			g.drawImage(ImageIO.read(ResourceConstants.ACAJA_LOGO), 80, 60, null);
		} catch (IOException ioe) {
			System.exit(1);
		}
		
		g.setColor(Color.RED);
		g.fillRect(20, 400, 280, 30);
		
	}

}
