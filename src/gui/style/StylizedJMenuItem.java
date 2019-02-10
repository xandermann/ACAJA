package gui.style;

import java.awt.Color;

/**
 * Classe qui permet de styliser le composant java swing correspondant pour
 * designer l'application.
 */
public class StylizedJMenuItem extends javax.swing.JMenuItem implements StylizedComponent {

	public StylizedJMenuItem(String title) {
		super(title);
	}

	@Override
	public void stylize() {
		this.setBackground(StyleConfigurator.BACKGROUND_COLOR);
		this.setForeground(Color.BLACK);
		// this.setBorderPainted(true);
	}

}
