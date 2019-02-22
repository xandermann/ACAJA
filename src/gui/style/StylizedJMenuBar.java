package gui.style;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.border.LineBorder;

/**
 * Classe qui permet de styliser le composant java swing correspondant pour
 * designer l'application.
 */
public class StylizedJMenuBar extends javax.swing.JMenuBar implements StylizedComponent {

	public StylizedJMenuBar() {
		super();
	}

	@Override
	public void stylize() {
		this.setBackground(StyleConfigurator.BACKGROUND_COLOR);
		this.setBorder(new LineBorder(StyleConfigurator.BACKGROUND_COLOR_SECONDARY));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(StyleConfigurator.BACKGROUND_COLOR);
		g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
	}

}
